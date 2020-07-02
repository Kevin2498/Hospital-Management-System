package com.hms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hms.bean.DiagnosisBean;
import com.hms.bean.PatientBean;
import com.hms.service.DiagnosisService;
import com.hms.service.PatientService;

public class DiagnosisController extends HttpServlet
{
	private static Logger logger = Logger.getLogger(DiagnosisController.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		logger.info("Inside doGet method of Diagnosis Controller");
		
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		logger.info("Inside doPost method of Diagnosis Controller");
		
		String source = request.getParameter("source");
		PatientBean patient = null;
		DiagnosisBean diagnosis = null;
		ArrayList<DiagnosisBean> diagnosisList = null;
		PatientService patientService = new PatientService();
		DiagnosisService diagnosisService = new DiagnosisService();
		PrintWriter out = response.getWriter();
		
		if(source.equals("ptSearch"))
		{
			logger.info("Searching Patient for adding diagnosis");
			
			try
			{
				String patientId = request.getParameter("searchValue");
				logger.debug("Patient ID = "+patientId);
				
				patient = patientService.searchPatient(Integer.parseInt(patientId));
				
				if(patient!=null)
				{
					diagnosisList = diagnosisService.getDiagnosisDtl(Integer.parseInt(patientId));
					
					request.setAttribute("patientSearch", patient);
					request.setAttribute("diagnosisList", diagnosisList);
					RequestDispatcher rd = request.getRequestDispatcher("DiagnosticMast.jsp");
					rd.forward(request, response);
				}
				else
				{
					logger.debug("No patient found with ID "+patientId);
					
					out.println("<script type=\"text/javascript\">");
					out.println("alert('No Patient found with Patient ID :"+patientId+" ');");
					out.println("location='DiagnosticMast.jsp';");
					out.println("</script>");
				}
			}
			catch(ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
				logger.error("Exception Occured:- "+e);
			}
		}
		else if(source.equals("issuediag"))
		{
			String patientId = request.getParameter("patientId");
			String patientName = request.getParameter("patientName");
			
			logger.info("Adding Diagnosis for Patient ID = "+patientId);
			
			try
			{
				diagnosisList = diagnosisService.bindDiagnosisDtl(Integer.parseInt(patientId));
				
				if(diagnosisList!=null && diagnosisList.size()>0)
				{
					request.setAttribute("diagnosisList", diagnosisList);
					request.setAttribute("patientId", patientId);
					request.setAttribute("patientName", patientName);
					RequestDispatcher rd = request.getRequestDispatcher("AddDiagnosis.jsp");
					rd.forward(request, response);
				}
				else
				{
					logger.debug("No Diagnosis found");
					
					out.println("<script type=\"text/javascript\">");
					out.println("location='DiagnosticMast.jsp';");
					out.println("</script>");
				}
				
			}
			catch(ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
				logger.error("Exception Occured:- "+e);
			}
		}
		
		else if(source.equals("addDiagnosis"))
		{
			String[] diagnosisId = request.getParameterValues("addItems");
			String patientId = request.getParameter("id");
			boolean status = false;
			
			
			if(diagnosisId!=null)
			{				
				try
				{
					status = diagnosisService.addDiagnosis(Integer.parseInt(patientId),diagnosisId);
					
					if(status)
					{
						logger.debug("Diagnosis Added Successfully");
						
						out.println("<script type=\"text/javascript\">");
						out.println("alert('Diagnosis added');");
						out.println("location='DiagnosticMast.jsp';");
						out.println("</script>");
					}
				}
				catch(ClassNotFoundException | SQLException e)
				{
					e.printStackTrace();
					logger.error("Exception Occured:- "+e);
				}
				
			}
			else
			{
				logger.debug("No Diagnosis Added");
				
				out.println("<script type=\"text/javascript\">");
				out.println("alert('No Diagnosis added');");
				out.println("location='DiagnosticMast.jsp';");
				out.println("</script>");
			}
		}
	}
}
