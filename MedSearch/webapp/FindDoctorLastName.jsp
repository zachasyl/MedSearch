<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<title>Find User</title>
</head>
<body class="mt-4 px-5">
	<div>
		<h1 class="display-4">MedSearch: Find Doctor</h1>
		<h2 class="lead">Look Up Doctor by Last Name</h2>
		<hr class="my-4">
		
		
		<form action="finddoctorlastname" method="post">
			<p>
				<label for="DoctorLastName">last name: </label> <input id="1"
					name="lastname" value="${param.lastname}" placeholder="Dr. xxxx"
					required>
			</p>
			<p>
				<button class="btn btn-primary" type="submit">Search</button>
			</p>
		</form>
	</div>
	<br />

	<div>
		<h2 class="display-5">Results</h2>
		<c:if test="${success != null}">
		<br>
		<div class="alert alert-success" id="successMessage">
			${success}</div>
		<br>
		</c:if>
		<table class="table" border="1">
				<tr>
					<th>UserName</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Phone</th>
					<th>Street 1</th>
					<th>Street 2</th>
					<th>City</th>
					<th>State</th>
					<th>Zip</th>
				</tr>
				
		<c:forEach items="${doctors}" var="doctor" >
		<tr>

	   	  <td><c:out value="${doctor.getUserName()}" /></td>
		  <td> <c:out value="${doctor.getFirstName()}" /></td>
		  <td> <c:out value="${doctor.getLastName()}" /></td>
		  <td> <c:out value="${doctor.getPhone()}" /></td>
		  <td> <c:out value="${doctor.getStreet1()}" /></td>
		  <td> <c:out value="${doctor.getStreet2()}" /></td>
		  <td> <c:out value="${doctor.getCity()}" /></td>
		  <td> <c:out value="${doctor.getState()}" /></td>
		  <td> <c:out value="${doctor.getZipcode()}" /></td>
		  
		</tr>
	    </c:forEach>
  
			 
			
		</table>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>