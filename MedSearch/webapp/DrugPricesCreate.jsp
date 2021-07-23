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
<title>Create Prescription</title>
</head>
<body class="mt-4 px-5">
	<h1>Create Prescription</h1>
	<form action="prescriptionscreate" method="post">
		<p>
			<label for="description">Description</label>
			<input id="description" name="description" value="">
		</p>
		<p>
			<label for="currency">Currency</label>
			<input id="currency" name="currency" value="">
		</p>
		<p>
			<label for="cost">Cost</label>
			<input id="cost" name="cost" value="">
		</p>
		<p>
			<label for="unit">Unit</label>
			<input id="unit" name="unit" value="">
		</p>
		<p>
			<label for="drugid">DrugId</label>
			<input id="drugid" name="drugid" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
	    <span id="successMessage"><b>${messages.success}</b></span>
	</p>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>
