package com.hms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hms.bean.MedicineBean;
import com.hms.bean.PatientBean;
import com.hms.util.DBConnection;

public class MedicineDao 
{
	Connection con = null;
	PreparedStatement ps=null;
	ResultSet result=null;
	CallableStatement st = null;
	CallableStatement st1 = null;
	
	public ArrayList<MedicineBean> getMedsDtl(int patientId) throws ClassNotFoundException, SQLException
	{
		ArrayList<MedicineBean> medicineList = new ArrayList<MedicineBean>();
		MedicineBean medicine = null;
		
		con = DBConnection.getConnection();
		st = con.prepareCall("{call dbo.MedicineMastProc(?,?)}");
		st.setString("action", "SEARCHMEDS");
		st.setInt("Pid", patientId);
		result = st.executeQuery();
		
		while(result.next())
		{
			medicine = new MedicineBean();
			
			medicine.setMedicineName(result.getString("Medicine_Name"));
			medicine.setQuantity(result.getInt("Quantity"));
			medicine.setRate(result.getInt("Rate"));
			medicine.setAmount(result.getInt("Amount"));
			
			medicineList.add(medicine);
		}
		
		DBConnection.closeConnection(con);
		DBConnection.closeStatement(st);
		
		return medicineList;
	}
	
	public ArrayList<MedicineBean> viewMeds() throws ClassNotFoundException, SQLException
	{
		ArrayList<MedicineBean> medicineList = new ArrayList<MedicineBean>();
		MedicineBean medicine = null;
		
		con = DBConnection.getConnection();
		st = con.prepareCall("{call dbo.MedicineMastProc(?)}");
		st.setString("action", "VIEW");
		result = st.executeQuery();
		
		while(result.next())
		{
			medicine = new MedicineBean();
			
			medicine.setMedicineName(result.getString("Name"));
			medicine.setQuantity(result.getInt("Quantity_Available"));
			medicine.setRate(result.getInt("Rate"));
			medicineList.add(medicine);
		}
		DBConnection.closeConnection(con);
		DBConnection.closeStatement(st);
		
		return medicineList;
	}
	
	public boolean addMeds(String[] medId, String[] quantity, String patientId) throws ClassNotFoundException, SQLException
	{
		int rowstatus=0;
		
		con = DBConnection.getConnection();
		
		for(int i=0;i<medId.length;i++)
		{
			st = con.prepareCall("{call dbo.MedicineMastProc(?,?,?,?)}");
			st.setString("action", "ADDITEM");
			st.setInt("Medicine_Pid", Integer.parseInt(medId[i]));
			st.setInt("Pid", Integer.parseInt(patientId));
			st.setInt("Quantity", Integer.parseInt(quantity[i]));
			rowstatus += st.executeUpdate();
		}
		
		if(rowstatus>0)
		{
			return true;
		}
		else
			return false;
	}
}
