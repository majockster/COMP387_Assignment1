package assignment.team._387a2.tableGateways;

import assignment.team._387a2.DataMapper;
import assignment.team._387a2.helperObjects.SQLConnection;
import assignment.team._387a2.rowGateways.CourseTimeGateway;

import java.sql.ResultSet;
import java.util.List;

public class CourseTimeTableGateway {
    public CourseTimeTableGateway() {
    }

    public CourseTimeGateway findById(int pId) {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM CourseTimes WHERE courseTimeID = " + pId + ";";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<CourseTimeGateway> courseTimes = DataMapper.ConvertToCourseTimes(result);

        CourseTimeGateway courseTime = courseTimes == null || courseTimes.size() == 0 ? null : courseTimes.get(0);

        connection.Close();

        return courseTime;
    }

    public List<CourseTimeGateway> findByCourseId(int pCourseId) {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM CourseTimes WHERE courseID = " + pCourseId + ";";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<CourseTimeGateway> courseTimes = DataMapper.ConvertToCourseTimes(result);

        connection.Close();

        return courseTimes;
    }

    public CourseTimeGateway findByCourseTimeId(int pCourseTimeId) {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM CourseTimes WHERE courseTimeID = " + pCourseTimeId + ";";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<CourseTimeGateway> courseTimes = DataMapper.ConvertToCourseTimes(result);

        CourseTimeGateway courseTime = courseTimes == null || courseTimes.size() == 0 ? null : courseTimes.get(0);

        connection.Close();

        return courseTime;
    }

    public List<CourseTimeGateway> getAll() {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM CourseTimes;";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<CourseTimeGateway> courseTimes = DataMapper.ConvertToCourseTimes(result);

        connection.Close();

        return courseTimes;
    }

    public void updateCourseTime(CourseTimeGateway pCourseTime) {
        SQLConnection connection = new SQLConnection();

        String updateQuery = "UPDATE CourseTimes " +
                "SET courseId = " + pCourseTime.getCourseID() +
                " WHERE courseTimeID = " + Integer.toString(pCourseTime.getCourseTimeID()) + ";";

        connection.ExecuteNoReturn(updateQuery);

        connection.Close();
    }

    public void insertCourseTime(CourseTimeGateway pCourseTime) {
        SQLConnection connection = new SQLConnection();

        String insertQuery = "INSERT INTO CourseTimes(courseID) " +
                "VALUES (" + pCourseTime.getCourseID() + ");";

        connection.ExecuteNoReturn(insertQuery);

        // Updating coursetime ID to the proper value.
        CourseTimeGateway newCourseTime = findByCourseTimeId(pCourseTime.getCourseTimeID());
        pCourseTime.setCourseTimeID(newCourseTime.getCourseTimeID());

        connection.Close();
    }

    public void deleteCourseTime(CourseTimeGateway pCourseTime) {
        SQLConnection connection = new SQLConnection();

        String deleteQuery = "DELETE FROM CourseTimes WHERE CourseTimeID = " + pCourseTime.getCourseTimeID() + ";";

        connection.ExecuteNoReturn(deleteQuery);

        connection.Close();
    }

    public void deleteCourseTimeByCourseTimeId(int pCourseTimeID) {
        SQLConnection connection = new SQLConnection();

        String deleteQuery = "DELETE FROM CourseTimes WHERE CourseTimeID = " + pCourseTimeID + ";";

        connection.ExecuteNoReturn(deleteQuery);

        connection.Close();
    }

    public void deleteCourseTimeByCourseId(int pCourseID) {
        SQLConnection connection = new SQLConnection();

        String deleteQuery = "DELETE FROM CourseTimes WHERE CourseID = " + pCourseID + ";";

        connection.ExecuteNoReturn(deleteQuery);

        connection.Close();
    }
}
