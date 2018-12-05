package com.wallet.dao;

import java.util.List;
import com.wallet.dto.Customer;
import com.wallet.dto.PrintTransactions;
import com.wallet.exception.WalletException;
import com.wallet.exception.InvalidPhoneNumber;

public interface WalletDAO {
	
	public Customer createAccount(Customer c) throws WalletException;
	public Customer getAccount(String mobileno);
	public boolean setAccount(String mobileNo, double amount);
	
	public List<PrintTransactions> getTransactions(String mobileNo) throws WalletException, InvalidPhoneNumber;
	public void loadTransaction(PrintTransactions pt);

}
