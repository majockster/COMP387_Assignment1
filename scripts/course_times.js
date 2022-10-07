var numberOfCourseTimes = 1;

function addAdditionalCourseTime() {
  let parentNode = document.getElementById("course-times");
  let courseTime = document.getElementById("course-time");
  let courseTimeClone = courseTime?.cloneNode(true);
  parentNode?.appendChild(courseTimeClone);
  document.getElementById("remove-course-time").disabled = false;
  numberOfCourseTimes++;
}

function removeCourseTime() {
  let allCourseTimes = document.getElementById("course-times");
  if (document.getElementById("course-times")?.childElementCount == 2) {
    allCourseTimes.removeChild(allCourseTimes.lastChild);
    document.getElementById("remove-course-time").disabled = true;
    numberOfCourseTimes--;
  } else if (document.getElementById("course-times")?.childElementCount > 2) {
    document.getElementById("remove-course-time").disabled = false;
    allCourseTimes.removeChild(allCourseTimes.lastChild);
    numberOfCourseTimes--;
  }
}
