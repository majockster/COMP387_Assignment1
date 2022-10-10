	<!DOCTYPE html">
	<?php
	?>
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
		<?php require_once("navbar.php"); ?>
		<!-- Checking user cookie and authorization -->
		<?php require("checkIfStudent.php"); ?>
		<?php
		if (!isset($_COOKIE["firstName"])) {
			$_COOKIE["firstName"] = "Guest";
		}
		if (!isset($_COOKIE["lastName"])) {
			$_COOKIE["lastName"] = "";
		}

		// Check data base connection
		require('../database/setup.php');

		print("<div class=\"container-fluid\">");
			print("<div class=\"jumbotron text-center title\">");
				print("<h1>Welcome {$_COOKIE["firstName"]} {$_COOKIE["lastName"]}</h1>");
			print("</div>");
		print("</div>");

		print("<div class=\"container-fluid\">");
		print("<div class=\"row\">");
		print("<div class=\"col-sm-2\">");
		print("</div>"); // End First Col
		print("<div class=\"col-sm-8\">");
		print("<h2 style='padding-top: 10px; padding-bottom: 10px'>Your courses</h2>");
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
			WHERE Student.StudentID = {$_COOKIE["personID"]}
			ORDER BY courseCode ASC";


		// Query registered courses
		if (!($result = mysqli_query($conn, $selectDroppable))) {
			print("<div class=\"container-fluid\">");
			print("<div class=\"row\">");
			print("<div class=\"col-sm-4\">");
			print("</div>"); // End First Col
			print("<div class=\"col-sm-4 justify-content-center\">");
			print("<div class=\"alert alert-danger text-center\">");
			print("<p>Could not execute query to retrieve registered courses!</p>");
			print(mysqli_error($conn));
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
		?>
		<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
		<script src="../js/bootstrap.bundle.min.js"></script>
	</body>

	</html>