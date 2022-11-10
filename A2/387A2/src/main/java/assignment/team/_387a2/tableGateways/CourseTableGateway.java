package assignment.team._387a2.tableGateways;

import assignment.team._387a2.DataMapper;
import assignment.team._387a2.dataObjects.SemesterCourses;
import assignment.team._387a2.helperObjects.SQLConnection;
import assignment.team._387a2.rowGateways.PersonGateway;
import assignment.team._387a2.rowGateways.StudentGateway;
import assignment.team._387a2.rowGateways.CourseGateway;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;


public class CourseTableGateway
{
    public CourseTableGateway()
    {
    }

    public CourseGateway findById(int pId, int concurrencyValue)
    {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM courses WHERE courseID = " + pId + ";";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<CourseGateway> courses = DataMapper.ConvertToCourses(result);

        CourseGateway Course = courses == null || courses.size() == 0 ? null : courses.get(0);

        connection.Close();

        return Course;
    }

    public CourseGateway findByCourseCode(String pCourseCode)
    {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM courses WHERE courseCode = '" + pCourseCode + "' ;";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<CourseGateway> courses = DataMapper.ConvertToCourses(result);

        CourseGateway Course = courses == null || courses.size() == 0 ? null : courses.get(0);

        connection.Close();

        return Course;
    }

    public List<CourseGateway> getAll()
    {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM courses;";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<CourseGateway> Courses = DataMapper.ConvertToCourses(result);

        connection.Close();

        return Courses;
    }

    public List<StudentGateway> getStudentListByCourseId(int courseId)
    {
        SQLConnection connection = new SQLConnection();
        String selectStudentList = """
            SELECT Student.studentID, Student.personID
            FROM Student
            INNER JOIN Registrations
            ON Student.studentID = Registrations.studentID
            WHERE Registrations.courseID =""" + courseId + " " +
            "ORDER BY Student.studentID";
        
        ResultSet result = connection.ExecuteQuery(selectStudentList);

        List<StudentGateway> students = DataMapper.ConvertToStudents(result);

        connection.Close();

        return students;
    }

    public List<CourseGateway> getCoursesByStudentId(int pStudentId)
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

        List<CourseGateway> Courses = DataMapper.ConvertToCourses(result);

        connection.Close();

        return Courses;
    }

    public List<CourseGateway> getCoursesByPersonId(int pPersonId)
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

        List<CourseGateway> Courses = DataMapper.ConvertToCourses(result);

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

        List<SemesterCourses> semesterCourses = DataMapper.ConvertToSemesterCourses(result);

        connection.Close();

        return semesterCourses;
    }

    public List<CourseGateway> getCoursesAvailableToStudent(int pStudentId)
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

        List<CourseGateway> Courses = DataMapper.ConvertToCourses(result);

        connection.Close();

        return Courses;
    }

    public void updateCourse(CourseGateway pCourse)
    {
        SQLConnection connection = new SQLConnection();

        // Converting the date to SQL format
        String format = "yyyy-MM-dd HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(format);
        String formattedStartDate = dateFormat.format(pCourse.getStartDate());
        String formattedEndDate = dateFormat.format(pCourse.getEndDate());

        String updateQuery = "UPDATE Çourses " +
                "SET courseCode = '" + pCourse.getCourseCode() + "' , " +
                "title = '" + pCourse.getTitle() + "' , " +
                "semester = '" + pCourse.getSemester() + "' , " +
                "instructor = '" + pCourse.getInstructor() + "' , " +
                "startDate = '" + formattedStartDate + "' , " +
                "endDate = '" + formattedEndDate + "' " +
                "WHERE courseID = " + Integer.toString(pCourse.getCourseID()) + ";";

        connection.ExecuteNoReturn(updateQuery);

        connection.Close();
    }

    public void insertCourse(CourseGateway pCourse)
    {
        SQLConnection connection = new SQLConnection();

        // Converting the date to SQL format
        String format = "yyyy-MM-dd HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(format);
        String formattedStartDate = dateFormat.format(pCourse.getStartDate());
        String formattedEndDate = dateFormat.format(pCourse.getEndDate());

        String insertQuery = "INSERT INTO Courses(courseCode, title, semester, instructor, startDate, endDate) " +
                "VALUES ('" + pCourse.getCourseCode() + "', " +
                "'" + pCourse.getTitle() + "', " +
                "'" + pCourse.getSemester() + "', " +
                "'" + pCourse.getInstructor() + "', " +
                "'" + formattedStartDate + "', " +
                "'" + formattedEndDate + "');";

        connection.ExecuteNoReturn(insertQuery);

        // Updating person ID to the proper value.
        CourseGateway newCourse = findByCourseCode(pCourse.getCourseCode());
        pCourse.setCourseID(newCourse.getCourseID());

        connection.Close();
    }

    public void deleteCourse(CourseGateway pCourse)
    {
        SQLConnection connection = new SQLConnection();

        String deleteQuery = "DELETE FROM Courses WHERE courseID = " + pCourse.getCourseID() + ";";

        connection.ExecuteNoReturn(deleteQuery);

        connection.Close();
    }

    public void deleteCourseById(int pCourseID)
    {
        SQLConnection connection = new SQLConnection();

        String deleteQuery = "DELETE FROM Courses WHERE courseID = " + pCourseID + ";";

        connection.ExecuteNoReturn(deleteQuery);

        connection.Close();
    }
}
