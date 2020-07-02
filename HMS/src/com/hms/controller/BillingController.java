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
import com.hms.bean.MedicineBean;
import com.hms.bean.PatientBean;
import com.hms.service.BillingService;
import com.hms.service.DiagnosisService;
import com.hms.service.MedicineService;
import com.hms.service.PatientService;

public class BillingController extends HttpServlet
{
	private static Logger logger = Logger.getLogger(BillingController.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		logger.info("Inside doGet method of Billing Controller");
		
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		logger.info("Inside doPost method of Billing Controller");
		
		String source = request.getParameter("source");
		PatientBean patient = null;
		DiagnosisBean diagnosis = null;
		ArrayList<DiagnosisBean> diagnosisList = null;
		ArrayList<MedicineBean> medicineList =null;
		PatientService patientService = new PatientService();
		DiagnosisService diagnosisService = new DiagnosisService();
		MedicineService medicineService = new MedicineService();
		BillingService billingService = new BillingService();
		PrintWriter out = response.getWriter();
		
		if(source.equals("ptSearch"))
		{
			logger.info("Searching Patient for generating bill");
			try
			{
				String patientId = request.getParameter("searchValue");
				logger.debug("Patient ID = "+patientId);
				
				patient = patientService.searchPatient(Integer.parseInt(patientId));
				
				if(patient!=null)
				{
					
					diagnosisList = diagnosisService.getDiagnosisDtl(Integer.parseInt(patientId));
					medicineList = medicineService.getMedsDtl(Integer.parseInt(patientId));
					
					request.setAttribute("patientSearch", patient);
					request.setAttribute("medicineDtl", medicineList);
					request.setAttribute("diagnosisList", diagnosisList);
					RequestDispatcher rd = request.getRequestDispatcher("PatientBilling.jsp");
					rd.forward(request, response);
				}
				else
				{
					logger.debug("No patient found with ID "+patientId);
					
					out.println("<script type=\"text/javascript\">");
					out.println("alert('No Patient found with Patient ID :"+patientId+" ');");
					out.println("location='PatientBilling.jsp';");
					out.println("</script>");
				}
			}
			catch(ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
				logger.error("Exception Occured:- "+e);
			}
		}
		
		else if(source.equals("generatebill"))
		{
			String patientId = request.getParameter("patientId");
			logger.info("Generating Bill for Patient Id = "+patientId);
			
			boolean status=false;
			
			try
			{
				status = billingService.generateBill(Integer.parseInt(patientId));
				
				if(status)
				{
					logger.debug("Bill generated Successfully");
					
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Bill Generated for Patient ID :"+patientId+" ');");
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
	}
}
