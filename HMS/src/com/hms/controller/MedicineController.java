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

import com.hms.bean.MedicineBean;
import com.hms.bean.PatientBean;
import com.hms.service.MedicineService;
import com.hms.service.PatientService;

public class MedicineController extends HttpServlet
{
	private static Logger logger = Logger.getLogger(MedicineController.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		logger.info("Inside doGet method of Medicine Controller");
		
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		logger.info("Inside doPost method of Medicine Controller");
		
		String source = request.getParameter("source");
		PatientBean patient = null;
		MedicineBean medicine = null;
		ArrayList<MedicineBean> medicineList =null;
		PatientService patientService = new PatientService();
		MedicineService medicineService = new MedicineService();
		PrintWriter out = response.getWriter();
		
		if(source.equals("ptSearch"))
		{
			
			logger.info("Searching Patient for adding medicine");
			
			try
			{
				String patientId = request.getParameter("searchValue");
				logger.debug("Patient ID = "+patientId);
				
				patient = patientService.searchPatient(Integer.parseInt(patientId));
				
				if(patient!=null)
				{
					
					medicineList = medicineService.getMedsDtl(Integer.parseInt(patientId));
										
					request.setAttribute("patientSearch", patient);
					request.setAttribute("medicineDtl", medicineList);
					RequestDispatcher rd = request.getRequestDispatcher("MedicineMast.jsp");
					rd.forward(request, response);
				}
				else
				{
					logger.debug("No patient found with ID "+patientId);
					
					out.println("<script type=\"text/javascript\">");
					out.println("alert('No Patient found with Patient ID :"+patientId+" ');");
					out.println("location='MedicineMast.jsp';");
					out.println("</script>");
				}
			}
			catch(ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
				logger.error("Exception Occured:- "+e);
			}
		}
		
		else if(source.equals("issuemeds"))
		{
			String patientId = request.getParameter("patientId");
			String patientName = request.getParameter("patientName");
			
			logger.info("Adding Medicine for Patient ID = "+patientId);
			
			try
			{
				medicineList = medicineService.viewMeds();
				
				if(medicineList!=null && medicineList.size()>0)
				{
					request.setAttribute("medicineList", medicineList);
					request.setAttribute("patientId", patientId);
					request.setAttribute("patientName", patientName);
					RequestDispatcher rd = request.getRequestDispatcher("IssueMedicine.jsp");
					rd.forward(request, response);
				}
				else
				{
					logger.debug("No medicine found");
					
					out.println("<script type=\"text/javascript\">");
					out.println("location='MedicineMast.jsp';");
					out.println("</script>");
				}
				
			}
			catch(ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
				logger.error("Exception Occured:- "+e);
			}
		}
		else if(source.equals("addMeds"))
		{
			String[] medId = request.getParameterValues("addItems");
			String patientId = request.getParameter("id");
			String[] quantity = request.getParameterValues("quantity");
			boolean status = false;
						
			ArrayList<String> quantityList = new ArrayList<String>();
			for (String s : quantity)
			    if (!s.equals(""))
			    	quantityList.add(s);
			
			quantity = quantityList.toArray(new String[quantityList.size()]);
			
			//System.out.println("Size of quantity list = "+quantityList.size());
			
			
			//System.out.println("Updated size = "+medid+" "+quant);
			
			if(medId!=null)
			{
				try
				{
					status = medicineService.addMeds(medId,quantity,patientId);
					
					if(status)
					{
						logger.debug("Medicine Added Successfully");
						
						out.println("<script type=\"text/javascript\">");
						out.println("alert('Medicines added');");
						out.println("location='MedicineMast.jsp';");
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
				logger.debug("No Medicine Added");
				
				out.println("<script type=\"text/javascript\">");
				out.println("alert('No Medicines added');");
				out.println("location='MedicineMast.jsp';");
				out.println("</script>");
			}
		}
	}
}
