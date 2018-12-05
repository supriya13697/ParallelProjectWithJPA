package com.wallet.exception;

public class NameException extends Exception{
	public NameException() {
		// TODO Auto-generated constructor stub
		super("*****Invalid Name declaration*****\n"
				+ "First letter should be capital remaining should be small letter... :)");
	}
}
