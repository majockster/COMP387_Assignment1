<%@ page import="assignment.team._387a2.helperObjects.SQLConnection" %>
<%@ page import="assignment.team._387a2.tableGateways.CourseTableGateway" %>
<%@ page import="assignment.team._387a2.domainObjects.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head class="page-header header container-fluid-fluid">
    <title>Admin - Delete a Course</title>
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
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap.min.css"/>
</head>

<body>
<%@ include file="navbar_oneLevelDeep.jsp" %>
<%@ include file="../checkIfAdmin.jsp" %>

<div class="container-fluid">
    <div class="jumbotron text-center title" style="padding-top: 10px; padding-bottom: 10px">
        <h1>Delete courses</h1>
    </div>
</div>
<!-- Checking user cookie and authorization -->
<%
    // Load cookies
    Map<String, Cookie> cookies = CookieHelper.ConvertRequestCookies(request);
    // DEBUG
    cookies.put("personID", new Cookie("personID", "1"));
    cookies.put("firstName", new Cookie("firstName", "Luke"));
    cookies.put("lastName", new Cookie("lastName", "Skywalker"));
    // END DEBUG

    if (cookies.get("firstName") == null) {
        Cookie newCookie = new Cookie("firstName", "Guest");
        cookies.put("firstName", newCookie);
    }
    if (cookies.get("lastName") == null) {
        Cookie newCookie = new Cookie("lastName", "");
        cookies.put("lastName", newCookie);
    }

    // Check Database Connection
    // Warning: Currently, will only crash the program if the connection cant be made.
    SQLConnection connection = new SQLConnection();


    // Creating Gateways
    CourseTableGateway courseTable = new CourseTableGateway();

%>

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
    // Loading post parameters
    String action = request.getParameter("action");
    String courseId = request.getParameter("courseId");
    String semester = request.getParameter("semester");

    // Select deletable courses
    List<Course> deletable = courseTable.getAll();

    // Check if we clicked a button
    if ((action != null && !action.isEmpty()) &&
            (courseId != null && !courseId.isEmpty()) &&
            (semester != null && !semester.isEmpty())
    ) {
        if (action.equals("Delete")) {
            courseTable.deleteCourseById(Integer.parseInt(courseId));
            deletable = courseTable.getAll();

%>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-4">
        </div>
        <%--// End First Col--%>
        <div class="col-sm-4 justify-content-center">
            <div class="alert alert-success text-center">
                <p>Dropped course successfully!</p>
            </div>
        </div>
        <%--// End Second Col--%>
        <div class="col-sm-4">
        </div>
        <%--// End Third Col--%>
    </div>
    <%--// End Row--%>
</div>
<%--// End container-fluid--%>
<%
        }

    }

    if (deletable.size() > 0) {
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
                    for (Course course : deletable) {
                %>
                <tr>
                    <td><%= course.getCourseCode() %>
                    </td>
                    <td><%= course.getTitle() %>
                    </td>
                    <td><%= course.getSemester() %>
                    </td>
                    <td><%= course.getInstructor() %>
                    </td>
                    <td><%= course.getStartDate() %>
                    </td>
                    <td><%= course.getEndDate() %>
                    </td>
                    <!-- // Clickable button to delete a course -->
                    <td>
                        <form class="justify-content-center text-center" method="post" action="">
                            <input class="btn btn-primary" type="submit" name="action" value="Delete"/>
                            <input type="hidden" name="courseId" value="<%= course.getId() %>"/>
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
} else {
%>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-4">
        </div> <!-- End First Col -->
        <div class="col-sm-4">
            <h3>There are no courses at this time.</h3>
            <br/>
        </div> <!-- End Second Col -->
        <div class="col-sm-4">
        </div> <!-- End Third Col -->
    </div> <!-- End Row -->
</div> <!-- End container-fluid -->
<%
    }
%>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script src="../../js/bootstrap.bundle.min.js"></script>
</body>


</html>