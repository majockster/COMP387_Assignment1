<%@ page import="java.util.ArrayList" %>
<%@ page import="assignment.team._387a2.rowGateways.CourseGateway" %>
<%@ page import="java.util.Date" %>
<%@ page import="assignment.team._387a2.tableGateways.CourseTableGateway" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="assignment.team._387a2.rowGateways.CourseTimeGateway" %>
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
    String courseStartTimes = request.getParameter("courseStartTimes");
    String courseEndTimes = request.getParameter("courseEndTimes");
    String courseRoom = request.getParameter("courseRoom");
    String[] monday = request.getParameterValues("monday");
    String[] tuesday = request.getParameterValues("tuesday");
    String[] wednesday = request.getParameterValues("wednesday");
    String[] thursday = request.getParameterValues("thursday");
    String[] friday = request.getParameterValues("friday");
    String courseSection = request.getParameter("courseSection");
    String[] entries = request.getParameterValues("numberOfTimes");
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

    // Inserting the course times
    for(int i = 0; i < numberOfTimes; i++){
        Integer intI = Integer.valueOf(i);
        if(mondays.contains(intI)){
            CourseTimeGateway courseTimeGateway = new CourseTimeGateway()
        }
    }
%>
<%= mondays %>
<% for(String entry : monday){%>

<%= entry %>

<%}%>

