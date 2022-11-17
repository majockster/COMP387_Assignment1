<%@ page import="java.util.ArrayList" %>
<%@ page import="assignment.team._387a2.rowGateways.CourseGateway" %>
<%@ page import="java.util.Date" %>
<%@ page import="assignment.team._387a2.tableGateways.CourseTableGateway" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="assignment.team._387a2.rowGateways.CourseTimeGateway" %>
<%@ page import="assignment.team._387a2.tableGateways.CourseTimeTableGateway" %>
<%!
    // Function to extract days for course times
    public ArrayList<Integer> getDays(String[] aDay, int numOfCourseTimes){
        int arrayLength = aDay.length;
        int numOfChecked = arrayLength - numOfCourseTimes;

        int currentIndex = 0;
        int currentDayIndex = 0;
        ArrayList<Integer> courseTimeDays = new ArrayList<Integer>();

        // e.g. [entry monday entry entry monday]

        while(numOfChecked > 0){
            if(!aDay[currentIndex + 1].equals("entry")) {
                courseTimeDays.add(currentDayIndex);
                currentDayIndex++;
                numOfChecked--;
                currentIndex++;
            } else {
                currentDayIndex++;
            }
            currentIndex++;
        }
        return courseTimeDays;
    }
%>
<%
    String courseCode = request.getParameter("courseCode");
    String courseTitle = request.getParameter("courseTitle");
    String courseInstructor = request.getParameter("courseInstructor");
    String courseSemester = request.getParameter("courseSemester");
    String courseStartString = request.getParameter("courseStartDate");
    String courseEndString = request.getParameter("courseEndDate");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date courseStartDate = sdf.parse(courseStartString);
    Date courseEndDate = sdf.parse(courseEndString);
    String[] courseStartTimes = request.getParameterValues("courseStartTime");
    String[] courseEndTimes = request.getParameterValues("courseEndTime");
    String[] courseRoom = request.getParameterValues("courseRoom");
    String[] monday = request.getParameterValues("monday");
    String[] tuesday = request.getParameterValues("tuesday");
    String[] wednesday = request.getParameterValues("wednesday");
    String[] thursday = request.getParameterValues("thursday");
    String[] friday = request.getParameterValues("friday");
    String[] courseSection = request.getParameterValues("courseSection");
    int numberOfTimes = (request.getParameterValues("numberOfTimes")).length;
%>
<%
    ArrayList<Integer> mondays = getDays(monday, numberOfTimes);
    ArrayList<Integer> tuesdays = getDays(tuesday, numberOfTimes);
    ArrayList<Integer> wednesdays = getDays(wednesday, numberOfTimes);
    ArrayList<Integer> thursdays = getDays(thursday, numberOfTimes);
    ArrayList<Integer> fridays = getDays(friday, numberOfTimes);


    // Inserting the courses
    CourseGateway cg = new CourseGateway(-1, courseCode, courseTitle, courseSemester, courseInstructor, courseStartDate,
            courseEndDate);
    CourseTableGateway courseTableGateway = new CourseTableGateway();
    courseTableGateway.insertCourse(cg);
    int courseId = cg.getCourseID();


    // Inserting the course times
    CourseTimeTableGateway courseTimeTableGateway = new CourseTimeTableGateway();
    for(int i = 0; i < numberOfTimes; i++){
        Integer intI = Integer.valueOf(i);
        if(mondays.contains(intI)){
            CourseTimeGateway courseTimeGateway = new CourseTimeGateway(-1, courseId, courseStartTimes[i],
                    courseEndTimes[i],
                    "monday",
                    courseSection[i],
                    courseRoom[i]);
            courseTimeTableGateway.insertCourseTime(courseTimeGateway);
        }
        if(tuesdays.contains(intI)){
            CourseTimeGateway courseTimeGateway = new CourseTimeGateway(-1, courseId, courseStartTimes[i],
                    courseEndTimes[i],
                    "tuesday",
                    courseSection[i],
                    courseRoom[i]);
            courseTimeTableGateway.insertCourseTime(courseTimeGateway);
        }
        if(wednesdays.contains(intI)){
            CourseTimeGateway courseTimeGateway = new CourseTimeGateway(-1, courseId, courseStartTimes[i],
                    courseEndTimes[i],
                    "wednesday",
                    courseSection[i],
                    courseRoom[i]);
            courseTimeTableGateway.insertCourseTime(courseTimeGateway);
        }
        if(thursdays.contains(intI)){
            CourseTimeGateway courseTimeGateway = new CourseTimeGateway(-1, courseId, courseStartTimes[i],
                    courseEndTimes[i],
                    "thursday",
                    courseSection[i],
                    courseRoom[i]);
            courseTimeTableGateway.insertCourseTime(courseTimeGateway);
        }
        if(fridays.contains(intI)){
            CourseTimeGateway courseTimeGateway = new CourseTimeGateway(-1, courseId, courseStartTimes[i],
                    courseEndTimes[i],
                    "friday",
                    courseSection[i],
                    courseRoom[i]);
            courseTimeTableGateway.insertCourseTime(courseTimeGateway);
        }
    }
%>
<jsp:forward page="createCourse.jsp">
    <jsp:param name="success" value="success"/>
</jsp:forward>
