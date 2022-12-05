package assignment.team._387a2.tableGateways;

import assignment.team._387a2.dataObjects.SemesterCourses;
import assignment.team._387a2.domainObjects.Student;
import assignment.team._387a2.domainObjects.Course;
import assignment.team._387a2.mappers.CourseMapper;

import java.util.List;


public class CourseTableGateway
{
    private CourseMapper courseMapper;

    public CourseTableGateway()
    {
        courseMapper = new CourseMapper();
    }

    public Course findById(int pId)
    {
        return courseMapper.find(pId);
    }

    public Course findByCourseCode(String pCourseCode)
    {
        return courseMapper.findByCourseCode(pCourseCode);
    }

    public List<Course> getAll()
    {
        return courseMapper.getAll();
    }

    public List<Student> getStudentListByCourseId(int courseId)
    {
        return courseMapper.getStudentListByCourseId(courseId);
    }

    public List<Course> getCoursesByStudentId(int pStudentId)
    {
        return courseMapper.getCoursesByStudentId(pStudentId);
    }

    public List<Course> getCoursesByPersonId(int pPersonId)
    {
        return courseMapper.getCoursesByPersonId(pPersonId);
    }

    public List<SemesterCourses> getCoursesBySemesterForPersonId(int pPersonId)
    {
        return courseMapper.getCoursesBySemesterForPersonId(pPersonId);
    }

    public List<Course> getCoursesAvailableToStudent(int pStudentId)
    {
        return courseMapper.getCoursesAvailableToStudent(pStudentId);
    }

    public void updateCourse(Course pCourse)
    {
        courseMapper.update(pCourse);
    }

    public void insertCourse(Course pCourse)
    {
        courseMapper.insert(pCourse);
    }

    public void deleteCourse(Course pCourse)
    {
        courseMapper.delete(pCourse);
    }

    public void deleteCourseById(int pCourseID)
    {
        courseMapper.deleteCourseById(pCourseID);
    }
}
