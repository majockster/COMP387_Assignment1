<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ page import = "assignment.team._387a2.helperObjects.SQLConnection" %>
<%@ page import="javax.servlet.http.Cookie" %>
<%@ page import="assignment.team._387a2.helperObjects.CookieHelper" %>
<%@ page import="assignment.team._387a2.tableGateways.CourseTableGateway" %>
<%@ page import="assignment.team._387a2.tableGateways.StudentTableGateway" %>
<%@ page import="assignment.team._387a2.rowGateways.StudentGateway" %>
<%@ page import="assignment.team._387a2.rowGateways.CourseGateway" %>
<%@ page import="static java.lang.Integer.parseInt" %>
<%@ page import="assignment.team._387a2.tableGateways.PersonTableGateway" %>
<%@ page import="assignment.team._387a2.rowGateways.PersonGateway" %>
<%@ page import="assignment.team._387a2.tableGateways.AdministratorTableGateway" %>
<%@ page import="assignment.team._387a2.rowGateways.AdministratorGateway" %>


<%
	String message = "";
	boolean error = false;
	if(request.getParameter("email") != null)
	{
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		PersonTableGateway ptg = new PersonTableGateway();
		PersonGateway person = ptg.findByEmail(email);
		if (person != null)
		{

			if (person.getPassword().equals(password))
			{
				String pid = String.valueOf(person.getPersonId());
				String fname = person.getFirstName();
				String lname = person.getLastName();

				Map<String, Cookie> cookies = CookieHelper.ConvertRequestCookies(request);
				cookies.put("personID", new Cookie("personID", pid));
				cookies.put("firstName", new Cookie("firstName", fname));
				cookies.put("lastName", new Cookie("lastName", lname));

				AdministratorTableGateway atg = new AdministratorTableGateway();
				AdministratorGateway admin = atg.findByPersonId(person.getPersonId());
				if (admin == null) {
					cookies.put("userType", new Cookie("userType", "student"));
					response.sendRedirect("student");
				}
				else
				{
					cookies.put("userType", new Cookie("userType", "admin"));
					response.sendRedirect("admin");
				}
			}
			else
			{
				error = true;
					%>
					<script type="text/javascript" >
						alert("Invalid email or password");
					</script>
					<%
			}
		}
		else
		{
			error = true;
					%>
					<script type="text/javascript" >
						alert("Invalid email or password");
					</script>
					<%
		}
	}
%>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Sign In</title>

		<!-- Bootstrap CSS -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">


		<!-- Custom styles for this template -->
		<link href="login.css" rel="stylesheet">
	</head>

	<body class="text-center">

	<main class="form-signin">
		<form action="login.jsp" method="post">
			<h1 class="h3 mb-3 fw-normal">Login</h1>

			<div class="form-floating">
				<input type="email" class="form-control" id="email" placeholder="name@example.com" name="email" required>
				<label for="email">Email address</label>
			</div>
			<div class="form-floating">
				<input type="password" class="form-control" id="password" placeholder="Password" name="password" required>
				<label for="password">Password</label>
			</div>

			<div class="d-grid gap-2">
				<button class="btn btn-sm btn-primary" type="submit">Submit</button>
			</div>
		</form>
	</main>

	</body>
</html>


