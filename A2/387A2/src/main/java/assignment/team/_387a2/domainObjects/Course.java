package assignment.team._387a2.domainObjects;

import java.util.Calendar;
import java.util.Date;

public class Course extends DomainObject
{
    int courseID;
    String courseCode;
    String title;
    String semester;
    String instructor;
    Date startDate;
    Date endDate;

    public Course()
    {
        courseID = -1;
        courseCode = "";
        title = "";
        semester = "";
        instructor = "";
        startDate = new Date();

        // Generating a course end date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_YEAR, 10);
        endDate = calendar.getTime();
    }

    public Course(int courseID, String courseCode, String title, String semester, String instructor, Date startDate, Date endDate) {
        this.courseID = courseID;
        this.courseCode = courseCode;
        this.title = title;
        this.semester = semester;
        this.instructor = instructor;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return getCourseID();
    }

    public int getCourseID() {
        return courseID;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getSemester() {
        return semester;
    }

    public String getInstructor() {
        return instructor;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
