<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ page import = "assignment.team._387a2.helperObjects.SQLConnection" %>
<%@ page import="javax.servlet.http.Cookie" %>
<%@ page import="assignment.team._387a2.helperObjects.CookieHelper" %>
<%@ page import="assignment.team._387a2.tableGateways.CourseTableGateway" %>
<%@ page import="assignment.team._387a2.tableGateways.StudentTableGateway" %>
<%@ page import="assignment.team._387a2.rowGateways.StudentGateway" %>
<%@ page import="assignment.team._387a2.rowGateways.CourseGateway" %>
<%@ page import="assignment.team._387a2.dataObjects.SemesterCourses" %>
<%@ page import="assignment.team._387a2.tableGateways.PersonTableGateway" %>
<%@ page import="java.util.Date" %>
<%@ page import="assignment.team._387a2.rowGateways.RegistrationGateway" %>
<%@ page import="assignment.team._387a2.tableGateways.RegistrationTableGateway" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>Admin - Student List Report</title>
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
    <%@ include file = "navbar_oneLevelDeep.jsp" %>

    <div class="container-fluid">
        <div class="jumbotron text-center title" style="padding-top: 10px; padding-bottom: 10px">
            <h1>Student List Report</h1>
        </div>
    </div>
    <!-- Checking user cookie and authorization -->
    <%@ include file = "../checkIfAdmin.jsp" %>
    <%
        SQLConnection connection = new SQLConnection();
        // Creating Gateways
        CourseTableGateway courseTable = new CourseTableGateway();
        PersonTableGateway personTable = new PersonTableGateway();
        StudentTableGateway studentTable = new StudentTableGateway();
        RegistrationTableGateway registrationTable = new RegistrationTableGateway();
    %>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-2"></div>
            <div class="col-sm-8">
                <h2>List of all courses</h2>
            </div>
            <div class="col-sm-2"></div>
        </div>
    </div>
    <%
        List<CourseGateway> getAllCourses = courseTable.getAll();
        if (getAllCourses.size() > 0)
        {
    %>
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-sm-2"></div>
            <div class="col-sm-8">
                <div class="">            
                    <table class="table table-responsive table-striped table-bordered table-hover">  
                        <tr>
                        <th>Course ID</th> 
                        <th>Course Code</th>
                        <th>Title</th>
                        <th>Semester</th>
                        <th>Instructor</th>
                        <th></th>
                        <%
                            for (CourseGateway course : getAllCourses)
                            {    
                        %>
                            <tr>
                                <td><%=course.getCourseID()%></td>
                                <td><%=course.getCourseCode()%></td>
                                <td><%=course.getTitle()%></td>
                                <td><%=course.getSemester()%></td>
                                <td><%=course.getInstructor()%></td>
                                <td>
                                    <form class="justify-content-center text-center" method="get" action="">
                                        <input 
                                        class="btn btn-primary" 
                                        type="submit" 
                                        name="action" 
                                        value="Show class list"/>
                                        <input 
                                        type="hidden" 
                                        name="courseId" 
                                        value="<%=course.getCourseID()%>"/>
                                        <input 
                                        type="hidden" 
                                        name="courseCode" 
                                        value="<%=course.getCourseCode()%>"/>
                                    </form>
                                </td>
                            </tr>
                        <%
                            }
                        %>
                    </table>
                </div>
            </div>
            <div class="col-sm-2"></div>
        </div>
    </div>
    <%
        }
        else
        {
    %>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-4"></div>
            <div class="col-sm-4">
                <h3>There are no courses.</h3>
                <br />
            </div>
            <div class="col-sm-4"></div>
        </div>
    </div>
    <%
        }

        if (request.getParameter("courseId") == null ||
            request.getParameter("courseCode") == null){
            return;
        }
        // Loading post parameters
        String action = request.getParameter("action");
        int courseId = !request.getParameter("courseId").isEmpty() ? Integer.parseInt(request.getParameter("courseId")) : -1;
        String courseCode = request.getParameter("courseCode");


        if ((action != null && !action.isEmpty()) &&
            (courseId != -1) &&
            (courseCode != null && !courseCode.isEmpty()))
        {
            List<StudentGateway> getStudentListFromCourse = courseTable.getStudentListByCourseId(courseId);

            if (getStudentListFromCourse.size() > 0)
            {
    %>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-2"></div>
            <div class="col-sm-8">
                <h2>List of students enrolled in class <%=courseCode%>:</h2>
            </div>
            <div class="col-sm-2"></div>
        </div>
    </div>
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-sm-2"></div>
            <div class="col-sm-8">
                <table class="table table-responsive table-striped table-bordered table-hover">  
                    <tr> 
                    <th>Student ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th></th>
                    <%
                        for (StudentGateway student : getStudentListFromCourse)
                        {
                    %>
                    <tr>
                    <td><%= student.getStudentId()%></td>
                    <td><%= personTable.findById(student.getPersonId()).getFirstName()%></td>
                    <td><%= personTable.findById(student.getPersonId()).getLastName()%></td>
                    <%
                        }
                    %>
                </table>
            </div>
            <div class="col-sm-2"></div>
        </div>
    </div>
    <%
            }
            else
            {
    %>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-4"></div>
            <div class="col-sm-4">
                <h3>There are no students enrolled in class <%=courseCode%>.</h3>
                <br />
            </div>
            <div class="col-sm-4"></div>
        </div>
    </div>
    <%
            }   
        }
    %>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <script src="../../js/bootstrap.bundle.min.js"></script>
</body>

</html>