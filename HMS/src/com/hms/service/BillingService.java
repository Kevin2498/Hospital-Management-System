package com.hms.service;

import java.sql.SQLException;

import com.hms.dao.BillingDao;

public class BillingService 
{
	BillingDao cdao = new BillingDao();
	
	public boolean generateBill(int patientId) throws ClassNotFoundException, SQLException
	{
		return cdao.generateBill(patientId);
	}
}
