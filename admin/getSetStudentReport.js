function fetchCoursesList(){
    var courseList = null
    axios.get('http://localhost/COMP387_Assignment1/studentListReportHelper.php')
         .then(response => {
            courseList = response.data.data
         })
         .catch(error => console.error(error))
    
    return courseList
}

function fetchStudentList(){
    var studentList = null
    axios.get('http://localhost/COMP387_Assignment1/studentListReportHandler.php')
    .then(response => {
       studentList = response.data.data
    })
    .catch(error => console.error(error))

    return studentList
}
//On body load
function displayCourseList(){
    var results = fetchCoursesList()
    if (results?.length == 0){
        document.getElementById('courseListDiv').innerHTML = "<h3>There are no courses available for you.</h3>"
    }
    else {
        document.getElementById('courseListDiv').innerHTML =
            "<table>" +
            "<tr>" +
            "<th>Course Code</th>" +
            "<th>Title</th>" +
            "<th>Semester</th>" +
            "<th>Instructor</th>" +
            "<th></th>"

        for(let row of results){
            document.getElementById('courseListDiv').innerHTML +=
            "<tr>" +
            `<td>${row.courseCode}</td>` +
            `<td>${row.title}</td>` +
            `<td>${row.semester}</td>` +
            `<td>${row.instructor}</td>` +
            //"<td><form method=\"get\" action=\"http://localhost/COMP387_Assignment1/studentListReportHandler.php\">" +
            "<td><input type=\"button\" id=\"action\" value=\"Show class list\"/></td></tr>"
            //"<td><input type=\"submit\" name=\"action\" value=\"Show class list\"/>" + 
            //`<input type=\"hidden\" name=\"courseId\" value=\'${results.courseId}\'/></form></td></tr>`
            document.getElementById('action').addEventListener('click', () => {displayStudentList(results.courseId)})
        }

        document.getElementById('courseListDiv').innerHTML += "</table><br>"
    }   
}

function displayStudentList(){
    var results = fetchStudentList()
    if (results?.length == 0){
        document.getElementById('courseListDiv').innerHTML = "<h3>There are no students in this class.</h3>"
    }
    else {
        
    }
}