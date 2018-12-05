package com.wallet.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.wallet.dao.WalletDAO;
import com.wallet.dao.WalletDAOImpl;
import com.wallet.dto.Customer;
import com.wallet.dto.PrintTransactions;
import com.wallet.exception.WalletException;
import com.wallet.exception.InvalidAmount;
import com.wallet.exception.InvalidPhoneNumber;
import com.wallet.exception.NameException;

public class WalletServiceImpl implements WalletService {

	WalletDAO dao;

	public WalletServiceImpl() {
		dao = new WalletDAOImpl();
	}

	
	@Override
	public Customer createAccount(Customer c) throws WalletException {

		Customer create = dao.createAccount(c);
		if(create == null)
			throw new WalletException("Mobile number doesn't exist");
		return create;
	}

	@Override
	public double showBalance(String mobileno) throws WalletException {
		
		String tranType = "Check balance";
		Customer bal = dao.getAccount(mobileno);
		if (bal == null)
			throw new WalletException("Mobile number doesn't exist");
		dao.loadTransaction(new PrintTransactions(mobileno, tranType, bal.getAmount()));
		return bal.getAmount();
	}

	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo,double amount) throws WalletException {
		
		String tranType = "Transfer";
		Customer sbal = dao.getAccount(sourceMobileNo);
		Customer tbal = dao.getAccount(targetMobileNo);
		if (sbal == null)
			throw new WalletException("Mobile number doesn't exist");
		if (tbal == null)
			throw new WalletException("Mobile number doesn't exist");
		double tmp = (sbal.getAmount() - amount);
		if (tmp >= 0) {
			dao.setAccount(targetMobileNo, tbal.getAmount() + amount);
			dao.setAccount(sourceMobileNo, sbal.getAmount() - amount);
		} else {
			throw new WalletException(
					"Minimum balance of Rupees greater than zero should be available...");
		}
		dao.loadTransaction(new PrintTransactions(sourceMobileNo, tranType, -amount));
		dao.loadTransaction(new PrintTransactions(targetMobileNo, tranType, amount));
		return dao.getAccount(sourceMobileNo);
	}

	@Override
	public Customer depositAmount(String mobileNo, double amount) throws InvalidPhoneNumber, InvalidAmount, WalletException {
		String tranType = "Deposit";
		Customer cDep1 = dao.getAccount(mobileNo);
		if (cDep1 == null)
			throw new WalletException("Mobile number doesn't exist");
		boolean c = dao.setAccount(mobileNo, cDep1.getAmount() + amount);
		Customer cDep = dao.getAccount(mobileNo);
		dao.loadTransaction(new PrintTransactions(mobileNo, tranType, amount));
		if (!c)
			throw new WalletException("Unable to deposit");
		else
			return cDep;
	}

	@Override
	public Customer withdrawAmount(String mobileNo, double amount) throws WalletException {
		String tranType = "Withdraw";
		boolean c = false;
		Customer cDep1 = dao.getAccount(mobileNo);
		if (cDep1 == null)
			throw new WalletException("Mobile number doesn't exist");
		if ((cDep1.getAmount() - amount) >= 0)
			c = dao.setAccount(mobileNo, cDep1.getAmount() - amount);
		Customer cDep = dao.getAccount(mobileNo);
		dao.loadTransaction(new PrintTransactions(mobileNo, tranType, amount));
		if (!c)
			throw new WalletException("Unable to withdraw");
		else
			return cDep;
	}

	@Override
	public List<PrintTransactions> getTransactions(String mobileNo) throws WalletException, InvalidPhoneNumber {
		if(dao.getAccount(mobileNo) == null)
			throw new WalletException("Mobile number doesn't exist");
		List<PrintTransactions> list = null;
		list = dao.getTransactions(mobileNo);
		return list;
	}
	
	@Override
	public boolean validateAll(Customer c) throws WalletException, NameException, InvalidAmount, InvalidPhoneNumber {
		boolean b = false;
		if (validateUserName(c.getCustomerName()) == true && validatePhoneNumber(c.getMobileNumber()) == true && validateAmount(c.getAmount()) == true)
			b = true;
		if (!b)
			throw new WalletException("Invalid details");
		return b;
	}

	@Override
	public boolean validateUserName(String name) throws NameException {
		Pattern p = Pattern.compile("[A-Z]{1}[a-z]{2,30}");
		Matcher mat = p.matcher(name);
		boolean b = mat.matches();
		if (!b)
			throw new NameException();
		return b;
	}

	@Override
	public boolean validatePhoneNumber(String mobileNo) throws InvalidPhoneNumber {
		Pattern pat = Pattern.compile("[6-9]{1}[0-9]{9}");
		Matcher mat = pat.matcher(mobileNo);
		boolean b = mat.matches();
		if (!b)
			throw new InvalidPhoneNumber();
		return b;
	}

	@Override
	public boolean validateAmount(double amt) throws InvalidAmount {
		Pattern pat = Pattern.compile("[1-9]{1}[0-9.]{0,9}");
		Matcher mat = pat.matcher(String.valueOf(amt));
		boolean b = mat.matches();
		if (!b)
			throw new InvalidAmount();
		return b;
	}

	@Override
	public boolean validateTargetMobNo(String targetMobNo) throws InvalidPhoneNumber {
		Pattern pat = Pattern.compile("[6-9]{1}[0-9]{9}");
		Matcher mat = pat.matcher(targetMobNo);
		boolean b = mat.matches();
		if (!b)
			throw new InvalidPhoneNumber();
		return b;
	}

}
