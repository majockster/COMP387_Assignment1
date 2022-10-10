<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>Search Results</title>
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
    <link rel="stylesheet" type="text/css" href="/COMP387_Assignment1/css/bootstrap.min.css" />
</head>

<body>
    <?php require_once("../../admin/navbar.php"); ?>

    <div class="container-fluid">
        <div class="jumbotron text-center title">
            <h1>Student List Report</h1>
        </div>
    </div>
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

    print("<div class=\"container-fluid\">");
    print("<div class=\"row\">");
    print("<div class=\"col-sm-2\">");
    print("</div>"); // End First Col
    print("<div class=\"col-sm-8\">");
    print("<h2>List of all courses</h2>");
    print("</div>"); // End Second Col
    print("<div class=\"col-sm-2\">");
    print("</div>"); // End Third Col
    print("</div>"); // End Row
    print("</div>"); // End container-fluid

    //Logic for displaying student list from a certain class

    $queryForGettingCoursesList =
        "SELECT Courses.courseId, Courses.courseCode, Courses.title, Courses.semester, Courses.instructor 
                    FROM Courses";
    if (!($queryForGettingCoursesList = mysqli_query($database, $queryForGettingCoursesList))) {
        print("<div class=\"container-fluid\">");
        print("<div class=\"row\">");
        print("<div class=\"col-sm-4\">");
        print("</div>"); // End First Col
        print("<div class=\"col-sm-4 justify-content-center\">");
        print("<div class=\"alert alert-danger text-center\">");
        print("<p>Could not execute query to retrieve all courses! </p>");
        print(mysqli_error($database));
        print("</div>");
        print("</div>"); // End Second Col
        print("<div class=\"col-sm-4\">");
        print("</div>"); // End Third Col
        print("</div>"); // End Row
        print("</div>"); // End container-fluid
    } else {
        if (mysqli_num_rows($queryForGettingCoursesList) > 0) {
            print("<div class=\"container-fluid\">");
            print("<div class=\"row justify-content-center\">");
            print("<div class=\"col-sm-2\">");
            print("</div>"); // End First Col
            print("<div class=\"col-sm-8\">");
            print("<div class=\"\">");
            print("
                            <table class=\"table table-responsive table-striped table-bordered table-hover\">  
                            <tr>
                            <th>Course ID</th> 
                            <th>Course Code</th>
                            <th>Title</th>
                            <th>Semester</th>
                            <th>Instructor</th>
                            <th></th>");

            // Loop over the rows returned, showing them in a table.
            while ($row = $queryForGettingCoursesList->fetch_assoc()) {
                print("<tr>");
                print("<td>{$row['courseId']}</td>");
                print("<td>{$row['courseCode']}</td>");
                print("<td>{$row['title']}</td>");
                print("<td>{$row['semester']}</td>");
                print("<td>{$row['instructor']}</td>");
                // Clickable button to display class list
                print("<td>
                                    <form class=\"justify-content-center text-center\" method=\"get\" action=\"\">
                                        <input type=\"submit\" name=\"action\" value=\"Show class list\"/>
                                        <input type=\"hidden\" name=\"courseId\" value=\"{$row['courseId']}\"/>
                                        <input type=\"hidden\" name=\"courseCode\" value=\"{$row['courseCode']}\"/>
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
            print("<h3>There are no courses.</h3>");
            print(mysqli_error($database));
            print("<br />");
            print("</div>"); // End Second Col
            print("<div class=\"col-sm-4\">");
            print("</div>"); // End Third Col
            print("</div>"); // End Row
            print("</div>"); // End container-fluid 
        }
    }
    if (
        isset($_GET['action']) &&
        isset($_GET['courseId']) && 
        isset($_GET['courseCode']) &&
        $_GET['action'] &&
        $_GET['courseId'] &&
        $_GET['courseCode']
    ) {
        $queryForGettingStudentListFromClass =
            "SELECT Person.firstName, Person.lastName, Student.studentID
                        FROM Person
                        INNER JOIN Student
                        ON Person.personID = Student.personID
                        INNER JOIN Registrations
                        ON Student.studentID = Registrations.studentID
                        WHERE Registrations.courseID = $courseId
                        ORDER BY Student.studentID";

        if (!($studentListFromClass = mysqli_query($database, $queryForGettingStudentListFromClass))) {
            print("<div class=\"container-fluid\">");
            print("<div class=\"row\">");
            print("<div class=\"col-sm-4\">");
            print("</div>"); // End First Col
            print("<div class=\"col-sm-4 justify-content-center\">");
            print("<div class=\"alert alert-danger text-center\">");
            print("<p>Could not get student list from this class! Please try again later. </p>");
            print(mysqli_error($database));
            print("</div>");
            print("</div>"); // End Second Col
            print("<div class=\"col-sm-4\">");
            print("</div>"); // End Third Col
            print("</div>"); // End Row
            print("</div>"); // End container-fluid
        } else {
            if (mysqli_num_rows($studentListFromClass) > 0) {
                print("<div class=\"container-fluid\">");
                print("<div class=\"row\">");
                print("<div class=\"col-sm-2\">");
                print("</div>"); // End First Col
                print("<div class=\"col-sm-8\">");
                print("<h2>List of students in this class: {$courseCode}</h2>");
                print("</div>"); // End Second Col
                print("<div class=\"col-sm-2\">");
                print("</div>"); // End Third Col
                print("</div>"); // End Row
                print("</div>"); // End container-fluid

                print("<div class=\"container-fluid\">");
                print("<div class=\"row justify-content-center\">");
                print("<div class=\"col-sm-2\">");
                print("</div>"); // End First Col
                print("<div class=\"col-sm-8\">");
                print("<table class=\"table table-responsive table-striped table-bordered table-hover\">  
                <tr> 
                <th>Student ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th></th>");
                // Loop over the rows returned, showing them in a table.
                while ($row = $studentListFromClass->fetch_assoc()) {
                    print("<tr>");
                    print("<td>{$row['studentID']}</td>");
                    print("<td>{$row['firstName']}</td>");
                    print("<td>{$row['lastName']}</td>");
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
                print("<h3>There are no students enrolled in this class.</h3>");
                print(mysqli_error($database));
                print("<br />");
                print("</div>"); // End Second Col
                print("<div class=\"col-sm-4\">");
                print("</div>"); // End Third Col
                print("</div>"); // End Row
                print("</div>"); // End container-fluid
            }
        }
    }
    mysqli_close($database);
    ?>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <script src="/js/bootstrap.bundle.min.js"></script>
</body>

</html>