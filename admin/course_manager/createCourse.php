<!DOCTYPE html">
<?php
// Setting user cookie.
$cookie_name = "admin_user";
$cookie_value = "2";
setcookie($cookie_name, $cookie_value, time() + (86400 * 30), "/"); // 86400 = 1 day
?>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Add a Course</title>
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

        form {
            width: 50%;
            margin: 10px auto;
            padding: 20px;
            border-width: 2px;
        }

        .form-group {
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
    <form action="createCourseHandler.php" method="post" class="border border-primary rounded" id="createCourseForm" name="createCourseForm">
        <div class="form-group">
            <label for="courseCode">Course Code</label>
            <input type="text" class="form-control" id="courseCode" name="courseCode" placeholder="e.g. SOEN387" required pattern="^(\w|\d|\s){3,8}$">
            <p class="invalid-message" id="invalid-code-message" style="display: none;">Must be between 3-10 characters. Must contain only letters, digits or whitespace.</p>
        </div>
        <div class="form-group">
            <label for="courseTitle">Course Title</label>
            <input type="text" class="form-control" id="courseTitle" name="courseTitle" placeholder="e.g. Web-Based Entreprise Design" required pattern="^(.|\s){3,40}$">
            <p class="invalid-message" id="invalid-title-message" style="display: none;">Must be between 3-250 characters.</p>
        </div>
        <div class="form-group">
            <div class="form-row">
                <div class="col">
                    <label for="courseInstructor">Instructor</label>
                    <input class="form-control" id="courseInstructor" name="courseInstructor" required>
                    <p class="invalid-message" id="invalid-instructor-message" style="display: none;">Must be between 3-400 characters.</p>
                </div>
                <div class="col">
                    <label for="courseSemesterList">Semester</label>
                    <input class="form-control" id="courseSemester" name="courseSemester" required>
                    <p class="invalid-message" id="invalid-semester-message" style="display: none;">Must be between 3-100 characters. Must contain only letters, digits or whitespace.</p>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label for="courseStartDate">Start Date</label>
            <input type="date" class="form-control" id="courseStartDate" name="courseStartDate" required>
        </div>
        <div class="form-group">
            <label for="courseEndDate">End Date</label>
            <input type="date" class="form-control" id="courseEndDate" name="courseEndDate" required>
            <p class="invalid-message" id="invalid-date" style="display : none;">End date must be greater than start date.</p>
        </div>

        <div class="form-group course-schedule border border-secondary rounded">
            <p class="font-weight-bold">Course Times</p>
            <div id="course-times">
                <div id="course-time">
                    <input type="hidden" value="1" name="numberOfTimes[]">
                    <div>
                        <label for="courseRoom">Room</label>
                        <input type="text" class="form-control course-room" id="courseRoom" name="courseRoom[]" required>
                        <p class="invalid-message invalid-room" style="display : none;">Must be between 3-150 chacaters.</p>
                    </div>
                    <div>
                        <label for="courseSection">Section</label>
                        <input type="text" class="form-control course-section" id="courseSection" name="courseSection[]" required>
                        <p class="invalid-message invalid-section" style="display : none;">Must be between 3-30 chacaters.</p>
                    </div>
                    <div class="form-row">
                        <div class="col">
                            <label for="courseStartTime">Start Time</label>
                            <input type="time" class="form-control course-start-time" id="courseStartTime" name="courseStartTime[]" required>
                        </div>
                        <div class="col">
                            <label for="courseEndTime">End Time</label>
                            <input type="time" class="form-control course-end-time" id="courseEndTime" name="courseEndTime[]" required>
                        </div>
                        <p class="invalid-message invalid-time-message" style="display: none;">End time must be greater than start time.</p>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="hidden" value="entry" name="monday[]">
                        <input class="form-check-input monday" type="checkbox" id="monday" value="monday" name="monday[]">
                        <label class="form-check-label">Monday</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="hidden" value="entry" name="tuesday[]">
                        <input class="form-check-input tuesday" type="checkbox" id="tuesday" value="tuesday" name="tuesday[]">
                        <label class="form-check-label">Tuesday</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="hidden" value="entry" name="wednesday[]">
                        <input class="form-check-input wednesday" type="checkbox" id="wednesday" value="wednesday" name="wednesday[]">
                        <label class="form-check-label">Wednesday</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="hidden" value="entry" name="thursday[]">
                        <input class="form-check-input thursday" type="checkbox" id="thursday" value="thursday" name="thursday[]">
                        <label class="form-check-label">Thursday</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="hidden" value="entry" name="friday[]">
                        <input class="form-check-input friday" type="checkbox" id="friday" value="friday" name="friday[]">
                        <label class="form-check-label">Friday</label>
                    </div>
                    <p class="invalid-message invalid-day" style="display : none;">Please select a day.</p>

                </div>
            </div>
            <button type="button" class="btn btn-success" id="add-course-time" onclick="addAdditionalCourseTime()">Add additional course time</button>
            <button type="button" class="btn btn-danger" id="remove-course-time" onclick="removeCourseTime()" disabled=true>Remove course time</button>
        </div>
        <button type="button" onclick="runValidations()" class="btn btn-primary">Confirm</button>
        <button type="reset" class="btn btn-secondary">Reset</button>
    </form>


    <script src="../../scripts/validateCreateCourseForm.js"></script>
    <script src="../../scripts/course_times.js"></script>
    <script src=" https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>

</html>