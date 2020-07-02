package com.hms.PatientException;

public class NoPatientException extends Exception
{
	public NoPatientException(String message)
	{
		super(message);
	}
	
	@Override
	public String getMessage()
	{
		return super.getMessage();
	}
}
