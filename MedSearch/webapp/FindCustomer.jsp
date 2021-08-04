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
		<h1 class="display-4">MedSearch: Find Customer</h1>
		<h2 class="lead">Look Up Customer by UserName</h2>
		<hr class="my-4">
		<form action="findcustomer" method="post">
			<p>
				<label for="username">user name: </label> <input id="1"
					name="username" value="${param.username}" placeholder="username"
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
			<thead>
				<tr>
					<th>UserName</th>
					<th>FirstName</th>
					<th>LastName</th>
					<th>Age</th>
					<th>Sex</th>
				

					
				</tr>
			</thead>
			<tbody>
				<c:if test="${customer!=null}">
					<tr>
						<td>${customer.getUserName()}</td>
						<td>${customer.getFirstName()}</td>
						<td>${customer.getLastName()}</td>
						<td>${customer.getAge()}</td>
						<td>${customer.getSex()}</td>
					

					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>