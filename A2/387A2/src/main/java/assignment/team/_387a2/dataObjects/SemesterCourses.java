package assignment.team._387a2.dataObjects;

import java.util.Calendar;
import java.util.Date;

public class SemesterCourses
{
    private int courseCount;
    private String semester;
    private Date startDate;
    private Date endDate;

    public SemesterCourses()
    {
        courseCount = -1;
        semester = "";
        startDate = new Date();

        // Generating a semester end date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_YEAR, 10);
        endDate = calendar.getTime();
    }

    public SemesterCourses(int courseCount, String semester, Date startDate, Date endDate) {
        this.courseCount = courseCount;
        this.semester = semester;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public int getCourseCount() {
        return courseCount;
    }

    public String getSemester() {
        return semester;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setCourseCount(int courseCount) {
        this.courseCount = courseCount;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
