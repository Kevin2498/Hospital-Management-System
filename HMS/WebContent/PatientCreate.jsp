<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
          <li class="nav-item active">
            <a class="nav-link" href="PatientCreate.jsp">Create Patient</a>
          </li>
          <li class="nav-item">
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
        <h1 class="mt-5">Create Patient</h1>
        <br/>
        
        <form class="justify-content-center" action="http://localhost:8080/HMS/PatientController" method="post">
        <input type="hidden" name="source" value="create">
        	<div class="row justify-content-center">
		    <div class="col-md-6">
		      <div class="md-form">
		      
		      <table>
		      	<tr>
		      		<td>Patient SSNID : &nbsp;</td>
		      		<td><input type="text" class="form-control" placeholder="Patient SSNID" name="ssnid" maxlength="9" autofocus required></td>
		      	</tr>
		      	<tr><td><br/></td></tr>
		      	<tr>
		      		<td>Patient Name : &nbsp;</td>
		      		<td><input type="text" class="form-control" placeholder="Patient Name" name="name" required></td>
		      	</tr>
		      	<tr><td><br/></td></tr>
		      	<tr>
		      		<td>Patient Age : &nbsp;</td>
		      		<td><input type="text" class="form-control" placeholder="Patient Age" name="age" maxlength="3" required></td>
		      	</tr>
		      	<tr><td><br/></td></tr>
		      	<tr>
		      		<td>Date of Admission : &nbsp;</td>
		      		<td><input type="date" class="form-control" name="dateOfAdmission" required></td>
		      	</tr>
		      	<tr><td><br/></td></tr>
		      	<tr>
		      		<td>Type of Bed : &nbsp;</td>
		      		<td><input type="radio" class=""  name="typeOfBed" value="General"> General <input type="radio" class=""  name="typeOfBed" value="Semi"> Semi <input type="radio" class=""  name="typeOfBed" value="Single"> Single</td>
		      	</tr>
		      	<tr><td><br/></td></tr>
		      	</table>
		      </div>
		      </div>
		      
		      <div class="col-md-6">
		      <table>
		      		<tr>
		      		<td>Address : &nbsp;</td>
		      		<td><textarea rows="2" cols="20" class="form-control" placeholder="Address" name="address" maxlength="50" required></textarea></td>
			      	</tr>
			      	<tr><td><br/></td></tr>
			      	<tr>
			      		<td>City : &nbsp;</td>
			      		<td><input type="text" class="form-control" placeholder="City" name="city" maxlength="30" required></td>
			      	</tr>
			      	<tr><td><br/></td></tr>
			      	<tr>
			      		<td>State : &nbsp;</td>
			      		<td><input type="text" class="form-control" placeholder="State" name="state" maxlength="30" required></td>
			      	</tr>
			      	<!-- <tr><td><br/></td></tr>
			      	<tr>
			      		<td>Status : &nbsp;</td>
			      		<td><input type="radio" class=""  name="status" value="active"> Active <input type="radio" class=""  name="status" value="discharged"> Discharged </td>
			      	</tr> -->
		      </table>
		      	
		      	
		      </div>
		      
		      <div class="row mt-3">
		      	<div class="col-md-12">
			      	<button class="btn btn-dark btn-sm my-0" type="submit">Submit</button> &nbsp;
			      	<button class="btn btn-dark btn-sm my-0" type="reset">Reset</button>
			    </div>
		      </div>
		      
		      
		      
		    </div>
        </form>
        
        
        
        
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