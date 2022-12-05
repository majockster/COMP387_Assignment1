<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ page import = "assignment.team._387a2.helperObjects.SQLConnection" %>
<%@ page import="javax.servlet.http.Cookie" %>
<%@ page import="assignment.team._387a2.helperObjects.CookieHelper" %>
<%@ page import="assignment.team._387a2.tableGateways.CourseTableGateway" %>
<%@ page import="assignment.team._387a2.tableGateways.StudentTableGateway" %>
<%@ page import="assignment.team._387a2.domainObjects.Student" %>
<%@ page import="assignment.team._387a2.domainObjects.Course" %>
<!DOCTYPE html">
<html>

<head>
    <title>Admin - Home</title>
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
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css" />

</head>

<body>
    <%@ include file = "navbar.jsp" %>
    <!-- Checking user cookie and authorization -->
	<%-- <?jsp require("checkIfAdmin.jsp"); ?> --%>
    <div class="container-fluid">
        <div class="jumbotron text-center title" style="padding-top: 10px; padding-bottom: 10px">
            <h1>Administrator Page</h1>
        </div>
    </div>
    <div class="container-fluid" style="margin: 0 auto">
        <div class="container-fluid border border-3 rounded" style="margin: 0 auto; width: 60%; background-color : #ADD8E6">
            <h2><a href="course_manager/createCourse.jsp" class="link-secondary" style="text-decoration: none">Add a course</a></h2>
            <h2><a href="course_manager/deleteCourse.jsp" class="link-secondary" style="text-decoration: none">Delete a course</a></h2>
            <h2><a href="report_generator/studentListReport.jsp" class="link-secondary" style="text-decoration: none">Access class list and get student list</a></h2>
            <h2><a href="report_generator/classListReport.jsp" class="link-secondary" style="text-decoration: none">Access students list and get classes taken</a></h2>
            <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
            <script src="../js/bootstrap.bundle.min.js"></script>
        </div>
    </div>
</body>

</html>