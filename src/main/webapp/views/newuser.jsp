
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body style="padding: 5%">
	
	<div id="wrap
		per">
		<div id="header">
			<h2>New User Registration Form :</h2>


		</div>

	</div>

	<div id="container">
		<h3></h3>
		<font color="red">${errorMessage}</font>

		<form:form action="savenewuser" modelAttribute="user" method="POST">
			<table>
			<form:hidden path="id" />
			
				<tr>
					<td>User Name:</td>
					<td><form:input path="userName" /></td>
				</tr>
				
				<tr>
					<td>Password:</td>
					<td><form:input path="password" /></td>
				</tr>
				<tr>
					<td>Role:</td>
					<td><form:input path="roles" /></td>
				</tr>

				<tr  ">
					<td style="padding-top: 20px;" colspan="2"><input type="submit" value="Save Changes" />
					</td>
				</tr>
			</table>
		</form:form>

	</div>


</body>
</html>