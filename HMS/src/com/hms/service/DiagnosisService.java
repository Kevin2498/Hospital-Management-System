package com.hms.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.hms.bean.DiagnosisBean;
import com.hms.dao.DiagnosisDao;

public class DiagnosisService 
{
	DiagnosisDao cdao = new DiagnosisDao();
	
	public ArrayList<DiagnosisBean> getDiagnosisDtl(int patientId) throws ClassNotFoundException, SQLException
	{
		return cdao.getDiagnosisDtl(patientId);
	}
	
	public ArrayList<DiagnosisBean> bindDiagnosisDtl (int patientId) throws ClassNotFoundException, SQLException
	{
		return cdao.bindDiagnosisDtl(patientId);
	}
	
	public boolean addDiagnosis (int patientId, String[] diagnosisId) throws ClassNotFoundException, SQLException
	{
		return cdao.addDiagnosis(patientId,diagnosisId);
	}
}
