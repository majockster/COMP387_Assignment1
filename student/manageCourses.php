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
	<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css" />
</head>

<body>
	<?php require_once("navbar.php"); ?>

	<div class="container-fluid">
		<div class="jumbotron text-center title">
			<h1>Manage courses</h1>
		</div>
	</div>
	<?php
	extract($_POST);

	// Checking user cookie
	if (!isset($_COOKIE['personID'])) {
		die("Could not retrieve user cookie. </body></html>");
	}

	// Connect to MySQL Server
	// mysqli_connect( Hostname, username, password)
	if (!($database = mysqli_connect("localhost", "root", ""))) {
		die("Could not connect to database </body></html>");
	}

	// Open database
	// mysqli_select_db( connection, database_name 
	if (!mysqli_select_db($database, "soen387a1")) {
		die("Could not open SOEN387A1 database </body></html>");
	}

	// Check if we clicked a button
	if (
		isset($_POST['action']) &&
		isset($_POST['courseId']) &&
		isset($_POST['semester']) &&
		$_POST['action'] &&
		$_POST['courseId'] &&
		$_POST['semester']
	) {
		// Check if we tried to add a course
		if ($_POST['action'] == 'Add') {
			// Verify business logic rules:
			// A student may register in up to 5 courses per semester.
			// TODO check semester
			$registeredCourses = "SELECT COUNT(Courses.CourseId) AS CoursesCount, Courses.semester, courses.startDate, courses.endDate 
						FROM Courses
						INNER JOIN Registrations
						ON Courses.courseID = Registrations.courseID
						INNER JOIN Student
						ON Registrations.studentID = Student.studentID
						WHERE Student.studentID = {$_COOKIE['personID']}
                        GROUP BY Courses.semester";

			if (!($registeredListResult = mysqli_query($database, $registeredCourses))) {
				print("<div class=\"container-fluid\">");
				print("<div class=\"row\">");
				print("<div class=\"col-sm-4\">");
				print("</div>"); // End First Col
				print("<div class=\"col-sm-4 justify-content-center\">");
				print("<div class=\"alert alert-danger text-center\">");
				print("<p>Could not add the course! Please try again later.</p>");
				print(mysqli_error($database));
				print("</div>");
				print("</div>"); // End Second Col
				print("<div class=\"col-sm-4\">");
				print("</div>"); // End Third Col
				print("</div>"); // End Row
				print("</div>"); // End container-fluid
			} else {
				// Continuing with verifying max courses for student
				$canAccess = true;
				while ($row = $registeredListResult->fetch_assoc()) {
					if ($row["CoursesCount"] >= 5 && $row["semester"] == $_POST['semester']) {
						print("<div class=\"container-fluid\">");
						print("<div class=\"row\">");
						print("<div class=\"col-sm-4\">");
						print("</div>"); // End First Col
						print("<div class=\"col-sm-4 justify-content-center\">");
						print("<div class=\"alert alert-danger text-center\">");
						print("<p>You cannot register for more than 5 courses per semester. Sorry!</p>");
						print(mysqli_error($database));
						print("</div>");
						print("</div>"); // End Second Col
						print("<div class=\"col-sm-4\">");
						print("</div>"); // End Third Col
						print("</div>"); // End Row
						print("</div>"); // End container-fluid
						$canAccess = false;
					}
				}
				if ($canAccess) {
					// Verify business logic rules:
					// A student can add a course up to one week after the start of the semester
					$courseDetail = "SELECT Courses.CourseId, courses.startDate, courses.endDate 
									FROM Courses
									WHERE Courses.CourseId = $courseId";

					if (!($courseDetailResult = mysqli_query($database, $courseDetail))) {
						print("<div class=\"container-fluid\">");
						print("<div class=\"row\">");
						print("<div class=\"col-sm-4\">");
						print("</div>"); // End First Col
						print("<div class=\"col-sm-4 justify-content-center\">");
						print("<div class=\"alert alert-danger text-center\">");
						print("<p>Could not add the course! Please try again later.</p>");
						print(mysqli_error($database));
						print("</div>");
						print("</div>"); // End Second Col
						print("<div class=\"col-sm-4\">");
						print("</div>"); // End Third Col
						print("</div>"); // End Row
						print("</div>"); // End container-fluid
					} else {
						// Continuing with verifying that a student can only add a course up to one week after start of semester
						$row = $courseDetailResult->fetch_assoc();
						$startDate = $row["startDate"];
						$todayDate = date("Y-m-d");

						// Converting dates to compare them
						$convertedStartDate = strtotime($startDate);
						$convertedToday = strtotime($todayDate);
						// Adds 7 days from the start date of the class
						$convertedMaxAddDate = strtotime("+7 day", $convertedStartDate);

						if ($convertedToday > $convertedMaxAddDate) {
							// We are past 7 days since the start of the course (semester), so we can't add it.
							print("<div class=\"container-fluid\">");
							print("<div class=\"row\">");
							print("<div class=\"col-sm-4\">");
							print("</div>"); // End First Col
							print("<div class=\"col-sm-4 justify-content-center\">");
							print("<div class=\"alert alert-danger text-center\">");
							print("Could not add the course, as it is past 1 week since the start of this course. Sorry! <br />");
							print(mysqli_error($database));
							print("<br />");
							print("</div>");
							print("</div>"); // End Second Col
							print("<div class=\"col-sm-4\">");
							print("</div>"); // End Third Col
							print("</div>"); // End Row
							print("</div>"); // End container-fluid
						} else {
							// We execute an add query before displaying the page.
							$registerCourse = "INSERT INTO Registrations(studentID, courseID) VALUES({$_COOKIE['personID']}, $courseId)";

							if (!($addResult = mysqli_query($database, $registerCourse))) {
								print("<div class=\"container-fluid\">");
								print("<div class=\"row\">");
								print("<div class=\"col-sm-4\">");
								print("</div>"); // End First Col
								print("<div class=\"col-sm-4 justify-content-center\">");
								print("<div class=\"alert alert-danger text-center\">");
								print("<p>Could not add the course! Please try again later.</p>");
								print(mysqli_error($database));
								print("</div>");
								print("</div>"); // End Second Col
								print("<div class=\"col-sm-4\">");
								print("</div>"); // End Third Col
								print("</div>"); // End Row
								print("</div>"); // End container-fluid
							} else {
								print("<div class=\"container-fluid\">");
								print("<div class=\"row\">");
								print("<div class=\"col-sm-4\">");
								print("</div>"); // End First Col
								print("<div class=\"col-sm-4 justify-content-center\">");
								print("<div class=\"alert alert-success text-center\">");
								print("<p>Added course successfully!</p>");
								print(mysqli_error($database));
								print("</div>");
								print("</div>"); // End Second Col
								print("<div class=\"col-sm-4\">");
								print("</div>"); // End Third Col
								print("</div>"); // End Row
								print("</div>"); // End container-fluid
							}
						}
					}
				}
			}
		}
		// Check if we tried to drop a course
		else if ($_POST['action'] == 'Drop') {
			// We execute an add query before displaying the page.
			$registerCourse = "DELETE FROM Registrations WHERE studentID ={$_COOKIE['personID']} AND courseID = $courseId";

			if (!($addResult = mysqli_query($database, $registerCourse))) {
				print("<div class=\"container-fluid\">");
				print("<div class=\"row\">");
				print("<div class=\"col-sm-4\">");
				print("</div>"); // End First Col
				print("<div class=\"col-sm-4 justify-content-center\">");
				print("<div class=\"alert alert-danger text-center\">");
				print("<p>Could not drop the course! Please try again later.</p>");
				print(mysqli_error($database));
				print("</div>");
				print("</div>"); // End Second Col
				print("<div class=\"col-sm-4\">");
				print("</div>"); // End Third Col
				print("</div>"); // End Row
				print("</div>"); // End container-fluid
			} else {
				print("<div class=\"container-fluid\">");
				print("<div class=\"row\">");
				print("<div class=\"col-sm-4\">");
				print("</div>"); // End First Col
				print("<div class=\"col-sm-4 justify-content-center\">");
				print("<div class=\"alert alert-success text-center\">");
				print("<p>Dropped course successfully!</p>");
				print(mysqli_error($database));
				print("</div>");
				print("</div>"); // End Second Col
				print("<div class=\"col-sm-4\">");
				print("</div>"); // End Third Col
				print("</div>"); // End Row
				print("</div>"); // End container-fluid
			}
		}
	}

	// =================== ADD COURSE ===================
	print("<div class=\"container-fluid\">");
	print("<div class=\"row\">");
	print("<div class=\"col-sm-2\">");
	print("</div>"); // End First Col
	print("<div class=\"col-sm-8\">");
	print("<h2>Register for a course</h2>");
	print("</div>"); // End Second Col
	print("<div class=\"col-sm-2\">");
	print("</div>"); // End Third Col
	print("</div>"); // End Row
	print("</div>"); // End container-fluid

	// Select available courses to register to.
	// Essentially, all courses that the student is not registered to.
	$selectAvailable = "SELECT Courses.CourseId, Courses.courseCode, Courses.title, Courses.semester, courses.instructor, courses.startDate, courses.endDate 
				 FROM Courses 
				 EXCEPT
				 SELECT Courses.CourseId, Courses.courseCode, Courses.title, Courses.semester, courses.instructor, courses.startDate, courses.endDate 
				 FROM Courses
				 INNER JOIN Registrations
				 ON Courses.courseID = Registrations.courseID
				 INNER JOIN Student
				 ON Registrations.studentID = Student.studentID
				 WHERE Student.studentID = {$_COOKIE['personID']}
				 ORDER BY courseCode ASC";

	// Query available courses
	if (!($result = mysqli_query($database, $selectAvailable))) {
		print("<div class=\"container-fluid\">");
		print("<div class=\"row\">");
		print("<div class=\"col-sm-4\">");
		print("</div>"); // End First Col
		print("<div class=\"col-sm-4 justify-content-center\">");
		print("<div class=\"alert alert-danger text-center\">");
		print("<p>Could not execute query to retrieve available courses!</p>");
		print(mysqli_error($database));
		print("</div>");
		print("</div>"); // End Second Col
		print("<div class=\"col-sm-4\">");
		print("</div>"); // End Third Col
		print("</div>"); // End Row
		print("</div>"); // End container-fluid
	} else {
		if (mysqli_num_rows($result) > 0) {
			print("<div class=\"container-fluid\">");
			print("<div class=\"row justify-content-center\">");
			print("<div class=\"col-sm-2\">");
			print("</div>"); // End First Col
			print("<div class=\"col-sm-8\">");
			print("<div class=\"\">");
			print("
										<table class=\"table table-responsive table-striped table-bordered table-hover\"> 
											<tr> 
												<th>Course Code</th>
												<th>Title</th>
												<th>Semester</th>
												<th>Instructor</th>
												<th>Start Date</th>
												<th>End Date</th>
												<th></th>
											</tr>");

			// Loop over the rows returned, showing them in a table.
			while ($row = $result->fetch_assoc()) {
				print("<tr>");
				print("<td>{$row['courseCode']}</td>");
				print("<td>{$row['title']}</td>");
				print("<td>{$row['semester']}</td>");
				print("<td>{$row['instructor']}</td>");
				print("<td>{$row['startDate']}</td>");
				print("<td>{$row['endDate']}</td>");
				// Clickable button to add a course
				print("<td>
												<form class=\"justify-content-center text-center\" method=\"post\" action=\"\">
													<input class=\"btn btn-primary\" type=\"submit\" name=\"action\" value=\"Add\"/>
													<input type=\"hidden\" name=\"courseId\" value=\"{$row['CourseId']}\"/>
													<input type=\"hidden\" name=\"semester\" value=\"{$row['semester']}\"/>
												</form>
												</td>");
				print("</tr>");
			}
			print("</table>");
			print("</div>");
			print("</div>"); // End Second Col
			print("<div class=\"col-sm-2\">");
			print("</div>"); // End Third Col
			print("</div>"); // End Row
			print("</div>"); // End container-fluid

		} else {
			print("<div class=\"container-fluid\">");
			print("<div class=\"row\">");
			print("<div class=\"col-sm-4\">");
			print("</div>"); // End First Col
			print("<div class=\"col-sm-4\">");
			print("<h3>There are no courses available for you.</h3>");
			print(mysqli_error($database));
			print("<br />");
			print("</div>"); // End Second Col
			print("<div class=\"col-sm-4\">");
			print("</div>"); // End Third Col
			print("</div>"); // End Row
			print("</div>"); // End container-fluid
		}
	}

	// =================== DROP COURSE ===================

	print("<div class=\"container-fluid\">");
	print("<div class=\"row\">");
	print("<div class=\"col-sm-2\">");
	print("</div>"); // End First Col
	print("<div class=\"col-sm-8\">");
	print("<h2>Drop a course</h2>");
	print("</div>"); // End Second Col
	print("<div class=\"col-sm-2\">");
	print("</div>"); // End Third Col
	print("</div>"); // End Row
	print("</div>"); // End container-fluid
	// Select available courses to drop.
	// Essentially, all courses that the student is registered to.
	$selectDroppable = " SELECT Courses.CourseId, Courses.courseCode, Courses.title, 
				 Courses.semester, courses.instructor, courses.startDate, courses.endDate 
				 FROM Courses
				 INNER JOIN Registrations
				 ON Courses.courseID = Registrations.courseID
				 INNER JOIN Student
				 ON Registrations.studentID = Student.studentID
				 WHERE Student.studentID = {$_COOKIE['personID']}
				 ORDER BY courseCode ASC";

	// Query registered courses
	if (!($result = mysqli_query($database, $selectDroppable))) {
		print("<div class=\"container-fluid\">");
		print("<div class=\"row\">");
		print("<div class=\"col-sm-4\">");
		print("</div>"); // End First Col
		print("<div class=\"col-sm-4 justify-content-center\">");
		print("<div class=\"alert alert-danger text-center\">");
		print("<p>Could not execute query to retrieve registered courses!</p>");
		print(mysqli_error($database));
		print("</div>");
		print("</div>"); // End Second Col
		print("<div class=\"col-sm-4\">");
		print("</div>"); // End Third Col
		print("</div>"); // End Row
		print("</div>"); // End container-fluid
	} else {
		if (mysqli_num_rows($result) > 0) {
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
													<th></th>
												</tr>");

			// Loop over the rows returned, showing them in a table.
			while ($row = $result->fetch_assoc()) {
				print("<tr>");
				print("<td>{$row['courseCode']}</td>");
				print("<td>{$row['title']}</td>");
				print("<td>{$row['semester']}</td>");
				print("<td>{$row['instructor']}</td>");
				print("<td>{$row['startDate']}</td>");
				print("<td>{$row['endDate']}</td>");
				// Clickable button to drop a course
				print("<td>
											<form class=\"justify-content-center text-center\" method=\"post\" action=\"\">
												<input class=\"btn btn-primary\" type=\"submit\" name=\"action\" value=\"Drop\"/>
												<input type=\"hidden\" name=\"courseId\" value=\"{$row['CourseId']}\"/>
												<input type=\"hidden\" name=\"semester\" value=\"{$row['semester']}\"/>
											</form>
											</td>");
				print("</tr>");
			}
			print("</table>");
			print("</div>"); // End Second Col
			print("<div class=\"col-sm-2\">");
			print("</div>"); // End Third Col
			print("</div>"); // End Row
			print("</div>"); // End container-fluid
		} else {
			print("<div class=\"container-fluid\">");
			print("<div class=\"row\">");
			print("<div class=\"col-sm-4\">");
			print("</div>"); // End First Col
			print("<div class=\"col-sm-4\">");
			print("<h3>You are not registered to any courses at this time.</h3>");
			print(mysqli_error($database));
			print("<br />");
			print("</div>"); // End Second Col
			print("<div class=\"col-sm-4\">");
			print("</div>"); // End Third Col
			print("</div>"); // End Row
			print("</div>"); // End container-fluid
		}
	}
	mysqli_close($database);
	?>
	<!-- end PHP script -->
	<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
	<script src="./js/bootstrap.bundle.min.js"></script>
</body>

</html>