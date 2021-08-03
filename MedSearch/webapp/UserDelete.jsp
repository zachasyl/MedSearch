<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<title>Delete a User</title>
</head>
<body>

<main class="mt-4 px-5">
	<div>
		<h1 class="display-4">MedSearch: Delete User</h1>
		<hr class="my-4">
		<div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
		<form action="userdelete" method="post">
			<p> <label for="username">UserName:</label> <input
				id="username" name="username"
				value="${fn:escapeXml(param.username)}"> 
			</p>
			<p>
				<span id="submitButton"
					<c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
					<input type="submit" value="Delete" class="btn btn-primary">
				</span>
			</p>
		</form>
		</div>
		<br /> <br />
	</div>

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
	
	</main>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>