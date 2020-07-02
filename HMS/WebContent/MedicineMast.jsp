<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.hms.bean.PatientBean" %>
<%@ page import="com.hms.bean.MedicineBean" %>
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
      <a class="navbar-brand" href="#">Pharmacist</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item active">
            <a class="nav-link" href="MedicineMast.jsp">Patient Details</a>
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
        <h4 class="mt-5">Get Patient Details</h4>
        
        <br/>
        <div class="row justify-content-center">
        	<form class="form-inline" action="http://localhost:8080/HMS/MedicineController" method="post">
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
				      <th scope="col">Date of Admission</th>
				      <th scope="col">Type of Bed</th>
				      <th scope="col">Address</th>
				      <th scope="col">City</th>
				      <th scope="col">State</th>
				      <th scope="col">Status</th>
				    </tr>
				  </thead>
				  
				  <tbody>
				    <tr>
				      <th scope="row">1</th>
				      <td><%= patient.getPatientId() %></td>
				      <td><%= patient.getPatientSsnid() %></td>
				      <td><%= patient.getName() %></td>
				      <td><%= patient.getAge() %></td>
 				      <td><%= patient.getDateOfAdmission() %></td>
				      <td><%= patient.getTypeOfBed() %></td>
				      <td><%= patient.getAddress() %></td>
				      <td><%= patient.getCity() %></td>
				      <td><%= patient.getState() %></td>
				      <% if(patient.getStatus().equals("A")){ %>
				      <td>Active</td>
				      <% } else { %>
				      <td>Discharged</td>
				      <% } %>
				    </tr>
				    
				  </tbody>
			</table>
        </div>
        
        <h4 class="">Medicine Issued</h4>
        
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
			        		for(int i=0;i<medicineList.size();i++)
			        		{
			        			medicine = new MedicineBean();
			        			medicine = medicineList.get(i);
			        		
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
        
        <div class="row justify-content-center">
        	<form class="form-inline" action="http://localhost:8080/HMS/MedicineController" method="post">
        		<input type="hidden" name="source" value="issuemeds">
        		<input type="hidden" name="patientId" value=<%= patient.getPatientId() %>>
        		<input type="hidden" name="patientName" value=<%= patient.getName() %>>
			  <button class="btn btn-dark btn-sm my-0" type="submit">Issue Medicine</button>
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