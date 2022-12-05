package assignment.team._387a2.mappers;

import assignment.team._387a2.domainObjects.CourseTime;
import assignment.team._387a2.domainObjects.DomainObject;
import assignment.team._387a2.helperObjects.SQLConnection;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CourseTimeMapper extends assignment.team._387a2.mappers.DataMapper {
    @Override
    public CourseTime find(int id) {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM CourseTimes WHERE courseTimeID = " + id + ";";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<CourseTime> courseTimes = convertToCourseTimes(result);

        CourseTime courseTime = courseTimes == null || courseTimes.size() == 0 ? null : courseTimes.get(0);

        connection.Close();

        return courseTime;
    }

    @Override
    public void update(DomainObject ct) {
        SQLConnection connection = new SQLConnection();
        CourseTime courseTime = (CourseTime) ct;

        String updateQuery = "UPDATE CourseTimes " +
                "SET courseId = " + courseTime.getCourseID() +
                " WHERE courseTimeID = " + Integer.toString(courseTime.getId()) + ";";

        connection.ExecuteNoReturn(updateQuery);

        connection.Close();
    }

    @Override
    public void insert(DomainObject ct) {
        SQLConnection connection = new SQLConnection();
        CourseTime courseTime = (CourseTime) ct;

        String insertQuery = "INSERT INTO CourseTimes(courseID, startTime, endTime, day, section, room) " +
                "VALUES ('" + courseTime.getCourseID() + "', " +
                "'" + courseTime.getStartTime() + "', " +
                "'" + courseTime.getEndTime() + "', " +
                "'" + courseTime.getDay() + "', " +
                "'" + courseTime.getSection() + "', " +
                "'" + courseTime.getRoom() + "');";

        connection.ExecuteNoReturn(insertQuery);

        // Updating coursetime ID to the proper value.
        List<CourseTime> listCourseTimes = getAll();
        CourseTime newCourseTime = listCourseTimes.get(listCourseTimes.size()-1);
        courseTime.setCourseTimeID(newCourseTime.getId());

        connection.Close();
    }

    @Override
    public void delete(DomainObject ct) {
        SQLConnection connection = new SQLConnection();
        CourseTime courseTime = (CourseTime) ct;

        String deleteQuery = "DELETE FROM CourseTimes WHERE CourseTimeID = " + courseTime.getId() + ";";

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

    public List<CourseTime> getAll() {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM CourseTimes;";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<CourseTime> courseTimes = convertToCourseTimes(result);

        connection.Close();

        return courseTimes;
    }

    public List<CourseTime> findByCourseId(int pCourseId) {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM CourseTimes WHERE courseID = " + pCourseId + ";";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<CourseTime> courseTimes = convertToCourseTimes(result);

        connection.Close();

        return courseTimes;
    }

    private List<CourseTime> convertToCourseTimes(ResultSet pResult)
    {
        try
        {
            List<CourseTime> courseTimes = new ArrayList<>();
            while(pResult.next())
            {
                CourseTime courseTime = new CourseTime(
                        pResult.getInt("courseTimeID"),
                        pResult.getInt("courseID"),
                        pResult.getString("startTime"),
                        pResult.getString("endTime"),
                        pResult.getString("day"),
                        pResult.getString("section"),
                        pResult.getString("room")
                );

                courseTimes.add(courseTime);
            }

            return courseTimes;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }
    }
}
