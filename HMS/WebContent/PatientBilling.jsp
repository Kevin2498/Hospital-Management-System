<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.time.Period" %> 
<%@ page import="com.hms.bean.PatientBean" %>
<%@ page import="com.hms.bean.MedicineBean" %>
<%@ page import="com.hms.bean.DiagnosisBean" %>
<%@ page import="com.hms.util.DateUtil" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HMS</title>

  <!-- Bootstrap core CSS -->
  <link href="Resources/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="Resources/styleBase.css">
  <script src="mainscript.js"></script>
</head>

<body>

  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
    <div class="container">
      <a class="navbar-brand" href="#">Admission Desk Executive</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a class="nav-link" href="http://localhost:8080/HMS/PatientController?source=view">View Patients</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="PatientSearch.jsp">Search Patient</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="PatientCreate.jsp">Create Patient</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="PatientUpdate.jsp">Update Patient</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="PatientDelete.jsp">Delete Patient</a>
          </li>
          <li class="nav-item active">
            <a class="nav-link" href="PatientBilling.jsp">Patient Billing</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="Login.html">Logout</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <!-- Page Content -->
  <div class="container maintable">
    <div class="row">
      <div class="col-lg-12 text-center">
        <h4 class="mt-5">Patient Billing</h4>
        
        <br/>
        <div class="row justify-content-center">
        	<form class="form-inline" action="http://localhost:8080/HMS/BillingController" method="post">
        		<input type="hidden" name="source" value="ptSearch">
				Patient ID : &nbsp;
			  <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" name="searchValue" maxlength="9" autofocus required>
			  <button class="btn btn-dark btn-sm my-0" type="submit">Search</button>
			</form>
        </div>
        
        
        <% 
        PatientBean patient = (PatientBean)request.getAttribute("patientSearch");
    	if(patient!=null)
    	{
    		int totalDiagnostic = 0;
    		int totalmeds = 0;
    		int roomBill=0;
    		int grandTotal=0;
    		
    			SimpleDateFormat AppDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    			
    			Date doj = DateUtil.convertStringToDate(patient.getDateOfAdmission(), "dd/MM/yyyy");
    			Date dod = new java.util.Date();
    			
    			long days = dod.getTime() - doj.getTime();
    			
    			int diffDays = (int) (days / (24 * 60 * 60 * 1000));
    			
    			
    			
    			if(patient.getTypeOfBed().equals("General"))
    			{
    				roomBill = diffDays*2000;
    			}
    			else if(patient.getTypeOfBed().equals("Semi"))
    			{
    				roomBill = diffDays*4000;
    			}
    			else if(patient.getTypeOfBed().equals("Single"))
    			{
    				roomBill = diffDays*8000;
    			}
    	%>
        <br/>
        <div class="row">
        	<table class="table table-hover">
				  <thead class="thead-dark">
				    <tr>
				      <th scope="col">S.No.</th>
				      <th scope="col">ID</th>
				      <th scope="col">SSNID</th>
				      <th scope="col">Name</th>
				      <th scope="col">Age</th>
				      <th scope="col">Address</th>
				      <th scope="col">Date of Admission</th>
				      <th scope="col">Date of Discharge</th>
				      <th scope="col">Type of Bed</th>
				    </tr>
				  </thead>
				  
				  <tbody>
				    <tr>
				      <th scope="row">1</th>
				      <td><%= patient.getPatientId() %></td>
				      <td><%= patient.getPatientSsnid() %></td>
				      <td><%= patient.getName() %></td>
				      <td><%= patient.getAge() %></td>
				      <td><%= patient.getAddress() %></td>
 				      <td><%= patient.getDateOfAdmission() %></td>
 				      <td><% out.println(AppDateFormat.format(new java.util.Date())); %></td>
				      <td><%= patient.getTypeOfBed() %></td>
				    </tr>
				    
				  </tbody>
			</table>
        </div>
        
        <div class="row">
        	<div class="col-md-6 text-right">
        		<label><b>No. of Days : <%= diffDays %></b></label>
        	</div>
        	<div class="col-md-6 text-right">
        		<label><b>Bill for Room : <span>&#8377;</span><%= roomBill %></b></label>
        	</div>
        </div>
        
        <br/>
        
        <h4 class="">Pharmacy Charges</h4>
        
        <% 
        ArrayList<MedicineBean> medicineList = (ArrayList<MedicineBean>)request.getAttribute("medicineDtl");
    	MedicineBean medicine = null;
    
    	if(medicineList!=null && medicineList.size()>0)
		{
    	%>
        
        <div class="row">
        	<table class="table table-hover">
				  <thead class="thead-dark">
				    <tr>
				      <th scope="col">S.No.</th>
				      <th scope="col">Medicine Name</th>
				      <th scope="col">Quantity</th>
				      <th scope="col">Rate</th>
				      <th scope="col">Amount</th>
				    </tr>
				  </thead>
				  
				  <%
				  			int[] medsTotal = new int[medicineList.size()];		
				  
			        		for(int i=0;i<medicineList.size();i++)
			        		{
			        			medicine = new MedicineBean();
			        			medicine = medicineList.get(i);
			        		
			        			medsTotal[i] = medicine.getAmount();
		          %>
				  
				  <tbody>
				    <tr>
				      <th scope="row"><% out.print(i+1); %></th>
				      <td><%= medicine.getMedicineName() %></td>
				      <td><%= medicine.getQuantity() %></td>
				      <td><%= medicine.getRate() %></td>
				      <td><%= medicine.getAmount() %></td>
				    </tr>
				    
				  </tbody>
				  <% } %>
			</table>
        </div>
        
        <%	
    	
	    	for(int i=0;i<medsTotal.length;i++)
	    	{
	    		totalmeds += medsTotal[i]; 
	    	} 
        %>
        
        <div class="row">
        	<div class="col-md-11 text-right">
        		<label><b>Bill for Pharmacy : <span>&#8377;</span><%= totalmeds %></b></label>
        	</div>
        </div>
        
        <br/>
        <%	
		}
    	else {
		%>
		
		<div class="col-lg-12 text-center">
        <h5 class="mt-5">No Record Found</h5>
        </div>
        <br/>
        <%	
    		}
		%>
        
        <h4 class="">Diagnostics Charges</h4>
        
        <% 
        ArrayList<DiagnosisBean> diagnosisList = (ArrayList<DiagnosisBean>)request.getAttribute("diagnosisList");
        DiagnosisBean diagnosis = null;
    
    	if(diagnosisList!=null && diagnosisList.size()>0)
		{
    	%>
        
        <div class="row">
        	<table class="table table-hover">
				  <thead class="thead-dark">
				    <tr>
				      <th scope="col">S.No.</th>
				      <th scope="col">Diagnosis Name</th>
				      <th scope="col">Charge</th>
				    </tr>
				  </thead>
				  
				  <%
				  			int[] diagnosticTotal = new int[diagnosisList.size()];			
				  
			        		for(int i=0;i<diagnosisList.size();i++)
			        		{
			        			diagnosis = new DiagnosisBean();
			        			diagnosis = diagnosisList.get(i);
			        		
			        			diagnosticTotal[i] = diagnosis.getCharge();
		          %>
				  
				  <tbody>
				    <tr>
				      <th scope="row"><% out.print(i+1); %></th>
				      <td><%= diagnosis.getDiagnosisName() %></td>
				      <td><%= diagnosis.getCharge() %></td>
				    </tr>
				    
				  </tbody>
				  <% } %>
			</table>
        </div>
        
        <% 
        	
        	
        	for(int i=0;i<diagnosticTotal.length;i++)
        	{
        		totalDiagnostic += diagnosticTotal[i]; 
        	}
        %>
        
        <div class="row">
        	<div class="col-md-11 text-right">
        		<label><b>Bill for Diagnostics : <span>&#8377;</span><%= totalDiagnostic %></b></label>
        	</div>
        </div>
        
        <br/>
        <br/>
        
        <%	
		}
    	else {
		%>
		
		<div class="col-lg-12 text-center">
        <h5 class="mt-5">No Record Found</h5>
        </div>
        <br/>
        
        <%	
    		} 
    		
    		grandTotal = roomBill+totalmeds+totalDiagnostic;
		%>
		
		<div class="row justify-content-center">
        		<label><h5><b>Grand Total : <span>&#8377;</span><%= grandTotal %></b></h5></label>
        </div>
        <br/>
		
			<div class="row justify-content-center maintable">
	        	<form class="form-inline" action="http://localhost:8080/HMS/BillingController" method="post">
	        		<input type="hidden" name="source" value="generatebill">
	        		<input type="hidden" name="patientId" value=<%= patient.getPatientId() %>>
				  <button class="btn btn-dark btn-sm my-0" type="submit">Generate Bill</button>
				</form>
        	</div>
		
		<%	
    		}
		%>
		
		
        
        <!-- <p class="lead">Complete with pre-defined file paths and responsive navigation!</p>
        <ul class="list-unstyled">
          <li>Bootstrap 4.5.0</li>
          <li>jQuery 3.5.1</li>
        </ul> -->
      </div>
    </div>
  </div>
  

  <div class="footer">
  	<p class="footer-content">Hospital Management System</p>
  </div>

</body>
</html>