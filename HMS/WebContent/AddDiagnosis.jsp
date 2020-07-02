<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.hms.bean.PatientBean" %>
<%@ page import="com.hms.bean.DiagnosisBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HMS</title>

  <!-- Bootstrap core CSS -->
  <link href="Resources/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="Resources/styleBase.css">

</head>

<body>

  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
    <div class="container">
      <a class="navbar-brand" href="#">Diagnostic Service Executive</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a class="nav-link" href="DiagnosticMast.jsp">Patient Details</a>
          </li>          
          <li class="nav-item">
            <a class="nav-link" href="Login.html">Logout</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <!-- Page Content -->
  <div class="container">
    <div class="row">
      <div class="col-lg-12 text-center">
        <h4 class="mt-5">Add Diagnosis</h4>
        
        <br/>
        <% 
        ArrayList<DiagnosisBean> diagnosisList = (ArrayList<DiagnosisBean>)request.getAttribute("diagnosisList");
        DiagnosisBean diagnosis = null;
    
    	if(diagnosisList!=null && diagnosisList.size()>0)
		{
    	%>
        <form action="http://localhost:8080/HMS/DiagnosisController" method="post">
        <input type="hidden" name="source" value="addDiagnosis">
        
        <div class="row justify-content-center">
		    <div class="col-md-6">
			    <table>
			      	<tr>
				    	<td>Patient ID : &nbsp;</td>
				    	<td><input type="text" class="form-control" name="id" value=<%= request.getParameter("patientId") %> readonly></td>
			    	</tr>
		    	</table>
		    </div>
		    
		    <div class="col-md-6">
		    	<table>
			      	<tr>
			      		<td>Patient Name : &nbsp;</td>
		    			<td><input type="text" class="form-control" name="name" value=<%= request.getParameter("patientName") %> readonly></td>
			      	</tr>
		      	</table>
		    	
		    </div>
		</div>
		
		<br/>
        
        <div class="row maintable">
        	<table class="table table-hover">
				  <thead class="thead-dark">
				    <tr>
				      <th scope="col">S.No.</th>
				      <th scope="col">Diagnosis Name</th>
				      <th scope="col">Charge</th>
				      <th scope="col">Add Item</th>
				    </tr>
				  </thead>
				  
				  <%		int size = diagnosisList.size();
			        		for(int i=0;i<diagnosisList.size();i++)
			        		{
			        			diagnosis = new DiagnosisBean();
			        			diagnosis = diagnosisList.get(i);
			        			
		          %>
		          		         		          				  
				  <tbody>
				    <tr>
				      <th scope="row"><% out.print(i+1); %></th>
				      <td><%= diagnosis.getDiagnosisName() %></td>
				      <td><%= diagnosis.getCharge() %></td>
				      <td><input type="checkbox" name="addItems" value="<% out.print(diagnosis.getDiagnosisId()); %>"/> </td>
				    </tr>
				    
				  </tbody>
				  <%	
			    		}
					 }
				  %>
			</table>
        </div>
        
        <div class="row mt-3">
		      	<div class="col-md-12">
			      	<button class="btn btn-dark btn-sm my-0" type="submit">Add Items</button> &nbsp;
			      	<button class="btn btn-dark btn-sm my-0" type="reset">Reset</button>
			    </div>
		      </div>
        
        </form>
        
        </div>
    </div>
  </div>
	
	<div class="footer">
  	<p class="footer-content">Hospital Management System</p>
  </div>
	
</body>
</html>