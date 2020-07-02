package com.hms.bean;

public class DiagnosisBean 
{
	private int diagnosisId;
	private String diagnosisName;
	private int charge;
	
	public int getDiagnosisId()
	{
		return diagnosisId;
	}
	public void setDiagnosisId(int diagnosisId)
	{
		this.diagnosisId = diagnosisId;
	}
	
	public String getDiagnosisName()
	{
		return diagnosisName;
	}
	public void setDiagnosisName(String diagnosisName)
	{
		this.diagnosisName = diagnosisName;
	}
	
	public int getCharge()
	{
		return charge;
	}
	public void setCharge(int charge)
	{
		this.charge = charge;
	}
}
