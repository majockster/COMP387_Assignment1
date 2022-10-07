function checkEndTimeGreaterThanStartTime() {
  var startTimes = document.getElementsByClassName("course-start-time");
  var endTimes = document.getElementsByClassName("course-end-time");
  var errorMessages = document.getElementsByClassName("invalid-time-message");
  var invalidCount = 0;
  for (let i = 0; i < numberOfCourseTimes; i++) {
    if (startTimes[i].value >= endTimes[i].value) {
      startTimes[i].classList.add("invalid-time");
      errorMessages[i].style.display = "block";
      invalidCount++;
    } else {
      errorMessages[i].style.display = "none";
      startTimes[i].classList.remove("invalid-time");
    }
  }
  return invalidCount > 0 ? false : true;
}

function checkEndDateGreaterThanStartDate() {
  var startDate = document.getElementById("courseStartDate");
  var endDate = document.getElementById("courseEndDate");
  if (endDate.value > startDate.value) {
    document.getElementById("invalid-date").style.display = "none";
    startDate.style.backgroundColor = "#28a745";
    endDate.style.backgroundColor = "#28a745";
    startDate.style.borderColor = "#28a745";
    endDate.style.borderColor = "#28a745";
    return true;
  } else {
    document.getElementById("invalid-date").style.display = "block";
    startDate.style.backgroundColor = "lightcoral";
    endDate.style.backgroundColor = "lightcoral";
    return false;
  }
}

function checkIfDaysWereChosen() {
  var monday = document.getElementsByClassName("monday");
  var tuesday = document.getElementsByClassName("tuesday");
  var wednesday = document.getElementsByClassName("wednesday");
  var thursday = document.getElementsByClassName("thursday");
  var friday = document.getElementsByClassName("friday");
  var invalidDays = document.getElementsByClassName("invalid-day");
  var invalidCount = 0;

  for (let i = 0; i < numberOfCourseTimes; i++) {
    if (
      !(
        monday[i].checked ||
        tuesday[i].checked ||
        wednesday[i].checked ||
        thursday[i].checked ||
        friday[i].checked
      )
    ) {
      invalidDays[i].style.display = "block";
      invalidCount++;
    } else {
      invalidDays[i].style.display = "none";
    }
  }
  return invalidCount > 0 ? false : true;
}

function checkCourseRooms() {
  var courseRooms = document.getElementsByClassName("course-room");
  var invalidRooms = document.getElementsByClassName("invalid-room");
  var invalidCount = 0;
  for (let i = 0; i < numberOfCourseTimes; i++) {
    var validCourseRoom = /^(.|\s){3,150}$/.test(courseRooms[i].value);
    if (!validCourseRoom) {
      invalidCount++;
      invalidRooms[i].style.display = "block";
      courseRooms[i].style.backgroundColor = "lightcoral";
    } else {
      invalidRooms[i].style.display = "none";
      courseRooms[i].style.backgroundColor = "#28a745";
    }
  }
  return invalidCount > 0 ? false : true;
}

function checkCourseSections() {
  var courseSections = document.getElementsByClassName("course-section");
  var invalidSections = document.getElementsByClassName("invalid-section");
  var invalidCount = 0;
  for (let i = 0; i < numberOfCourseTimes; i++) {
    var validCourseSection = /^(.|\s){3,30}$/.test(courseSections[i].value);
    if (!validCourseSection) {
      invalidCount++;
      invalidSections[i].style.display = "block";
      courseSections[i].style.backgroundColor = "lightcoral";
    } else {
      invalidSections[i].style.display = "none";
      courseSections[i].style.backgroundColor = "#28a745";
    }
  }
  return invalidCount > 0 ? false : true;
}

function checkCourseCode() {
  var courseCode = document.getElementById("courseCode");
  var validCode = /^(\w|\d|\s){3,10}$/.test(courseCode.value);
  var invalidCodeMessage = document.getElementById("invalid-code-message");
  if (!validCode) {
    invalidCodeMessage.style.display = "block";
    courseCode.style.backgroundColor = "lightcoral";
  } else {
    invalidCodeMessage.style.display = "none";
    courseCode.style.backgroundColor = "#28a745";
  }
  return validCode;
}

function checkCourseTitle() {
  var courseTitle = document.getElementById("courseTitle");
  var validTitle = /^(.|\s){3,250}$/.test(courseTitle.value);
  var invalidTitleMessage = document.getElementById("invalid-title-message");
  if (!validTitle) {
    invalidTitleMessage.style.display = "block";
    courseTitle.style.backgroundColor = "lightcoral";
  } else {
    invalidTitleMessage.style.display = "none";
    courseTitle.style.backgroundColor = "#28a745";
  }
  return validTitle;
}

function checkInstructor() {
  var courseInstructor = document.getElementById("courseInstructor");
  var validInstructor = /^(.|\s){3,400}$/.test(courseInstructor.value);
  var invalidInstructorMessage = document.getElementById(
    "invalid-instructor-message"
  );
  if (!validInstructor) {
    invalidInstructorMessage.style.display = "block";
    courseInstructor.style.backgroundColor = "lightcoral";
  } else {
    invalidInstructorMessage.style.display = "none";
    courseInstructor.style.backgroundColor = "#28a745";
  }
  return validInstructor;
}

function checkSemester() {
  var courseSemester = document.getElementById("courseSemester");
  var validSemester = /^(\w|\d|\s){3,100}$/.test(courseSemester.value);
  var invalidSemesterMessage = document.getElementById(
    "invalid-semester-message"
  );
  if (!validSemester) {
    invalidSemesterMessage.style.display = "block";
    courseSemester.style.backgroundColor = "lightcoral";
  } else {
    invalidSemesterMessage.style.display = "none";
    courseSemester.style.backgroundColor = "#28a745";
  }
  return validSemester;
}

function runValidations() {
  var validations = [];
  validations.push(checkCourseCode());
  validations.push(checkCourseTitle());
  validations.push(checkInstructor());
  validations.push(checkSemester());
  validations.push(checkCourseRooms());
  validations.push(checkCourseSections());
  validations.push(checkEndTimeGreaterThanStartTime());
  validations.push(checkIfDaysWereChosen());
  validations.push(checkEndDateGreaterThanStartDate());
  if (!validations.includes(false)) {
    var form = document.getElementById("createCourseForm");
    form.requestSubmit();
  }
}
