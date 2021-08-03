<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<title>Create Drug Interaction</title>
</head>
<body class="mt-4 px-5">
	<div>
		<h1 class="display-4">MedSearch: Create Drug Interaction</h1>
		<h2 class="lead">Create a Drug Interaction between two drugs with
			their IDs</h2>
		<hr class="my-4">
		<form action="reactionscreate" method="post">
			<p>
				<label for="drugIdA">Drug ID A: </label> <input id="drugIdA"
					name="drugIdA" value="${fn:escapeXml(param.drugIdA)}">
			</p>
			<p>
				<label for="drugIdB">Drug ID B: </label> <input id="drugIdB"
					name="drugIdB" value="${fn:escapeXml(param.drugIdB)}">
			</p>
			<p>
				<label for="description">Description: </label> <input
					id="description" name="description"
					value="${fn:escapeXml(param.description)}">
			</p>
			<p>
				<button class="btn btn-primary" type="submit">Create</button>
			</p>
		</form>
	</div>
	<br />
	<c:if test="${messages.fail != null}">
		<br>
		<div class="alert alert-danger" id="failMessage">${messages.fail}</div>
		<br>
	</c:if>
	<c:if test="${messages.success != null}">
		<br>
		<div class="alert alert-success" id="successMessage">${messages.success}</div>
		<br>
	</c:if>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>
