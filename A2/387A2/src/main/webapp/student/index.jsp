<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ page import = "assignment.team._387a2.helperObjects.SQLConnection" %>
<%@ page import="javax.servlet.http.Cookie" %>
<%@ page import="assignment.team._387a2.helperObjects.CookieHelper" %>
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
		<%@ include file = “navbar.jsp” %>
		<!-- Checking user cookie and authorization -->
		<%@ include file = “checkIfStudent.jsp” %>
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
			if(!SQLConnection.TestConnection())
				return;
		%>

		<div class="container-fluid">
			<div class="jumbotron text-center title">
				<h1>Welcome ${cookie["firstName"]} ${cookie["lastName"]}</h1>
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
		<% // Select available courses to drop.
		// Essentially, all courses that the student is registered to.
		String selectDroppable = """
   			SELECT Courses.CourseId, Courses.courseCode, Courses.title,
			Courses.semester, courses.instructor, courses.startDate, courses.endDate
			FROM Courses
			INNER JOIN Registrations
			ON Courses.courseID = Registrations.courseID
			INNER JOIN Student
			ON Registrations.studentID = Student.studentID
			WHERE Student.personID =""" + cookie["personID"] +
			"ORDER BY courseCode ASC";

		ResultSet droppableResult = SQLConnection.ExecuteQuery(selectDroppable);

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
			if (SQLConnection.NumberOfRows(droppableResult) > 0) {
				print("<div class=\"container-fluid\">");
				print("<div class=\"row justify-content-center\">");
				print("<div class=\"col-sm-2\">");
				print("</div>"); // End First Col
				print("<div class=\"col-sm-8\">");
				print("<table class=\"table table-responsive table-striped table-bordered table-hover\"> 
											<tr> 
												<th>Course Code</th>
												<th>Title</th>
												<th>Semester</th>
												<th>Instructor</th>
												<th>Start Date</th>
												<th>End Date</th>
											</tr>");

				// Loop over the rows returned, showing them in a table.
				while ($row = $result->fetch_assoc())
				{
					print("<tr>");
					print("<td>{$row['courseCode']}</td>");
					print("<td>{$row['title']}</td>");
					print("<td>{$row['semester']}</td>");
					print("<td>{$row['instructor']}</td>");
					print("<td>{$row['startDate']}</td>");
					print("<td>{$row['endDate']}</td>");
					print("</tr>");
				}
				print("</table>");
				print("</div>"); // End Second Col
				print("<div class=\"col-sm-2\">");
				print("</div>"); // End Third Col
				print("</div>"); // End Row
				print("</div>"); // End container-fluid
			}
			else
			{
				print("<div class=\"container-fluid\">");
				print("<div class=\"row\">");
				print("<div class=\"col-sm-4\">");
				print("</div>"); // End First Col
				print("<div class=\"col-sm-4\">");
				print("<h3>You are not registered to any courses at this time.</h3>");
				print(mysqli_error($conn));
				print("<br />");
				print("</div>"); // End Second Col
				print("<div class=\"col-sm-4\">");
				print("</div>"); // End Third Col
				print("</div>"); // End Row
				print("</div>"); // End container-fluid
			}
		}
		mysqli_close($conn);
		<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
		<script src="../js/bootstrap.bundle.min.js"></script>
	</body>

	</html>