<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>Search Results</title>
    <style type="text/css">
        body {
            font-family: arial, sans-serif;
            background-color: #F0E68C
        }

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
</head>

<body>
    <?php
    extract($_GET);

    // Checking user cookie
    if (!isset($_COOKIE["admin_user"])) {
        die("Could not retrieve admin user cookie. </body></html>");
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

    //Logic for displaying student list

    $queryForGettingStudentList =
    "SELECT Person.firstName, Person.lastName, Student.studentID
    FROM Person
    INNER JOIN Student
    ON Person.personID = Student.personID";
    if (!($queryForGettingStudentList = mysqli_query($database, $queryForGettingStudentList))) {
        print("Could not execute query to retrieve all students! <br />");
        die(mysqli_error($database) . "</body></html>");
    } else {
        if (mysqli_num_rows($queryForGettingStudentList) > 0) {
            print("<h2>List of all students</h2><br />");
            print("<table> 
                        <tr> 
                        <th>Student ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th></th>");

            // Loop over the rows returned, showing them in a table.
            while ($row = $queryForGettingStudentList->fetch_assoc()) {
                print("<tr>");
                print("<td>{$row['studentID']}</td>");
                print("<td>{$row['firstName']}</td>");
                print("<td>{$row['lastName']}</td>");
                // Clickable button to display class enrolled list
                print("<td>
                        <form method=\"get\" action=\"\">
                            <input type=\"submit\" name=\"action\" value=\"Show classes enrolled list\"/>
                            <input type=\"hidden\" name=\"studentId\" value=\"{$row['studentID']}\"/>
                        </form>
                        </td>");
                print("</tr>");
            }
            print("</table><br>");
        } else {
            print("<h3>There are no students.</h3>");
        }
    }
    if (
        isset($_GET['action']) &&
        isset($_GET['studentId']) &&
        $_GET['action'] &&
        $_GET['studentId']
    ) {
        $queryForGettingClassesEnrolledFromStudent =
            "SELECT Courses.courseId, Courses.courseCode, Courses.title, Courses.semester, Courses.instructor
            FROM Courses
            INNER JOIN Registrations
            ON Courses.courseId = Registrations.courseId
            WHERE Registrations.studentID = $studentId";

        if (!($studentListFromClass = mysqli_query($database, $queryForGettingClassesEnrolledFromStudent))) {
            print("Could not get student list from this class! Please try again later. <br />");
            print(mysqli_error($database));
            print("<br />");
        } else {
            if (mysqli_num_rows($studentListFromClass) > 0) {
                print("<h2>List of classes enrolled for this student</h2><br />");
                print("<table> 
                            <tr> 
                            <th>Course Code</th>
                            <th>Title</th>
                            <th>Semester</th>
                            <th>Instructor</th>
                            <th></th>");

                // Loop over the rows returned, showing them in a table.
                while ($row = $studentListFromClass->fetch_assoc()) {
                    print("<tr>");
                    print("<td>{$row['courseCode']}</td>");
                    print("<td>{$row['title']}</td>");
                    print("<td>{$row['semester']}</td>");
                    print("<td>{$row['instructor']}</td>");
                    print("</tr>");
                }
                print("</table>");
            } else {
                print("<h3>This student did not enroll to any classes.</h3>");
            }
        }
    }
    ?>
</body>

</html>