<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<! DOCTYPE html>

<html>

<head>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<title>List Customers</title>

</head>

<body>



	<div id="wrapper">
		<div id="header">
		<h3></h3>
			
			<h2>Manage Tasks:</h2>
			 
			
		</div>
	</div>

	<div id="content">
		<table class="table">
			<tr>
				<th scope="col">Task name</th>
				<th scope="col">Start date</th>
				<th scope="col">End date </th>
				<th scope="col">Description</th>
				<th scope="col">Email</th>
				<th scope="col">Description</th>
				<th scope="col">Severity</th>
				<th scope="col">User</th>

			</tr>

			<c:forEach var="task" items="${tasks}">


				<tr>
					<td>${task.getTaksName() }</td>
					<td>${task.getSdate() }</td>
					<td>${task.getEdate() }</td>
					<td>${task.getDescription() }</td>
					<td>${task.getEmail() }</td>
					<td>${task.getSeverity() }</td>
					<td>${task.getUser() }</td>
					<td><a href="${updatelink }">update</a></td>



				</tr>
			</c:forEach>

		</table>

		<div>


			<button type="button" onclick="window.location.href='newuserform'">New
				Customer</button>


		</div>


	</div>

</body>

</html>

