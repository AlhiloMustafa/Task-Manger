
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

	<div id="wrapper">
		<div id="header">
			<h2>Create / Update a Task:</h2>


		</div>

	</div>

	<div id="container">
		<h3></h3>

		<form:form action="newtask" modelAttribute="task" method="POST">
			<table>
				<form:hidden path="id" />

				<tr>
					<td>First Name:</td>
					<td><form:input path="taksName" /></td>
				</tr>
				<tr>
					<td>start date:</td>
					<td><form:input path="sdate" /></td>
				</tr>
				<tr>
					<td>end date:</td>
					<td><form:input path="edate" /></td>
				</tr>
				<tr>
					<td>description:</td>
					<td><form:input path="description" /></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><form:input path="email" /></td>
				</tr>
				<tr>
					<td>user:</td>
					<td><form:input path="user" /></td>
				</tr>

				<tr>
					<td>severity:</td>

					<td><form:select path="severity">
							<form:option value="NONE" label="Select" />
							<form:options items="${severityList}" />
						</form:select></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Save Changes" />
					</td>
				</tr>
			</table>
		</form:form>

	</div>


</body>
</html>


