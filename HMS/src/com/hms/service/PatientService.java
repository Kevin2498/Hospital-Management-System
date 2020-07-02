package com.hms.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.hms.PatientException.NoPatientException;
import com.hms.bean.PatientBean;
import com.hms.dao.PatientDao;

public class PatientService 
{
	PatientDao cdao = new PatientDao();
	
	public ArrayList<PatientBean> viewPatient() throws ClassNotFoundException, SQLException
	{
		return cdao.viewPatient();
	}
	
	public PatientBean searchPatient(int patientId) throws ClassNotFoundException, SQLException
	{
		return cdao.searchPatient(patientId);
	}
	
	public int addPatient(PatientBean patient) throws ClassNotFoundException, SQLException
	{
		return cdao.addPatient(patient);
	}
	
	public PatientBean getPatientDtl(int patientId) throws ClassNotFoundException, SQLException
	{
		return cdao.getPatientDtl(patientId);
	}
	
	public boolean updatePatient(PatientBean patient) throws ClassNotFoundException, SQLException
	{
		return cdao.updatePatient(patient);
	}
	
	public boolean deletePatient(int patientId) throws ClassNotFoundException, SQLException, NoPatientException
	{
		return cdao.deletePatient(patientId);
	}
}
