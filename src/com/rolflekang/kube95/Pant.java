package com.rolflekang.kube95;

import java.util.Date;

public class Pant {
	private Date date;
	private String user;
	private double amount;
	
	public Pant(Date date, double amount, String user){
		this.date = date;
		this.user = user;
		this.amount = amount;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() { return date; }

	public void setUser(String user) {
		this.user = user;
	}

	public String getUser() { return user; }

	public void setAmount(double value) {
		this.amount = value;
	}

	public double getAmount() { return amount; }
	
}
