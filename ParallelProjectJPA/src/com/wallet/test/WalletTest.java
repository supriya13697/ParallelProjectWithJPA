package com.wallet.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.wallet.exception.InvalidAmount;
import com.wallet.exception.InvalidPhoneNumber;
import com.wallet.exception.NameException;
import com.wallet.service.WalletServiceImpl;

public class WalletTest {

	@Test
	public void ValidateNameTrue() throws NameException{
		WalletServiceImpl bs = new WalletServiceImpl();
		assertEquals(true, bs.validateUserName("Supriya"));
		assertEquals(true, bs.validateUserName("Suppi"));
	}


	@Test
	public void ValidatePhonNumberTrue() throws InvalidPhoneNumber{
		WalletServiceImpl bs = new WalletServiceImpl();
		assertEquals(true, bs.validatePhoneNumber("9632584170"));
		assertEquals(true, bs.validatePhoneNumber("7702725233"));
	}

	@Test
	public void ValidateAmountTrue() throws InvalidAmount{
		WalletServiceImpl bs = new WalletServiceImpl();
		assertEquals(true, bs.validateAmount(100));
		assertEquals(true, bs.validateAmount(2000000));
	}

	@Test(expected = InvalidPhoneNumber.class )
	public void ValidatePhoneNumber() throws InvalidPhoneNumber{
		WalletServiceImpl bs = new WalletServiceImpl();
		assertEquals(false, bs.validatePhoneNumber("96325"));
		assertEquals(false, bs.validatePhoneNumber("kjvciod"));
		assertEquals(false, bs.validatePhoneNumber("584170"));
		assertEquals(false, bs.validatePhoneNumber("testing"));
		assertEquals(false, bs.validatePhoneNumber("@#%"));
	}

	@Test(expected = NameException.class ) 
	public void ValidateName() throws NameException{
		WalletServiceImpl bs = new WalletServiceImpl();
		assertEquals(false, bs.validateUserName("Supriya123"));
		assertEquals(false, bs.validateUserName("Supriya@1234"));
		assertEquals(false, bs.validateUserName("2514533"));
		assertEquals(false, bs.validateUserName("supriya"));
	}


	@Test(expected = InvalidAmount.class ) 
	public void ValidateAmount() throws InvalidAmount{
		WalletServiceImpl bs = new WalletServiceImpl();
		assertEquals(false, bs.validateAmount(0));
		assertEquals(false, bs.validateAmount(-400));
	}

}
