package assignment.team._387a2.domainObjects;

public class CourseTime extends DomainObject
{
    int courseTimeID;
    int courseID;
    String  startTime;
    String endTime;
    String day;
    String section;
    String room;

    public void setCourseTimeID(int courseTimeID) {
        this.courseTimeID = courseTimeID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public CourseTime()
    {
        courseTimeID = -1;
        courseID = -1;
        startTime = "";
        endTime = "";
        day = "";
        section = "";
        room = "";
    }


    public CourseTime(int courseTimeID, int courseID, String startTime, String endTime, String day, String section, String room) {
        this.courseTimeID = courseTimeID;
        this.courseID = courseID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
        this.section = section;
        this.room = room;
    }

    public int getId() {
        return getCourseTimeID();
    }

    public int getCourseTimeID() {
        return courseTimeID;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getDay() {
        return day;
    }

    public String getSection() {
        return section;
    }

    public String getRoom() {
        return room;
    }
}
