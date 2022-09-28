		
		<!DOCTYPE html>
		<html xmlns = "http://www.w3.org/1999/xhtml">
		   <head>
		      <title>Search Results</title>
		   <style type = "text/css">
		         body  { font-family: arial, sans-serif;
		                 background-color: #F0E68C } 
		         table { background-color: #ADD8E6 }
		         td    { padding-top: 2px;
		                 padding-bottom: 2px;
		                 padding-left: 4px;
		                 padding-right: 4px;
		                 border-width: 1px;
		                 border-style: inset }
		      </style>
		   </head>
		   <body>
		      <?php
		         extract( $_POST );
				
				 // Checking user cookie
				 if(!isset($_COOKIE["user"])) 
				 {
					die("Could not retrieve user cookie. </body></html>");
				 } 

				 // Connect to MySQL Server
				 // mysqli_connect( Hostname, username, password)
		         if ( !($database = mysqli_connect("localhost", "root", "")) )  
				 {
					die("Could not connect to database </body></html>");
				 }                    
		            
				// Open database
				 // mysqli_select_db( connection, database_name 
		         if (!mysqli_select_db($database ,"soen387a1"))
				 {
					die("Could not open SOEN387A1 database </body></html>");
				 }            

				 // Check if we clicked a button
				 if (isset($_POST['action']) && 
					isset($_POST['courseId']) &&
					$_POST['action'] && 
					$_POST['courseId']) 
				 {
					// Check if we tried to add a course
					if ($_POST['action'] == 'Add') 
					{
						// Verify business logic rules:
							// A student may register in up to 5 courses per semester.
						$registeredCourses = "SELECT Courses.CourseId, Courses.courseCode, Courses.title, Courses.semester, courses.instructor, courses.startDate, courses.endDate 
						FROM Courses
						INNER JOIN Registrations
						ON Courses.courseID = Registrations.courseID
						INNER JOIN Student
						ON Registrations.studentID = Student.studentID
						WHERE Student.studentID = {$_COOKIE["user"]}";

						if ( !($registeredListResult = mysqli_query($database,$registeredCourses)) ) 
						{
							print("Could not add the course! Please try again later. <br />");
							print(mysqli_error($database));
							print("<br />");
						}
						else
						{
							// Continuing with verifying max courses for student
							if(mysqli_num_rows($registeredListResult) >= 5)
							{
								print("Could not add the course! You cannot register for more than 5 courses per semester. <br />");
								print("<br />");
							}
							else
							{
								// Verify business logic rules:
									// A student can add a course up to one week after the start of the semester
								$courseDetail = "SELECT Courses.CourseId, courses.startDate, courses.endDate 
									FROM Courses
									WHERE Courses.CourseId = $courseId";
								
								if ( !($courseDetailResult = mysqli_query($database,$courseDetail)) ) 
								{
									print("Could not add the course! Please try again later. <br />");
									print(mysqli_error($database));
									print("<br />");
								}
								else
								{
									// Continuing with verifying that a student can only add a course up to one week after start of semester
									$row = $courseDetailResult->fetch_assoc();
									$startDate = $row["startDate"];
									$todayDate = date("Y-m-d");

									// Converting dates to compare them
									$convertedStartDate = strtotime($startDate);
									$convertedToday = strtotime($todayDate);
									// Adds 7 days from the start date of the class
									$convertedMaxAddDate = strtotime("+7 day", $convertedStartDate);

									if($convertedToday > $convertedMaxAddDate)
									{
										// We are past 7 days since the start of the course (semester), so we can't add it.
										print("Could not add the course, as it is past 1 week since the start of this course. Sorry! <br />");
										print("<br />");
									}
									else
									{
										// We execute an add query before displaying the page.
										$registerCourse = "INSERT INTO Registrations(studentID, courseID) VALUES({$_COOKIE["user"]}, $courseId)";

										if ( !($addResult = mysqli_query($database,$registerCourse)) ) 
										{
											print("Could not add the course! Please try again later. <br />");
											print(mysqli_error($database));
											print("<br />");
										}
										else
										{
											print("<h2>Added course successfully!</h2>");
										}
									}
								}
							}
						}						
					}
					// Check if we tried to drop a course
					else if ($_POST['action'] == 'Drop') 
					{
						// We execute an add query before displaying the page.
					    $registerCourse = "DELETE FROM Registrations WHERE studentID ={$_COOKIE["user"]} AND courseID = $courseId";

						if ( !($addResult = mysqli_query( $database,$registerCourse)) ) 
						{
							print("Could not drop the course! Please try again later. <br />");
							print(mysqli_error($database));
							print("<br />");
						}
						else
						{
							print("<h2>Dropped course successfully!</h2>");
						}
					}
				  }
				
				 // =================== ADD COURSE ===================

				 print("<h1>Register for a course</h1>");
		         // Select available courses to register to.
				 // Essentially, all courses that the student is not registered to.
				 $selectAvailable="SELECT Courses.CourseId, Courses.courseCode, Courses.title, Courses.semester, courses.instructor, courses.startDate, courses.endDate 
				 FROM Courses 
				 EXCEPT
				 SELECT Courses.CourseId, Courses.courseCode, Courses.title, Courses.semester, courses.instructor, courses.startDate, courses.endDate 
				 FROM Courses
				 INNER JOIN Registrations
				 ON Courses.courseID = Registrations.courseID
				 INNER JOIN Student
				 ON Registrations.studentID = Student.studentID
				 WHERE Student.studentID = {$_COOKIE["user"]}";
		
		         // Query available courses
		         if ( !($result = mysqli_query( $database,$selectAvailable)) ) 
		         {
		            print("Could not execute query to retrieve available courses! <br />");
		            die(mysqli_error($database) . "</body></html>");
		         }
				else
				{
					if(mysqli_num_rows($result) > 0)
					{
						print("<table> 
						<tr> 
						<th>Course Code</th>
						<th>Title</th>
						<th>Semester</th>
						<th>Instructor</th>
						<th>Start Date</th>
						<th>End Date</th>
						<th></th>");
						
						// Loop over the rows returned, showing them in a table.
						while ($row = $result->fetch_assoc()) {						
							print("<tr>");
							print ("<td>{$row['courseCode']}</td>");
							print ("<td>{$row['title']}</td>");
							print ("<td>{$row['semester']}</td>");
							print ("<td>{$row['instructor']}</td>");
							print ("<td>{$row['startDate']}</td>");
							print ("<td>{$row['endDate']}</td>");
							// Clickable button to add a course
							print ("<td>
								<form method=\"post\" action=\"\">
									<input type=\"submit\" name=\"action\" value=\"Add\"/>
									<input type=\"hidden\" name=\"courseId\" value=\"{$row['CourseId']}\"/>
								  </form>
								</td>");
							print("</tr>");
						}
						print("</table>");
					}
					else
					{
						print("<h3>There are no courses available for you.</h3>");
					}
				}

				// =================== DROP COURSE ===================

				print("<h1>Drop a course</h1>");
				// Select available courses to drop.
				// Essentially, all courses that the student is registered to.
				 $selectDroppable="
				 SELECT Courses.CourseId, Courses.courseCode, Courses.title, Courses.semester, courses.instructor, courses.startDate, courses.endDate 
				 FROM Courses
				 INNER JOIN Registrations
				 ON Courses.courseID = Registrations.courseID
				 INNER JOIN Student
				 ON Registrations.studentID = Student.studentID
				 WHERE Student.studentID = {$_COOKIE["user"]}";
		
		         // Query registered courses
		         if ( !($result = mysqli_query( $database,$selectDroppable)) ) 
		         {
		            print("Could not execute query to retrieve registered courses! <br />");
		            die(mysqli_error($database) . "</body></html>");
		         }
				else
				{
					if(mysqli_num_rows($result) > 0)
					{
						print("<table> 
						<tr> 
						<th>Course Code</th>
						<th>Title</th>
						<th>Semester</th>
						<th>Instructor</th>
						<th>Start Date</th>
						<th>End Date</th>
						<th></th>");
						
						// Loop over the rows returned, showing them in a table.
						while ($row = $result->fetch_assoc()) {						
							print("<tr>");
							print ("<td>{$row['courseCode']}</td>");
							print ("<td>{$row['title']}</td>");
							print ("<td>{$row['semester']}</td>");
							print ("<td>{$row['instructor']}</td>");
							print ("<td>{$row['startDate']}</td>");
							print ("<td>{$row['endDate']}</td>");
							// Clickable button to drop a course
							print ("<td>
								<form method=\"post\" action=\"\">
									<input type=\"submit\" name=\"action\" value=\"Drop\"/>
									<input type=\"hidden\" name=\"courseId\" value=\"{$row['CourseId']}\"/>
								  </form>
								</td>");
							print("</tr>");
						}
						print("</table>");
					}
					else
					{
						print("<h3>You are not registered to any courses at this time.</h3>");
					}
				}
		        mysqli_close($database);
		      ?><!-- end PHP script -->
		      
		   </body>
		</html>
