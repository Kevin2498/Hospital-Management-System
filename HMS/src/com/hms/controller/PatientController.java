package com.hms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hms.PatientException.NoPatientException;
import com.hms.bean.PatientBean;
import com.hms.service.PatientService;
import com.hms.util.DateUtil;
import com.hms.PatientException.NoPatientException;

public class PatientController extends HttpServlet
{
	private static Logger logger = Logger.getLogger(PatientController.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		logger.info("Inside doGet method of Patient Controller");
		
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		logger.info("Inside doPost method of Patient Controller");
		
		String source = request.getParameter("source");
		ArrayList<PatientBean> patientList =null;
		PatientService service = new PatientService();
		PatientBean patient = null;
		PrintWriter out = response.getWriter();
		
		if(source.equals("view"))
		{
			logger.debug("View Patient List");
			
			try
			{
				patientList = service.viewPatient();
				if(patientList!=null && patientList.size()>0)
	        	{
					request.setAttribute("patientList", patientList);
					RequestDispatcher rd = request.getRequestDispatcher("PatientView.jsp");
					rd.forward(request, response);
	        	}
				else
				{
					out.println("<script type=\"text/javascript\">");
					out.println("location='PatientView.jsp';");
					out.println("</script>");
				}
			}
			catch(ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
				logger.error("Exception Occured:- "+e);
			}
			
		}
		else if(source.equals("search"))
		{
			logger.info("Searching Patient");
			
			try
			{
				String patientId = request.getParameter("searchValue");
				logger.debug("Patient ID = "+patientId);
				
				patient = service.searchPatient(Integer.parseInt(patientId));
				
				
				if(patient!=null)
				{
					System.out.println(patient.getDateOfAdmission());
					request.setAttribute("patientSearch", patient);
					RequestDispatcher rd = request.getRequestDispatcher("PatientSearch.jsp");
					rd.forward(request, response);
				}
				else
				{
					out.println("<script type=\"text/javascript\">");
					out.println("alert('No Patient found with Patient ID :"+patientId+" ');");
					out.println("location='PatientSearch.jsp';");
					out.println("</script>");
				}
			}
			catch(ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
				logger.error("Exception Occured:- "+e);
			}
		}
		
		else if(source.equals("create"))
		{
			logger.info("Creating Patient");
			
			patient = new PatientBean();
			patient.setPatientSsnid(request.getParameter("ssnid"));
			patient.setName(request.getParameter("name"));
			patient.setAge(Integer.parseInt(request.getParameter("age")));
			patient.setDateOfAdmission(request.getParameter("dateOfAdmission"));
			patient.setTypeOfBed(request.getParameter("typeOfBed"));
			patient.setAddress(request.getParameter("address"));
			patient.setCity(request.getParameter("city"));
			patient.setState(request.getParameter("state"));
			/*String status = request.getParameter("status");
			if(status.equals("active"))
			{
				patient.setStatus("A");
			}
			else
				patient.setStatus("D");*/
			
			
			try
			{
				int patientId = service.addPatient(patient);
				
				if(patientId!=0)
				{
					logger.debug("Patient Created Successfully");
					
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Patient Created with Patient ID :"+patientId+" ');");
					out.println("location='http://localhost:8080/HMS/PatientController?source=view';");
					out.println("</script>");
				}
			}
			catch(ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
				logger.error("Exception Occured:- "+e);
			}
		}
		else if(source.equals("searchUpdate"))
		{			
			String patientId = request.getParameter("searchValue");
			logger.info("Updating Patient with ID = "+patientId);
			
			try
			{
				patient = service.getPatientDtl(Integer.parseInt(patientId));
				
				if(patient!=null)
				{
					request.setAttribute("patientSearch", patient);
					
					RequestDispatcher rd = request.getRequestDispatcher("PatientUpdate.jsp");
					rd.forward(request, response);
				}
				else
				{
					logger.debug("No Patient found with ID = "+patientId);
					
					out.println("<script type=\"text/javascript\">");
					out.println("alert('No Patient found with Patient ID :"+patientId+" ');");
					out.println("location='PatientUpdate.jsp';");
					out.println("</script>");
				}
				
				
			}
			catch(ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
				logger.error("Exception Occured:- "+e);
			}
		}
		
		else if(source.equals("update"))
		{			
			patient = new PatientBean();
			patient.setPatientId(request.getParameter("id"));
			patient.setPatientSsnid(request.getParameter("ssnid"));
			patient.setName(request.getParameter("name"));
			patient.setAge(Integer.parseInt(request.getParameter("age")));
			patient.setDateOfAdmission(request.getParameter("dateOfAdmission"));
			patient.setTypeOfBed(request.getParameter("typeOfBed"));
			patient.setAddress(request.getParameter("address"));
			patient.setCity(request.getParameter("city"));
			patient.setState(request.getParameter("state"));
			/*String status = request.getParameter("status");
			if(status.equals("active"))
			{
				patient.setStatus("A");
			}
			else
				patient.setStatus("D");*/
			
			try
			{
				boolean flag = service.updatePatient(patient);
				if(flag)
				{
					logger.debug("Patient Updated");
					
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Patient Updated with Patient ID :"+patient.getPatientId()+" ');");
					out.println("location='http://localhost:8080/HMS/PatientController?source=view';");
					out.println("</script>");
				}
				else
				{
					out.println("<script type=\"text/javascript\">");
					out.println("alert('No Patient found with Patient ID :"+patient.getPatientId()+" ');");
					out.println("location='PatientUpdate.jsp';");
					out.println("</script>");
				}
				
			}
			catch(ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
				logger.error("Exception Occured:- "+e);
			}
		}
		
		else if(source.equals("delete"))
		{
			String patientId = request.getParameter("deleteValue");
			logger.info("Deleting Patient with ID = "+patientId);
			
			try
			{
				boolean status = service.deletePatient(Integer.parseInt(patientId));
				if(status)
				{
					logger.debug("Patient deleted successfull");
					
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Patient Deleted with Patient ID :"+patientId+" ');");
					out.println("location='http://localhost:8080/HMS/PatientController?source=view';");
					out.println("</script>");
				}
			}
			catch(ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
				logger.error("Exception Occured:- "+e);
			}
			catch(NoPatientException e)
			{				
				logger.error("Exception Occured:- "+e);
				out.println("<script type=\"text/javascript\">");
				out.println("alert('No Patient found with Patient ID :"+patientId+" ');");
				out.println("location='PatientDelete.jsp';");
				out.println("</script>");
			}
		}
	}
}
