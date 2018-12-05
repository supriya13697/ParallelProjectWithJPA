package com.wallet.exception;

public class InvalidAmount extends Exception{
	public InvalidAmount() {
			super("*****Invalid Amount*****\n"
					+ "Amount can't be negative...\n"
					+ "Amount can't be zero :)");
	}
}
