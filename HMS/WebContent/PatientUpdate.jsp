<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.hms.bean.PatientBean" %>
<%@ page import="com.hms.util.DateUtil" %>
<%@ page import="java.util.Date" %>

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
          <li class="nav-item active">
            <a class="nav-link" href="PatientUpdate.jsp">Update Patient</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="PatientDelete.jsp">Delete Patient</a>
          </li>
          <li class="nav-item">
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
  <div class="container">
    <div class="row">
      <div class="col-lg-12 text-center">
        <h1 class="mt-5">Update Patient</h1>
        
        <br/>
        <div class="row justify-content-center">
        	<form class="form-inline" action="http://localhost:8080/HMS/PatientController" method="post">
        		<input type="hidden" name="source" value="searchUpdate">
				Patient ID : &nbsp;
			  <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" name="searchValue" maxlength="9" autofocus required>
			  <button class="btn btn-dark btn-sm my-0" type="submit">Search</button>
			</form>
        </div>
        <br/>
        <br/>
        
        <% PatientBean patient = (PatientBean)request.getAttribute("patientSearch"); 
	        if(patient!=null)
	    	{
        %>
        
        <form class="justify-content-center" action="http://localhost:8080/HMS/PatientController" method="post">
        <input type="hidden" name="source" value="update">
        
        
        	<div class="row justify-content-center">
		    <div class="col-md-6">
		      <div class="md-form">
		      
		      
		      
		      <table>
		      	<tr>
		      		<td>Patient ID : &nbsp;</td>
		      		<td><input type="text" class="form-control" placeholder="Patient ID" name="id" maxlength="9" value="<%= patient.getPatientId() %>" readonly></td>
		      	</tr>
		      	<tr><td><br/></td></tr>
		      	<tr>
		      		<td>Patient SSNID : &nbsp;</td>
		      		<td><input type="text" class="form-control" placeholder="Patient SSNID" name="ssnid" maxlength="9" value="<%= patient.getPatientSsnid() %>" autofocus required></td>
		      	</tr>
		      	<tr><td><br/></td></tr>
		      	<tr>
		      		<td>Patient Name : &nbsp;</td>
		      		<td><input type="text" class="form-control" placeholder="Patient Name" name="name" value="<%= patient.getName()  %>" required></td>
		      	</tr>
		      	<tr><td><br/></td></tr>
		      	<tr>
		      		<td>Patient Age : &nbsp;</td>
		      		<td><input type="text" class="form-control" placeholder="Patient Age" name="age" maxlength="3" value="<%= patient.getAge()  %>" required></td>
		      	</tr>
		      	<tr><td><br/></td></tr>
		      			      	
		      	<tr>
		      		<td>Date of Admission (MM/DD/YYY) : &nbsp;</td>
		      		<td><input type="text" class="form-control" name="dateOfAdmission" value="<%= patient.getDateOfAdmission()  %>" required></td>
		      	</tr>
		      	<tr><td><br/></td></tr>
		      	
		      	</table>
		      </div>
		      </div>
		      
		      <div class="col-md-6">
		      <table>
		      		<tr>
			      		<td>Type of Bed : &nbsp;</td>
			      		<td><input type="radio" class=""  name="typeOfBed" value="General" <% if(patient.getTypeOfBed().equals("General")) { %> checked <% } %>> General <input type="radio" class=""  name="typeOfBed" value="Semi" <% if(patient.getTypeOfBed().equals("Semi")) { %> checked <% } %>> Semi <input type="radio" class=""  name="typeOfBed" value="Single" <% if(patient.getTypeOfBed().equals("Single")) { %> checked <% } %>> Single</td>
			      	</tr>
			      	<tr><td><br/></td></tr>
		      		<tr>
		      		<td>Address : &nbsp;</td>
		      		<td><textarea rows="2" cols="20" class="form-control" placeholder="Address" name="address" maxlength="50" required> <%= patient.getAddress()  %></textarea></td>
			      	</tr>
			      	<tr><td><br/></td></tr>
			      	<tr>
			      		<td>City : &nbsp;</td>
			      		<td><input type="text" class="form-control" placeholder="City" name="city" maxlength="30" value="<%= patient.getCity()  %>" required></td>
			      	</tr>
			      	<tr><td><br/></td></tr>
			      	<tr>
			      		<td>State : &nbsp;</td>
			      		<td><input type="text" class="form-control" placeholder="State" name="state" maxlength="30" value="<%= patient.getState()  %>" required></td>
			      	</tr>
			      	<%-- <tr><td><br/></td></tr>
			      	<tr>
			      		<td>Status : &nbsp;</td>
			      		<td><input type="radio" class=""  name="status" value="active" <% if(patient.getStatus().equals("A")) { %> checked <% } %>> Active <input type="radio" class=""  name="status" value="discharged" <% if(patient.getStatus().equals("D")) { %> checked <% } %>> Discharged </td>
			      	</tr> --%>
		      </table>
		      	
		      	
		      </div>
		      
		      <div class="row mt-3">
		      	<div class="col-md-12">
			      	<button class="btn btn-dark btn-sm my-0" type="submit">Update</button> &nbsp;
<!-- 			      	<button class="btn btn-dark btn-sm my-0" type="reset">Reset</button> -->
			    </div>
		      </div>
		      
		      
		      
		    </div>
        </form>
        
        <%} %>
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