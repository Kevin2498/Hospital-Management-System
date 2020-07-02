package com.hms.bean;

public class MedicineBean 
{
	private String medicineName;
	private int quantity;
	private int rate;
	private int amount;
	
	public String getMedicineName()
	{
		return medicineName;
	}
	public void setMedicineName(String medicineName)
	{
		this.medicineName = medicineName;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
	
	public int getRate()
	{
		return rate;
	}
	public void setRate(int rate)
	{
		this.rate = rate;
	}
	
	public int getAmount()
	{
		return amount;
	}
	public void setAmount(int amount)
	{
		this.amount = amount;
	}
}
