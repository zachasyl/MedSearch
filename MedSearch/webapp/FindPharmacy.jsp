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
		<h1 class="display-4">MedSearch: Find Pharmacy</h1>
		<h2 class="lead">Look Up Pharmacy by Pharmacy Name</h2>
		<hr class="my-4">
		
		
		<form action="findpharmacy" method="post">
			<p>
				<label for="PharmacyName">pharmacy name: </label> <input id="1"
					name="username" value="${param.username}" placeholder="name"
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
					<th>Pharmacy Name</th>
					<th>User Name</th>
					<th>Open Time</th>
					<th>Close Time</th>
					<th>Phone</th>
					<th>Street 1</th>
					<th>Street 2</th>
					<th>City</th>
					<th>State</th>
					<th>Zip</th>
					
				</tr>

	<c:forEach items="${pharmacies}" var="pharmacy" >
			  
		  <tr>

	      	<td><c:out value="${pharmacy.getPharmacyName()}" /></td>
	      	<td><c:out value="${pharmacy.getUserName()}" /></td>
		  	<td> <c:out value="${pharmacy.getOpenTime()}" /></td>
		  	<td> <c:out value="${pharmacy.getCloseTime()}" /></td>
		  	<td><c:out value="${pharmacy.getPhone()}" /></td>
		  	<td> <c:out value="${pharmacy.getStreet1()}" /></td>
		  	<td><c:out value="${pharmacy.getStreet2()}" /></td>
		  	<td><c:out value="${pharmacy.getCity()}" /></td>
		  	<td><c:out value="${pharmacy.getState()}" /></td>
		  	<td><c:out value="${pharmacy.getZipcode()}" /></td>
		  
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