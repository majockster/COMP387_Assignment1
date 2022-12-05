package assignment.team._387a2.mappers;

import assignment.team._387a2.dataObjects.SemesterCourses;
import assignment.team._387a2.domainObjects.Course;
import assignment.team._387a2.domainObjects.DomainObject;
import assignment.team._387a2.domainObjects.Student;
import assignment.team._387a2.helperObjects.SQLConnection;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CourseMapper extends DataMapper{
    @Override
    public Course find(int id) {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM courses WHERE courseID = " + id + ";";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<Course> courses = convertToCourses(result);

        Course course = courses == null || courses.size() == 0 ? null : courses.get(0);

        connection.Close();

        return course;
    }

    @Override
    public void update(DomainObject c) {
        SQLConnection connection = new SQLConnection();
        Course course = (Course) c;

        // Converting the date to SQL format
        String format = "yyyy-MM-dd HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(format);
        String formattedStartDate = dateFormat.format(course.getStartDate());
        String formattedEndDate = dateFormat.format(course.getEndDate());

        String updateQuery = "UPDATE Courses " +
                "SET courseCode = '" + course.getCourseCode() + "' , " +
                "title = '" + course.getTitle() + "' , " +
                "semester = '" + course.getSemester() + "' , " +
                "instructor = '" + course.getInstructor() + "' , " +
                "startDate = '" + formattedStartDate + "' , " +
                "endDate = '" + formattedEndDate + "' " +
                "WHERE courseID = " + Integer.toString(course.getId()) + ";";

        connection.ExecuteNoReturn(updateQuery);

        connection.Close();
    }

    @Override
    public void insert(DomainObject c) {
        SQLConnection connection = new SQLConnection();
        Course course = (Course) c;

        // Converting the date to SQL format
        String format = "yyyy-MM-dd HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(format);
        String formattedStartDate = dateFormat.format(course.getStartDate());
        String formattedEndDate = dateFormat.format(course.getEndDate());

        String insertQuery = "INSERT INTO Courses(courseCode, title, semester, instructor, startDate, endDate) " +
                "VALUES ('" + course.getCourseCode() + "', " +
                "'" + course.getTitle() + "', " +
                "'" + course.getSemester() + "', " +
                "'" + course.getInstructor() + "', " +
                "'" + formattedStartDate + "', " +
                "'" + formattedEndDate + "');";

        connection.ExecuteNoReturn(insertQuery);

        // Updating person ID to the proper value.
        List<Course> listCourses = getAll();
        Course newCourse = listCourses.get(listCourses.size()-1);
        course.setCourseID(newCourse.getId());

        connection.Close();
    }

    @Override
    public void delete(DomainObject c) {
        SQLConnection connection = new SQLConnection();
        Course course = (Course) c;

        String deleteQuery = "DELETE FROM Courses WHERE courseID = " + course.getId() + ";";

        connection.ExecuteNoReturn(deleteQuery);

        connection.Close();
    }

    public List<Course> getAll()
    {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM courses;";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<Course> Courses = convertToCourses(result);

        connection.Close();

        return Courses;
    }

    public void deleteCourseById(int pCourseID)
    {
        SQLConnection connection = new SQLConnection();

        String deleteQuery = "DELETE FROM Courses WHERE courseID = " + pCourseID + ";";

        connection.ExecuteNoReturn(deleteQuery);

        connection.Close();
    }

    public List<Course> getCoursesAvailableToStudent(int pStudentId)
    {
        SQLConnection connection = new SQLConnection();

        // Select available courses to register to.
        // Essentially, all courses that the student is not registered to.
        String availableCoursesQuery =
                """
                SELECT Courses.CourseId, Courses.courseCode, Courses.title, Courses.semester, courses.instructor, courses.startDate, courses.endDate 
                            FROM Courses 
                            EXCEPT
                            SELECT Courses.CourseId, Courses.courseCode, Courses.title, Courses.semester, courses.instructor, courses.startDate, courses.endDate 
                            FROM Courses
                            INNER JOIN Registrations
                            ON Courses.courseID = Registrations.courseID
                            INNER JOIN Student
                            ON Registrations.studentID = Student.studentID
                            WHERE Student.studentID = """ + pStudentId +
                        " ORDER BY courseCode ASC";

        ResultSet result = connection.ExecuteQuery(availableCoursesQuery);

        List<Course> Courses = convertToCourses(result);

        connection.Close();

        return Courses;
    }

    public Course findByCourseCode(String pCourseCode)
    {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM courses WHERE courseCode = '" + pCourseCode + "' ;";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<Course> courses = convertToCourses(result);

        Course Course = courses == null || courses.size() == 0 ? null : courses.get(0);

        connection.Close();

        return Course;
    }

    public List<Student> getStudentListByCourseId(int courseId)
    {
        SQLConnection connection = new SQLConnection();
        String selectStudentList = """
            SELECT Student.studentID, Student.personID, firstName, lastName, password, address, email, phoneNumber, dateOfBirth
                FROM Student
                INNER JOIN Person
                ON Student.personID = Person.personID
                INNER JOIN Registrations
                ON Student.studentID = Registrations.studentID
                WHERE Registrations.courseID =""" + courseId + " " +
                "ORDER BY Student.studentID";

        ResultSet result = connection.ExecuteQuery(selectStudentList);

        StudentMapper studentMapper = new StudentMapper();

        List<Student> students = studentMapper.convertToStudents(result);

        connection.Close();

        return students;
    }

    public List<Course> getCoursesByStudentId(int pStudentId)
    {
        SQLConnection connection = new SQLConnection();

        // Select available courses to drop.
        // Essentially, all courses that the student is registered to.

        String selectDroppable = """
            SELECT Courses.CourseId, Courses.courseCode, Courses.title,
			Courses.semester, courses.instructor, courses.startDate, courses.endDate
			FROM Courses
			INNER JOIN Registrations
			ON Courses.courseID = Registrations.courseID
			INNER JOIN Student
			ON Registrations.studentID = Student.studentID
			WHERE Student.studentID =""" + pStudentId + " " +
                "ORDER BY courseCode ASC";

        ResultSet result = connection.ExecuteQuery(selectDroppable);

        List<Course> Courses = convertToCourses(result);

        connection.Close();

        return Courses;
    }

    public List<Course> getCoursesByPersonId(int pPersonId)
    {
        SQLConnection connection = new SQLConnection();

        // Select available courses to drop.
        // Essentially, all courses that the student is registered to.

        String selectDroppable = """
            SELECT Courses.CourseId, Courses.courseCode, Courses.title,
			Courses.semester, courses.instructor, courses.startDate, courses.endDate
			FROM Courses
			INNER JOIN Registrations
			ON Courses.courseID = Registrations.courseID
			INNER JOIN Student
			ON Registrations.studentID = Student.studentID
			WHERE Student.personID =""" + pPersonId + " " +
                "ORDER BY courseCode ASC";

        ResultSet result = connection.ExecuteQuery(selectDroppable);

        List<Course> Courses = convertToCourses(result);

        connection.Close();

        return Courses;
    }

    public List<SemesterCourses> getCoursesBySemesterForPersonId(int pPersonId)
    {
        SQLConnection connection = new SQLConnection();

        // Select available courses to drop.
        // Essentially, all courses that the student is registered to.

        String registeredCoursesQuery = """
      					SELECT COUNT(Courses.CourseId) AS CoursesCount, Courses.semester, courses.startDate, courses.endDate
						FROM Courses
						INNER JOIN Registrations
						ON Courses.courseID = Registrations.courseID
						INNER JOIN Student
						ON Registrations.studentID = Student.studentID
						WHERE Student.personID = """ + pPersonId + " " +
                "GROUP BY Courses.semester";

        ResultSet result = connection.ExecuteQuery(registeredCoursesQuery);

        List<SemesterCourses> semesterCourses = convertToSemesterCourses(result);

        connection.Close();

        return semesterCourses;
    }

    private List<Course> convertToCourses(ResultSet pResult)
    {
        try
        {
            List<Course> courses = new ArrayList<>();
            while(pResult.next())
            {
                Course course = new Course(
                        pResult.getInt("courseID"),
                        pResult.getString("courseCode"),
                        pResult.getString("title"),
                        pResult.getString("semester"),
                        pResult.getString("instructor"),
                        pResult.getDate("startDate"),
                        pResult.getDate("endDate")
                );

                courses.add(course);
            }

            return courses;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public List<SemesterCourses> convertToSemesterCourses(ResultSet pResult)
    {
        try
        {
            List<SemesterCourses> semesterCourses = new ArrayList<>();
            while(pResult.next())
            {
                SemesterCourses semesterCourse = new SemesterCourses(
                        pResult.getInt("CoursesCount"),
                        pResult.getString("semester"),
                        pResult.getDate("startDate"),
                        pResult.getDate("endDate")
                );

                semesterCourses.add(semesterCourse);
            }

            return semesterCourses;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }
    }
}
