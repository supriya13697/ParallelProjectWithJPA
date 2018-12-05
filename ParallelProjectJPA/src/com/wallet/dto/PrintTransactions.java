package com.wallet.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wallettransactions")
public class PrintTransactions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer tranId;
	private String mobileNumber;
	private String tranType;
	private double amount;
	private Date tranDate;
	
	public PrintTransactions() {
	}
	
	public PrintTransactions(String mobileNumber, String tranType, double amount) {
		this.mobileNumber = mobileNumber;
		this.tranType = tranType;
		this.amount = amount;
		this.tranDate = new Date();
	}
	
	public Integer getTranId() {
		return tranId;
	}
	
	public void setTranId(Integer tranId) {
		this.tranId = tranId;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	public String getTranType() {
		return tranType;
	}
	
	public void setTranType(String tranType) {
		this.tranType = tranType;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString()
	{
		return "Transaction ID: " + tranId + ", Mobile Number: "
				+ mobileNumber + ", Transaction Type: " + tranType + ", Amount: "
				+ amount + ", Transaction Date: " + tranDate;
	}
	
	
}
