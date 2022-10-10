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
</head>

<body>
    <h1>Administrator Page</h1>
    <h2><a href="course_manager/createCourse.php">Add a course</a></h2>
    <h2><a href="course_manager/deleteCourse.php">Delete a course</a></h2>
    <h2><a href="studentListReport.php">Access class list and get student list</a></h2>
    <h2><a href="classListReport.php">Access students list and get classes taken</a></h2>
</body>

</html>