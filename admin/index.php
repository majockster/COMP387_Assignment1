<!DOCTYPE html">
<?php
// Setting user cookie.
$cookie_name = "admin_user";
$cookie_value = "2";
setcookie($cookie_name, $cookie_value, time() + (86400 * 30), "/"); // 86400 = 1 day
?>
<html>

<head>
    <title>Courses Management</title>
    <style type="text/css">
        body {
            background-color: #F0E68C
        }

        h2 {
            font-family: arial, sans-serif;
            color: blue
        }

        input {
            background-color: blue;
            color: yellow;
            font-weight: bold
        }
    </style>
    <link rel="stylesheet" type="text/css" href="/COMP387_Assignment1/css/bootstrap.min.css" />

</head>

<body>
    <div class="container-fluid">
        <div class="jumbotron text-center title" style="padding-top: 10px; padding-bottom: 10px">
            <h1>Administrator Page</h1>
        </div>
    </div>
    <div class="container-fluid" style="margin: 0 auto">
        <div class="container-fluid border border-3 rounded" style="margin: 0 auto; width: 60%; background-color : #ADD8E6">
            <h2><a href="course_manager/createCourse.php" class="link-secondary" style="text-decoration: none">Add a course</a></h2>
            <h2><a href="course_manager/deleteCourse.php" class="link-secondary" style="text-decoration: none">Delete a course</a></h2>
            <h2><a href="report_generator/studentListReport.php" class="link-secondary" style="text-decoration: none">Access class list and get student list</a></h2>
            <h2><a href="report_generator/classListReport.php" class="link-secondary" style="text-decoration: none">Access students list and get classes taken</a></h2>
            <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
            <script src="/js/bootstrap.bundle.min.js"></script>
        </div>
    </div>
</body>

</html>