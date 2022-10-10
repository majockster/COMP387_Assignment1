<!DOCTYPE html">
<html xmlns="http://www.w3.org/1999/xhtml">
<?php
// Setting user cookie.
$cookie_name = "admin_user";
$cookie_value = "2";
setcookie($cookie_name, $cookie_value, time() + (86400 * 30), "/"); // 86400 = 1 day
?>
<html>



<head class="page-header header container-fluid-fluid">
    <title>Delete a Course</title>
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
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap.min.css" />
</head>

<body>
    <?php require_once("../../admin/navbar.php"); ?>

    <div class="container-fluid">
        <div class="jumbotron text-center title" style="padding-top: 10px; padding-bottom: 10px">
            <h1>Delete courses</h1>
        </div>
    </div>
    <?php require_once("../../database/setup.php");
    if (isset($_POST['action'])) {
        if ($_POST['action'] == 'Delete') {
            // We execute an add query before displaying the page.
            $courseId = $_POST['courseId'];
            $deleteCourse = "DELETE FROM Courses WHERE courseID = $courseId";

            if (!($addResult = mysqli_query($conn, $deleteCourse))) {
                print("<div class=\"container-fluid\">");
                print("<div class=\"row\">");
                print("<div class=\"col-sm-4\">");
                print("</div>"); // End First Col
                print("<div class=\"col-sm-4 justify-content-center\">");
                print("<div class=\"alert alert-danger text-center\">");
                print("<p>Could not delete the course! Please try again later.</p>");
                print(mysqli_error($conn));
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
                print("<p>Deleted course successfully!</p>");
                print(mysqli_error($conn));
                print("</div>");
                print("</div>"); // End Second Col
                print("<div class=\"col-sm-4\">");
                print("</div>"); // End Third Col
                print("</div>"); // End Row
                print("</div>"); // End container-fluid
            }
        }
    }

    $selectDeletable = " SELECT Courses.CourseId, Courses.courseCode, Courses.title, 
 Courses.semester, courses.instructor, courses.startDate, courses.endDate 
 FROM Courses
 ORDER BY courseCode ASC";
    // Query registered courses
    if (!($result = mysqli_query($conn, $selectDeletable))) {
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
                                <input class=\"btn btn-primary\" type=\"submit\" name=\"action\" value=\"Delete\"/>
                                <input type=\"hidden\" name=\"courseId\" value=\"{$row['CourseId']}\"/>
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
            print(mysqli_error($conn));
            print("<br />");
            print("</div>"); // End Second Col
            print("<div class=\"col-sm-4\">");
            print("</div>"); // End Third Col
            print("</div>"); // End Row
            print("</div>"); // End container-fluid
        }
    }
    ?>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <script src="../../js/bootstrap.bundle.min.js"></script>
</body>

</html>