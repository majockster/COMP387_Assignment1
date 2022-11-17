<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap.min.css"/>
    <title>Admin - Add a Course</title>
    <style type="text/css">
        form {
            background-color: #ADD8E6
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

        form {
            width: 50%;
            margin: 10px auto;
            padding: 20px;
            border-width: 2px;
        }

        .mb-3 {
            padding-left: 10px;
            padding-right: 10px;
        }

        .course-schedule {
            padding-top: 10px;
            padding-bottom: 10px;
        }

        .invalid-time {
            border-color: red;
            background-color: lightcoral;
        }

        .invalid-message {
            color: red;
        }
    </style>
</head>
<body>
<%@ include file="navbar_oneLevelDeep.jsp" %>
<div class="container-fluid">
    <div class="jumbotron text-center title" style="padding-top: 10px; padding-bottom: 10px">
        <h1>Create a Course</h1>
    </div>
</div>
<% String success = request.getParameter("success"); %>
<% if(success != null){
    if(success.equals("success")){
        success = "WOOHOO";%>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-4"></div>
        <div class="col-sm-4 justify-content-center style='margin-top : 20px'>'">
            <div class="alert alert-success text-center">
                <p>Added course successfully!</p>
            </div>
        </div>
    </div>
</div>
<%}}%>
<!-- Checking user cookie and authorization -->
<%--    <?php require("../checkIfAdmin.php"); ?>--%>
<form action="createCourseHandler.jsp" method="post" class="border border-primary rounded" id="createCourseForm" name="createCourseForm">
    <div class="mb-3">
        <label for="courseCode">Course Code</label>
        <input type="text" class="form-control" id="courseCode" name="courseCode" placeholder="e.g. SOEN387" required
               pattern="^(\w|\d|\s){3,8}$">
        <p class="invalid-message" id="invalid-code-message" style="display: none;">Must be between 3-10 characters.
            Must contain only letters, digits or whitespace.</p>
    </div>
    <div class="mb-3">
        <label for="courseTitle">Course Title</label>
        <input type="text" class="form-control" id="courseTitle" name="courseTitle"
               placeholder="e.g. Web-Based Entreprise Design" required pattern="^(.|\s){3,40}$">
        <p class="invalid-message" id="invalid-title-message" style="display: none;">Must be between 3-250
            characters.</p>
    </div>
    <div class="mb-3">
        <div class="row">
            <div class="col">
                <label for="courseInstructor">Instructor</label>
                <input class="form-control" id="courseInstructor" name="courseInstructor" required>
                <p class="invalid-message" id="invalid-instructor-message" style="display: none;">Must be between 3-400
                    characters.</p>
            </div>
            <div class="col">
                <label for="courseSemesterList">Semester</label>
                <input class="form-control" id="courseSemester" name="courseSemester" required>
                <p class="invalid-message" id="invalid-semester-message" style="display: none;">Must be between 3-100
                    characters. Must contain only letters, digits or whitespace.</p>
            </div>
        </div>
    </div>

    <div class="mb-3">
        <label for="courseStartDate">Start Date</label>
        <input type="date" class="form-control" id="courseStartDate" name="courseStartDate" required>
    </div>
    <div class="mb-3">
        <label for="courseEndDate">End Date</label>
        <input type="date" class="form-control" id="courseEndDate" name="courseEndDate" required>
        <p class="invalid-message" id="invalid-date" style="display : none;">End date must be greater than start
            date.</p>
    </div>

    <div class="mb-3 course-schedule border border-secondary rounded">
        <p class="font-weight-bold">Course Times</p>
        <div id="course-times">
            <div id="course-time">
                <input type="hidden" value="1" name="numberOfTimes">
                <div>
                    <label for="courseRoom">Room</label>
                    <input type="text" class="form-control course-room" id="courseRoom" name="courseRoom" required>
                    <p class="invalid-message invalid-room" style="display : none;">Must be between 3-150 chacaters.</p>
                </div>
                <div>
                    <label for="courseSection">Section</label>
                    <input type="text" class="form-control course-section" id="courseSection" name="courseSection"
                           required>
                    <p class="invalid-message invalid-section" style="display : none;">Must be between 3-30
                        chacaters.</p>
                </div>
                <div class="row">
                    <div class="col">
                        <label for="courseStartTime">Start Time</label>
                        <input type="time" class="form-control course-start-time" id="courseStartTime"
                               name="courseStartTime" required>
                    </div>
                    <div class="col">
                        <label for="courseEndTime">End Time</label>
                        <input type="time" class="form-control course-end-time" id="courseEndTime"
                               name="courseEndTime" required>
                    </div>
                    <p class="invalid-message invalid-time-message" style="display: none;">End time must be greater than
                        start time.</p>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="hidden" value="entry" name="monday">
                    <input class="form-check-input monday" type="checkbox" id="monday" value="monday" name="monday">
                    <label class="form-check-label">Monday</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="hidden" value="entry" name="tuesday">
                    <input class="form-check-input tuesday" type="checkbox" id="tuesday" value="tuesday"
                           name="tuesday">
                    <label class="form-check-label">Tuesday</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="hidden" value="entry" name="wednesday">
                    <input class="form-check-input wednesday" type="checkbox" id="wednesday" value="wednesday"
                           name="wednesday">
                    <label class="form-check-label">Wednesday</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="hidden" value="entry" name="thursday">
                    <input class="form-check-input thursday" type="checkbox" id="thursday" value="thursday"
                           name="thursday">
                    <label class="form-check-label">Thursday</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="hidden" value="entry" name="friday">
                    <input class="form-check-input friday" type="checkbox" id="friday" value="friday" name="friday">
                    <label class="form-check-label">Friday</label>
                </div>
                <p class="invalid-message invalid-day" style="display : none;">Please select a day.</p>

            </div>
        </div>
        <button type="button" class="btn btn-success" id="add-course-time" onclick="addAdditionalCourseTime()">Add
            additional course time
        </button>
        <button type="button" class="btn btn-danger" id="remove-course-time" onclick="removeCourseTime()" disabled=true>
            Remove course time
        </button>
    </div>
    <input type="hidden" name="createCourseRequest" value="true">
    <input class="btn btn-primary" type="button" name="createCourse" value="Confirm" onclick="runValidations()"></input>
    <button type="reset" class="btn btn-secondary">Reset</button>
</form>


<script src="../../scripts/validateCreateCourseForm.js"></script>
<script src="../../scripts/course_times.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script src="../../js/bootstrap.bundle.min.js"></script>
</body>
</html>
