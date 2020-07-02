package com.hms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hms.PatientException.NoPatientException;
import com.hms.bean.PatientBean;
import com.hms.util.DBConnection;
import com.hms.util.DateUtil;
import com.hms.PatientException.NoPatientException;

public class PatientDao 
{
	Connection con = null;
	PreparedStatement ps=null;
	ResultSet result=null;
	CallableStatement st = null;
	CallableStatement st1 = null;
	
	public ArrayList<PatientBean> viewPatient() throws SQLException, ClassNotFoundException
	{
		ArrayList<PatientBean> patientList = new ArrayList<PatientBean>();
		PatientBean patient = null;
		
		con = DBConnection.getConnection();
		st = con.prepareCall("{call dbo.PatientMastProc(?)}");
		st.setString("action", "VIEW");
		result = st.executeQuery();
		
		while(result.next())
		{
			patient = new PatientBean();
			
			patient.setPatientId(result.getString("Pid"));
			patient.setPatientSsnid(result.getString("SSNID"));
			patient.setName(result.getString("Name"));
			patient.setAge(result.getInt("Age"));
			patient.setDateOfAdmission(result.getString("Date_Of_Admission"));
			patient.setTypeOfBed(result.getString("Type_Of_Bed"));
			patient.setAddress(result.getString("Address"));
			patient.setCity(result.getString("City"));
			patient.setState(result.getString("State"));
			patient.setStatus(result.getString("Status"));
			
			patientList.add(patient);
		}
		
		DBConnection.closeConnection(con);
		DBConnection.closeStatement(st);
		
		return patientList;
	}
	
	public PatientBean searchPatient(int patientId) throws SQLException, ClassNotFoundException
	{
		PatientBean patient = null;
		
		con = DBConnection.getConnection();
		st = con.prepareCall("{call dbo.PatientMastProc(?,?)}");
		st.setString("action", "SEARCH");
		st.setInt("Pid", patientId);
		result = st.executeQuery();
		
		while(result.next())
		{
			patient = new PatientBean();
			
			patient.setPatientId(result.getString("Pid"));
			patient.setPatientSsnid(result.getString("SSNID"));
			patient.setName(result.getString("Name"));
			patient.setAge(result.getInt("Age"));
			patient.setDateOfAdmission(result.getString("Date_Of_Admission"));
			patient.setTypeOfBed(result.getString("Type_Of_Bed"));
			patient.setAddress(result.getString("Address"));
			patient.setCity(result.getString("City"));
			patient.setState(result.getString("State"));
			patient.setStatus(result.getString("Status"));
		}
		DBConnection.closeConnection(con);
		DBConnection.closeStatement(st);
		
		return patient;
	}
	
	public int addPatient(PatientBean patient) throws SQLException, ClassNotFoundException
	{
		int patientId=0;
		
		
		con = DBConnection.getConnection();
		
		System.out.println("DAO PAtient inside");
		
		st = con.prepareCall("{call dbo.InsertPatientProc(?,?,?,?,?,?,?,?)}");
		//st.setString("action", "ADD");
		st.setInt("ssnid", Integer.parseInt(patient.getPatientSsnid()));
		st.setString("Name", patient.getName());
		st.setInt("Age", patient.getAge());
		st.setString("Date_Of_Add", patient.getDateOfAdmission());
		st.setString("Bed_Type", patient.getTypeOfBed());
		st.setString("Address", patient.getAddress());
		st.setString("City", patient.getCity());
		st.setString("State", patient.getState());
		//st.setString("Status", patient.getStatus());
		
		
		int rowstatus = st.executeUpdate();
		
		if(rowstatus>0)
		{
			st1 = con.prepareCall("{call dbo.PatientMastProc(?)}");
			st1.setString("action", "LASTPID");
			result = st1.executeQuery();
			
			while(result.next())
			{
				patientId = Integer.parseInt(result.getString("Pid"));
			}
		}
		
		DBConnection.closeConnection(con);
		DBConnection.closeStatement(st);
		DBConnection.closeStatement(st1);
		
		return patientId;
	}
	
	public PatientBean getPatientDtl(int patientId) throws ClassNotFoundException, SQLException
	{
		PatientBean patient = null;
		
		con = DBConnection.getConnection();
		st = con.prepareCall("{call dbo.PatientMastProc(?,?)}");
		st.setString("action", "SEARCH");
		st.setInt("Pid", patientId);
		result = st.executeQuery();
		
		while(result.next())
		{
			patient = new PatientBean();
			
			patient.setPatientId(result.getString("Pid"));
			patient.setPatientSsnid(result.getString("SSNID"));
			patient.setName(result.getString("Name"));
			patient.setAge(result.getInt("Age"));
			patient.setDateOfAdmission(result.getString("Date_Of_Admission"));
			patient.setTypeOfBed(result.getString("Type_Of_Bed"));
			patient.setAddress(result.getString("Address"));
			patient.setCity(result.getString("City"));
			patient.setState(result.getString("State"));
			patient.setStatus(result.getString("Status"));
		}
		DBConnection.closeConnection(con);
		DBConnection.closeStatement(st);
		
		return patient;
	}
	
	public boolean updatePatient(PatientBean patient) throws ClassNotFoundException, SQLException
	{
		con = DBConnection.getConnection();
		
		st = con.prepareCall("{call dbo.PatientMastProc(?,?,?,?,?,?,?,?,?,?)}");
		st.setString("action", "UPDATE");
		st.setInt("Pid", Integer.parseInt(patient.getPatientId()));
		st.setInt("ssnid", Integer.parseInt(patient.getPatientSsnid()));
		st.setString("Name", patient.getName());
		st.setInt("Age", patient.getAge());
		st.setString("Date_Of_Add", patient.getDateOfAdmission());
		st.setString("Bed_Type", patient.getTypeOfBed());
		st.setString("Address", patient.getAddress());
		st.setString("City", patient.getCity());
		st.setString("State", patient.getState());
		//st.setString("Status", patient.getStatus());
		
		
		int count = st.executeUpdate();
		if(count>0)
		{
			return true;
		}
		else
		{
			return false;
		}		
	}
	
	public boolean deletePatient(int patientId) throws ClassNotFoundException, SQLException, NoPatientException
	{
		con = DBConnection.getConnection();
		st = con.prepareCall("{call dbo.PatientMastProc(?,?)}");
		st.setString("action", "DELETE");
		st.setInt("Pid", patientId);
		int count = st.executeUpdate();
		
		DBConnection.closeConnection(con);
		DBConnection.closeStatement(st);
		
		if(count>0)
		{
			return true;
		}
		else 
			throw new NoPatientException("Customer does not exist");
	}
}
