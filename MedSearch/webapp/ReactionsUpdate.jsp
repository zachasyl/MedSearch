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
<title>Update Drug Interactions</title>
</head>
<body class="mt-4 px-5">
	<div>
		<h1 class="display-4">MedSearch: Update Drug Interactions</h1>
		<h2 class="lead">Update the Description</h2>
		<hr class="my-4">
		<form action="reactionsupdate" method="post">
			<p>
				<label for="reactionId">Reaction ID: </label> <input id="reactionId"
					name="reactionId" value="${fn:escapeXml(param.reactionId)}">
			</p>
			<p>
				<label for="newDescription">New Description: </label> <input
					id="newDescription" name="newDescription"
					value="${fn:escapeXml(param.newDescription)}">
			</p>
			<p>
				<button class="btn btn-primary" type="submit">Update</button>
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
