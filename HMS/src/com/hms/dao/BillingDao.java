package com.hms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hms.util.DBConnection;

public class BillingDao 
{
	Connection con = null;
	PreparedStatement ps=null;
	ResultSet result=null;
	CallableStatement st = null;
	CallableStatement st1 = null;
	
	public boolean generateBill(int patientId) throws ClassNotFoundException, SQLException
	{
		int rowstatus=0;
		
		con = DBConnection.getConnection();
		st = con.prepareCall("{call dbo.PatientMastProc(?,?)}");
		st.setString("action", "GENBILL");
		st.setInt("Pid", patientId);
		rowstatus = st.executeUpdate();
		if(rowstatus>0)
		{
			return true;
		}
		else
			return false;
	}
}
