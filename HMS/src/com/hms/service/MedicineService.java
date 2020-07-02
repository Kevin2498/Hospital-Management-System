package com.hms.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.hms.bean.MedicineBean;
import com.hms.dao.MedicineDao;

public class MedicineService 
{
	MedicineDao cdao = new MedicineDao();
	
	public ArrayList<MedicineBean> getMedsDtl(int patientId) throws ClassNotFoundException, SQLException
	{
		return cdao.getMedsDtl(patientId);
	}
	
	public ArrayList<MedicineBean> viewMeds() throws ClassNotFoundException, SQLException
	{
		return cdao.viewMeds();
	}
	
	public boolean addMeds(String[] medId, String[] quantity, String patientId) throws ClassNotFoundException, SQLException
	{
		return cdao.addMeds(medId,quantity,patientId);
	}
}
