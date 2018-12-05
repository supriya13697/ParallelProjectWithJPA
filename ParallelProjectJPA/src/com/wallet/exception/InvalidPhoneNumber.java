package com.wallet.exception;

public class InvalidPhoneNumber extends Exception {
	public InvalidPhoneNumber() {
		// TODO Auto-generated constructor stub
		super("*****Invalid Phone Number*****\n"
				+ "Mobile Number should be of length 10 digits\n"
				+ "It should not consists of any special characters or alphabets :)");
	}
}
