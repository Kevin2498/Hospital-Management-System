package com.hms.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.hms.bean.UserBean;
import com.hms.service.UserService;


public class LoginController extends HttpServlet
{
	
	private static Logger logger = Logger.getLogger(LoginController.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		logger.info("Inside doPost method of Login Controller");
		
		String source = request.getParameter("source");
		
		if(source.equals("login"))
		{
			UserBean user = new UserBean();
			UserService service = new UserService();
			PrintWriter out = response.getWriter();
						
			user.setUsername(request.getParameter("username"));
			user.setPassword(request.getParameter("password"));
			
			HttpSession session=request.getSession();
			
			try
			{
				
				logger.debug("User with Credentials:- User Name: "+user.getUsername()+" and Password: "+user.getPassword()+" is logging in into HMS");
				
				if(service.verifyUser(user))
				{
					logger.debug("Login Successful");
					
					int UG_Pid = user.getUGPid();
					session.setAttribute("username", user.getUsername());
					session.setAttribute("password", user.getPassword());
					session.setAttribute("usergroup", UG_Pid);
					
					if(UG_Pid == 1)
					{
						logger.debug("User is Admission Desk Excutive");
						
						RequestDispatcher rd=getServletContext().getRequestDispatcher("/PatientController?source=view");
						rd.forward(request, response);
					}
					else if(UG_Pid == 2)
					{
						logger.debug("User is Pharmacist");
						
						RequestDispatcher rd=getServletContext().getRequestDispatcher("/MedicineMast.jsp");
						rd.forward(request, response);
					}
					else if(UG_Pid == 3)
					{
						logger.debug("User is Diagnostic Service Executive");
						
						RequestDispatcher rd=getServletContext().getRequestDispatcher("/DiagnosticMast.jsp");
						rd.forward(request, response);
					}
					
					
					
				}
				else
				{
					logger.debug("User Credentials Wrong");
					
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Username or Password incorrect');");
					out.println("location='Login.html';");
					out.println("</script>");
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
