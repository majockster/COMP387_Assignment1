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
            border-color: black;
            border-style: solid;
            border-radius: 10px;
        }
    </style>
</head>

<body>
    <form action="POST">
        <div class="form-group">
            <label for="courseCodeInput">Course Code</label>
            <input type="text" class="form-control" id="courseCodeInput" placeholder="e.g. SOEN387">
        </div>
        <div class="form-group">
            <label for="courseTitleInput">Course Title</label>
            <input type="text" class="form-control" id="courseTitleInput" placeholder="e.g. Web-Based Entreprise Design">
        </div>
        <div class="form-group">
            <div class="form-row">
                <div class="col">
                    <label for="courseSemesterList">Instructor</label>
                    <input class="form-control" id="courseInstructorList" list="courseInstructorOptions">
                    <datalist id="courseInstructorOptions" name="courseInstructorOptions">
                        <option value="Fake teacher 1">Fake teacher 1</option>
                        <option value="Fake teacher 2">Fake teacher 2</option>
                        <option value="Fake teacher 3">Fake teacher 3</option>
                    </datalist>
                </div>
                <div class="col">
                    <label for="courseInstructorList">Semester</label>
                    <input class="form-control" id="courseSemesterList" list="courseSemesterOptions">
                    <datalist id="courseSemesterOptions" name="courseSemesterOptions">
                        <option value="Fake semester 1">Fake semester 1</option>
                        <option value="Fake semester 2">Fake semester 2</option>
                        <option value="Fake semester 3">Fake semester 3</option>
                    </datalist>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label for="courseStartDateInput">Start Date</label>
            <input type="date" class="form-control" id="courseStartDateInput">
        </div>
        <div class="form-group">
            <label for="courseEndDateInput">End Date</label>
            <input type="date" class="form-control" id="courseEndDateInput">
        </div>
        <div class="form-group">

            <div class="form-row">
                <div class="col">
                    <label for="courseStartTimeInput">Start Time</label>
                    <input type="time" class="form-control" id="courseStartTimeInput">
                </div>
                <div class="col">
                    <label for="courseEndTimeInput">End Time</label>
                    <input type="time" class="form-control" id="courseEndTimeInput">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="monday" value="monday">
                <label class="form-check-label" for="monday">Monday</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="tuesday" value="tuesday">
                <label class="form-check-label" for="tuesday">Tuesday</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="wednesday" value="wednesday">
                <label class="form-check-label" for="wednesday">Wednesday</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="thursday" value="thursday">
                <label class="form-check-label" for="thursday">Thursday</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="friday" value="friday">
                <label class="form-check-label" for="friday">Friday</label>
            </div>
            <button type="button" class="btn btn-primary btn-sm">Add course times</button>
        </div>
        <button type="submit" class="btn btn-primary">Confirm</button>
        <button type="reset" class="btn btn-secondary">Reset</button>
    </form>


    <script src=" https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>

</html>