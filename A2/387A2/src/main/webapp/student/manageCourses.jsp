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
<%@ page import="assignment.team._387a2.dataObjects.SemesterCourses" %>
<%@ page import="assignment.team._387a2.tableGateways.PersonTableGateway" %>
<%@ page import="java.util.Date" %>
<%@ page import="assignment.team._387a2.rowGateways.RegistrationGateway" %>
<%@ page import="assignment.team._387a2.tableGateways.RegistrationTableGateway" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head class="page-header header container-fluid-fluid">
	<title>Student - Manage Courses</title>
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
	<%--		<%@ include file = "checkIfStudent.jsp" %>--%>
	
	<div class="container-fluid">
		<div class="jumbotron text-center title">
			<h1>Manage courses</h1>
		</div>
	</div>

	<%
	// Load cookies
	Map<String, Cookie> cookies = CookieHelper.ConvertRequestCookies(request);
	// DEBUG
	cookies.put("personID", new Cookie("personID", "1"));
	cookies.put("firstName", new Cookie("firstName", "Luke"));
	cookies.put("lastName", new Cookie("lastName", "Skywalker"));
	// END DEBUG

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

	// Check Database Connection
	// Warning: Currently, will only crash the program if the connection cant be made.
	SQLConnection connection = new SQLConnection();

	// Loading post parameters
	String action = request.getParameter("action");
	String courseId = request.getParameter("courseId");
	String semester = request.getParameter("semester");

	// Creating Gateways
	CourseTableGateway courseTable = new CourseTableGateway();
	PersonTableGateway personTable = new PersonTableGateway();
	StudentTableGateway studentTable = new StudentTableGateway();
	RegistrationTableGateway registrationTable = new RegistrationTableGateway();

	// Loading student gateway
	StudentGateway student = studentTable.findByPersonId(Integer.parseInt(cookies.get("personID").getValue()));


	// Check if we clicked a button
	if ( (action != null && !action.isEmpty()) &&
			(courseId != null && !courseId.isEmpty()) &&
			(semester != null && !semester.isEmpty())
		)
	{
		// Check if we tried to add a course
		if (action.equals("Add"))
		{
			// Verify business logic rules:
			// A student may register in up to 5 courses per semester.
			List<SemesterCourses> coursesBySemester = courseTable.getCoursesBySemesterForPersonId(student.getPersonId());

				boolean canAccess = true;

				for(SemesterCourses semesterCourse : coursesBySemester)
				{
					// Cannot register to more than 5 courses in the same semester.
					if(semesterCourse.getCourseCount() >= 5 && semesterCourse.getSemester().equals(semester))
					{
						%>
						<div class="container-fluid">
							<div class="row">
								<div class="col-sm-4">
								</div> <!-- // End First Col -->
								<div class="col-sm-4 justify-content-center">
									<div class="alert alert-danger text-center">
									<p>You cannot register for more than 5 courses per semester. Sorry!</p>
								</div>
								</div> <!-- // End Second Col -->
								<div class="col-sm-4">
								</div> <!-- // End Third Col -->
							</div> <!-- // End Row -->
						</div> <!-- // End container-fluid -->

						<%
							canAccess = false;
					}
				}
				if (canAccess)
				{
					// Verify business logic rules:
					// A student can add a course up to one week after the start of the semester
					CourseGateway courseDetails = courseTable.findById(Integer.parseInt(courseId));



						Date startDate = courseDetails.getStartDate();
						Date today = new Date();

						// Generating a date that is 7 days past the start date of the course
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(startDate);
						calendar.add(Calendar.DAY_OF_YEAR, 7);
						Date maxAddDate = calendar.getTime();

						if (today.after(maxAddDate))
						{
							// We are past 7 days since the start of the course (semester), so we can't add it.
							%>

							<div class="container-fluid">
								<div class="row">
									<div class="col-sm-4">
									</div> <!-- // End First Col -->
									<div class="col-sm-4 justify-content-center">
										<div class="alert alert-danger text-center">
											<p>Could not add the course, as it is past 1 week since the start of this course. Sorry! </p>
										<br />
									</div>
									</div> <!-- // End Second Col -->
									<div class="col-sm-4">
									</div> <!-- // End Third Col -->
								</div> <!-- // End Row -->
							</div> <!-- // End container-fluid -->
						<%
						}
						else
						{
							// We execute an add query before displaying the page.
							RegistrationGateway registration = new RegistrationGateway(-1, student.getStudentId(), Integer.parseInt(courseId));
							registrationTable.insertRegistration(registration);
							%>
							<div class="container-fluid">
								<div class="row">
									<div class="col-sm-4">
									</div> <%--// End First Col--%>
									<div class="col-sm-4 justify-content-center">
										<div class="alert alert-success text-center">
											<p>Added course successfully!</p>
										</div>
									</div> <%--// End Second Col--%>
									<div class="col-sm-4">
									</div> <%--// End Third Col--%>
								</div> <%--// End Row--%>
							</div> <%--// End container-fluid--%>
							<%
						}
					}
				}
		// Check if we tried to drop a course
		else if (action.equals("Drop"))
		{
			// We execute a drop query before displaying the page.
			registrationTable.deleteRegistrationByStudentAndCourseId(student.getStudentId(), Integer.parseInt(courseId));
			%>
			<div class="container-fluid">
				<div class="row">
					<div class="col-sm-4">
					</div> <%--// End First Col--%>
					<div class="col-sm-4 justify-content-center">
						<div class="alert alert-success text-center">
							<p>Dropped course successfully!</p>
						</div>
					</div> <%--// End Second Col--%>
					<div class="col-sm-4">
					</div> <%--// End Third Col--%>
				</div> <%--// End Row--%>
			</div> <%--// End container-fluid--%>
			<%
		}
	}
	%>
	<!-- // =================== ADD COURSE =================== -->
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-2">
			</div> <!-- End First Col -->
			<div class="col-sm-8">
				<h2>Register for a course</h2>
			</div> <!-- End Second Col -->
			<div class="col-sm-2">
			</div> <!-- End Third Col -->
		</div> <!-- End Row -->
	</div> <!-- End container-fluid -->

	<%


		// Query available courses
		List<CourseGateway> availableCourses = courseTable.getCoursesAvailableToStudent(student.getStudentId());
		if (availableCourses.size() > 0)
		{
			%>
			<div class="container-fluid">
				<div class="row justify-content-center">
				<div class="col-sm-2">
				</div> <!-- End First Col -->
				<div class="col-sm-8">
					<div class="">
						<table class="table table-responsive table-striped table-bordered table-hover">
							<tr>
								<th>Course Code</th>
								<th>Title</th>
								<th>Semester</th>
								<th>Instructor</th>
								<th>Start Date</th>
								<th>End Date</th>
								<th></th>
							</tr>
							<%
							// Loop over the rows returned, showing them in a table.
							for(CourseGateway course : availableCourses)
							{
								%>
								<tr>
									<td><%= course.getCourseCode() %></td>
									<td><%= course.getTitle() %></td>
									<td><%= course.getSemester() %></td>
									<td><%= course.getInstructor() %></td>
									<td><%= course.getStartDate() %></td>
									<td><%= course.getEndDate() %></td>
									<!-- Clickable button to add a course -->
									<td>
										<form class="justify-content-center text-center" method="post" action="">
											<input class="btn btn-primary" type="submit" name="action" value="Add"/>
											<input type="hidden" name="courseId" value="<%= course.getCourseID() %>"/>
											<input type="hidden" name="semester" value="<%= course.getSemester() %>"/>
										</form>
									</td>
								</tr>
							<%
							}
							%>
						</table>
					</div>
				</div> <!-- End Second Col -->
			<div class="col-sm-2">
			</div> <!-- End Third Col -->
		</div> <!-- End Row -->
	</div> <!-- End container-fluid -->
		<%
		}
		else
		{
			%>
			<div class="container-fluid">
				<div class="row">
					<div class="col-sm-4">
					</div> <!-- End First Col -->
					<div class="col-sm-4">
						<h3>There are no courses available for you.</h3>
						<br />
					</div> <!-- End Second Col -->
					<div class="col-sm-4">
					</div> <!-- End Third Col -->
				</div> <!-- End Row -->
			</div> <!-- End container-fluid -->
			<%
		}

	%>
	<%--// =================== DROP COURSE ===================--%>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-2">
			</div> <!-- End First Col -->
			<div class="col-sm-8">
				<h2>Drop a course</h2>
			</div> <!-- End Second Col -->
			<div class="col-sm-2">
			</div> <!-- End Third Col -->
		</div> <!-- End Row -->
	</div> <!-- End container-fluid -->
	<%
	// Select available courses to drop.
	// Essentially, all courses that the student is registered to.
	List<CourseGateway> droppable = courseTable.getCoursesByPersonId(student.getPersonId());

	// Query registered courses
		if (droppable.size() > 0)
		{
			%>
			<div class="container-fluid">
				<div class="row justify-content-center">
					<div class="col-sm-2">
					</div> <!-- End First Col -->
					<div class="col-sm-8">
						<table class="table table-responsive table-striped table-bordered table-hover">
							<tr>
								<th>Course Code</th>
								<th>Title</th>
								<th>Semester</th>
								<th>Instructor</th>
								<th>Start Date</th>
								<th>End Date</th>
								<th></th>
							</tr>
							<%
						// Loop over the rows returned, showing them in a table.
						for(CourseGateway course : droppable)
						{
							%>
							<tr>
								<td><%= course.getCourseCode() %></td>
								<td><%= course.getTitle() %></td>
								<td><%= course.getSemester() %></td>
								<td><%= course.getInstructor() %></td>
								<td><%= course.getStartDate() %></td>
								<td><%= course.getEndDate() %></td>
								<!-- // Clickable button to drop a course -->
								<td>
									<form class="justify-content-center text-center" method="post" action="">
										<input class="btn btn-primary" type="submit" name="action" value="Drop"/>
										<input type="hidden" name="courseId" value="<%= course.getCourseID() %>"/>
										<input type="hidden" name="semester" value="<%= course.getSemester() %>"/>
									</form>
								</td>
							</tr>
							<%
						}
						%>
						</table>
					</div> <!-- End Second Col -->
					<div class="col-sm-2">
					</div> <!-- End Third Col -->
				</div> <!-- End Row -->
			</div> <!-- End container-fluid -->
			<%
		}
		else
		{
			%>
			<div class="container-fluid">
				<div class="row">
					<div class="col-sm-4">
					</div> <!-- End First Col -->
					<div class="col-sm-4">
						<h3>You are not registered to any courses at this time.</h3>
						<br />
					</div> <!-- End Second Col -->
					<div class="col-sm-4">
					</div> <!-- End Third Col -->
				</div> <!-- End Row -->
			</div> <!-- End container-fluid -->
		<%
		}
	%>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
	<script src="../js/bootstrap.bundle.min.js"></script>
</body>

</html>