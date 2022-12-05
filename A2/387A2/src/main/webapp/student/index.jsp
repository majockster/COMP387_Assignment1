<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ page import = "assignment.team._387a2.helperObjects.SQLConnection" %>
<%@ page import="javax.servlet.http.Cookie" %>
<%@ page import="assignment.team._387a2.helperObjects.CookieHelper" %>
<%@ page import="assignment.team._387a2.tableGateways.CourseTableGateway" %>
<%@ page import="assignment.team._387a2.tableGateways.StudentTableGateway" %>
<%@ page import="assignment.team._387a2.domainObjects.Student" %>
<%@ page import="assignment.team._387a2.domainObjects.Course" %>
<!DOCTYPE html>
	<html>

	<head class="page-header header container-fluid-fluid">
		<title>Student - Home Page</title>
		<style type="text/css">
			table {
				background-color: #ADD8E6
			}

			td {
				padding-top: 2px;
				padding-bottom: 2px;
				padding-left: 4px;
				padding-right: 4px;
				border-width: 1px;
				border-style: inset
			}
		</style>
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css" />
	</head>

	<body>
		<%@ include file = "navbar.jsp" %>
<%--		<!-- Checking user cookie and authorization -->--%>
		<%@ include file = "checkIfStudent.jsp" %>
		<%
		// Load cookies
		Map<String, Cookie> cookies = CookieHelper.ConvertRequestCookies(request);

		if (cookies.get("firstName") == null)
		{
			Cookie newCookie = new Cookie("firstName", "Guest");
			cookies.put("firstName", newCookie);
		}
		if (cookies.get("lastName") == null)
		{
			Cookie newCookie = new Cookie("lastName", "");
			cookies.put("lastName", newCookie);
		}
		%>

		<!-- Check data base connection -->
		<%
			// Warning: Currently, will only crash the program if the connection cant be made.
			SQLConnection connection = new SQLConnection();
		%>

		<div class="container-fluid">
			<div class="jumbotron text-center title">
				<h1>Welcome <%= cookies.get("firstName").getValue() %> <%= cookies.get("lastName").getValue() %></h1>
			</div>
		</div>

		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-2">
				</div>  <%-- End First Col --%>
				<div class="col-sm-8">
					<h2 style='padding-top: 10px; padding-bottom: 10px'>Your courses</h2>
				</div> <%-- // End Second Col --%>
				<div class="col-sm-2">
				</div>" <%--// End Third Col --%>
			</div> <%-- // End Row --%>
		</div> <%-- // End container-fluid --%>
		<%
		StudentTableGateway studentTable = new StudentTableGateway();
		CourseTableGateway courseTable = new CourseTableGateway();

		// Retrieve student object of connected student
		int studentPersonId = Integer.parseInt(cookies.get("personID").getValue());

		Student student = studentTable.findByPersonId(studentPersonId, ResultSet.CONCUR_READ_ONLY);

		// Select available courses to drop.
		// Essentially, all courses that the student is registered to.
		List<Course> droppableResult = courseTable.getCoursesByStudentId(student.getId());

		// Query registered courses
		if (droppableResult == null)
		{
		%>
			<div class="container-fluid">
				<div class="row">
					<div class="col-sm-4">
					</div> <!-- End First Col -->
					<div class="col-sm-4 justify-content-center">
						<div class="alert alert-danger text-center">
							<p>Could not execute query to retrieve registered courses!</p>
						</div>
					</div> <!-- End Second Col -->
					<div class="col-sm-4">
					</div> <!-- End Third Col -->
				</div>" <!-- End Row -->
			</div> <!-- End container-fluid -->
		<%
		}
		else
		{
			if (droppableResult.size() > 0) {
				%>
				<div class="container-fluid">
				<div class="row justify-content-center">
				<div class="col-sm-2">
				</div> <%--// End First Col--%>
				<div class="col-sm-8">
				<table class="table table-responsive table-striped table-bordered table-hover"> 
											<tr> 
												<th>Course Code</th>
												<th>Title</th>
												<th>Semester</th>
												<th>Instructor</th>
												<th>Start Date</th>
												<th>End Date</th>
											</tr>
				<%
				// Loop over the rows returned, showing them in a table.
				for (Course course : droppableResult)
				{
					%>
						<tr>
						<td><%= course.getCourseCode() %></td>
						<td><%= course.getTitle() %></td>
						<td><%= course.getSemester() %></td>
						<td><%= course.getInstructor() %></td>
						<td><%= course.getStartDate() %></td>
						<td><%= course.getEndDate() %></td>
						</tr>
					<%
				}
				%>
				</table>
				</div> <!-- // End Second Col -->
				<div class="col-sm-2">
				</div> <!-- // End Third Col -->
				</div> <!-- // End Row -->
				</div> <!-- // End container-fluid -->
				<%
			}
			else
			{
				%>
					<div class="container-fluid">
						<div class="row">
							<div class="col-sm-4">
							</div> <%--// End First Col--%>
							<div class="col-sm-4">
								<h3>You are not registered to any courses at this time.</h3>
								<br />
							</div> <%--// End Second Col--%>
							<div class="col-sm-4">
							</div> <%--// End Third Col--%>
						</div> <%--// End Row--%>
					</div> <%--// End container-fluid--%>
				<%
			}
		}
		%>
		<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
		<script src="../js/bootstrap.bundle.min.js"></script>
	</body>

	</html>