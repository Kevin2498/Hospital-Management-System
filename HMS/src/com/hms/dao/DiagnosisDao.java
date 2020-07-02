package com.hms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hms.bean.DiagnosisBean;
import com.hms.util.DBConnection;

public class DiagnosisDao 
{
	Connection con = null;
	PreparedStatement ps=null;
	ResultSet result=null;
	CallableStatement st = null;
	CallableStatement st1 = null;
	
	public ArrayList<DiagnosisBean> getDiagnosisDtl(int patientId) throws ClassNotFoundException, SQLException
	{
		ArrayList<DiagnosisBean> diagnosisList = new ArrayList<DiagnosisBean>();
		DiagnosisBean diagnosis = null;
		
		con = DBConnection.getConnection();
		st = con.prepareCall("{call dbo.DiagnosisMastProc(?,?)}");
		st.setString("action", "SEARCHDIAG");
		st.setInt("Pid", patientId);
		result = st.executeQuery();
		
		while(result.next())
		{
			diagnosis = new DiagnosisBean();
			
			diagnosis.setDiagnosisName(result.getString("Name"));
			diagnosis.setCharge(result.getInt("Charge"));
			
			diagnosisList.add(diagnosis);
		}
		
		DBConnection.closeConnection(con);
		DBConnection.closeStatement(st);
		
		return diagnosisList;
	}
	
	public ArrayList<DiagnosisBean> bindDiagnosisDtl (int patientId) throws ClassNotFoundException, SQLException
	{
		ArrayList<DiagnosisBean> diagnosisList = new ArrayList<DiagnosisBean>();
		DiagnosisBean diagnosis = null;
		
		con = DBConnection.getConnection();
		st = con.prepareCall("{call dbo.DiagnosisMastProc(?,?)}");
		st.setString("action", "BIND");
		st.setInt("Pid", patientId);
		result = st.executeQuery();
		
		while(result.next())
		{
			diagnosis = new DiagnosisBean();
			
			diagnosis.setDiagnosisId(result.getInt("Pid"));
			diagnosis.setDiagnosisName(result.getString("Name"));
			diagnosis.setCharge(result.getInt("Charge"));
			
			diagnosisList.add(diagnosis);
		}
		
		DBConnection.closeConnection(con);
		DBConnection.closeStatement(st);
		
		return diagnosisList;
	}
	
	public boolean addDiagnosis (int patientId, String[] diagnosisId) throws ClassNotFoundException, SQLException
	{
		int rowstatus=0;
		
		con = DBConnection.getConnection();
		
		for(int i=0;i<diagnosisId.length;i++)
		{
			st = con.prepareCall("{call dbo.DiagnosisMastProc(?,?,?)}");
			st.setString("action", "ADD");
			st.setInt("Diagnosis_Pid", Integer.parseInt(diagnosisId[i]));
			st.setInt("Pid", patientId);
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
