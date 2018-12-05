package com.wallet.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wallet")
public class Customer {

	@Id
	private String mobileNumber;
	private String customerName;
	private double amount;
		
	public Customer(String name, String mobNo, double amt) {
		this.customerName = name;
		this.mobileNumber = mobNo;
		this.amount = amt;
	}
	
	public Customer() {
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void fundTransfer(double amount) {
		this.amount = this.amount - amount;
	}
	
	
	@Override
	public String toString() {
		return "CustomerName: " + customerName + ", MobileNumber: "+ mobileNumber + ", Amount: " + amount;
	}
	
	
	
}
