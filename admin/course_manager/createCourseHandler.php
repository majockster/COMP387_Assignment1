<?php
require_once("../../database/setup.php");

// Returns an array with the index indicating which course time selected a specific day
// e.g. For Mondays, they were  chosen for course time #1.
function getDays($day, $numOfCourseTimes)
{
    $arrayLength = count($day);
    $numOfChecked = $arrayLength - $numOfCourseTimes;

    $currentIndex = 0;
    $currentDayIndex = 0;
    $courseTimeDays = array();
    while ($numOfChecked > 0) {
        if ($day[$currentIndex + 1] != 'entry') {
            array_push($courseTimeDays, $currentDayIndex++);
            $numOfChecked--;
            $currentIndex++;
        } else {
            $currentDayIndex++;
        }
        $currentIndex++;
    }
    return $courseTimeDays;
}
if (isset($_POST)) {
    // Extract values from form
    $courseCode = $_POST['courseCode'];
    $courseTitle = $_POST['courseTitle'];
    $courseInstructor = $_POST['courseInstructor'];
    $courseSemester = $_POST['courseSemester'];
    $courseStartDate = $_POST['courseStartDate'];
    $courseEndDate = $_POST['courseEndDate'];
    $courseStartTimes = $_POST['courseStartTime'];
    $courseEndTimes = $_POST['courseEndTime'];
    $rooms = $_POST['courseRoom'];
    $mondays = $_POST['monday'];
    $tuesdays = $_POST['tuesday'];
    $wednesdays = $_POST['wednesday'];
    $thursdays = $_POST['thursday'];
    $fridays = $_POST['friday'];
    $sections = $_POST['courseSection'];
    $numberOfTimes = count($_POST['numberOfTimes']);


    // Ensure atomicity of sql transaction
    $conn->autocommit(FALSE);
    $conn->begin_transaction();


    // Creating Course
    $sql = "INSERT INTO courses(courseCode, title, semester, instructor, startDate, endDate) 
    VALUES ('$courseCode', '$courseTitle', '$courseSemester', '$courseInstructor', '$courseStartDate', '$courseEndDate')";
    $creatingCourse = mysqli_query($conn, $sql);

    // Creating Course Times
    $creatingSchedule = array();
    $courseID = mysqli_insert_id($conn);

    $mondayCourseTimes = getDays($mondays, $numberOfTimes);
    $tuesdayCourseTimes = getDays($tuesdays, $numberOfTimes);
    $wednesdayCourseTimes = getDays($wednesdays, $numberOfTimes);
    $thursdayCourseTimes = getDays($thursdays, $numberOfTimes);
    $fridayCourseTimes = getDays($fridays, $numberOfTimes);

    for ($i = 0; $i < $numberOfTimes; $i++) {

        if (in_array($i, $mondayCourseTimes)) {
            $sql = "INSERT INTO coursetimes(courseID, startTime, endTime, day, section, room) 
            VALUES ('$courseID', '$courseStartTimes[$i]', '$courseEndTimes[$i]', 'monday', '$sections[$i]', '$rooms[$i]')";
            array_push($creatingSchedule, mysqli_query($conn, $sql));
        }
        if (in_array($i, $tuesdayCourseTimes)) {
            $sql = "INSERT INTO coursetimes(courseID, startTime, endTime, day, section, room) 
            VALUES ('$courseID', '$courseStartTimes[$i]', '$courseEndTimes[$i]', 'tuesday', '$sections[$i]', '$rooms[$i]')";
            array_push($creatingSchedule, mysqli_query($conn, $sql));
        }
        if (in_array($i, $wednesdayCourseTimes)) {
            $sql = "INSERT INTO coursetimes(courseID, startTime, endTime, day, section, room) 
            VALUES ('$courseID', '$courseStartTimes[$i]', '$courseEndTimes[$i]', 'wednesday', '$sections[$i]', '$rooms[$i]')";
            array_push($creatingSchedule, mysqli_query($conn, $sql));
        }
        if (in_array($i, $thursdayCourseTimes)) {
            $sql = "INSERT INTO coursetimes(courseID, startTime, endTime, day, section, room) 
            VALUES ('$courseID', '$courseStartTimes[$i]', '$courseEndTimes[$i]', 'thursday', '$sections[$i]', '$rooms[$i]')";
            array_push($creatingSchedule, mysqli_query($conn, $sql));
        }
        if (in_array($i, $fridayCourseTimes)) {
            $sql = "INSERT INTO coursetimes(courseID, startTime, endTime, day, section, room) 
            VALUES ('$courseID', '$courseStartTimes[$i]', '$courseEndTimes[$i]', 'friday', '$sections[$i]', '$rooms[$i]')";
            array_push($creatingSchedule, mysqli_query($conn, $sql));
        }
    }

    // Commit if all sql queries were successful
    if ($creatingCourse && !in_array(FALSE, $creatingSchedule)) {
        mysqli_commit($conn);
    } else {
        mysqli_rollback($conn);
    }

    $conn->close();
}
