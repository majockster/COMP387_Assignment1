package assignment.team._387a2.tableGateways;

import assignment.team._387a2.DataMapper;
import assignment.team._387a2.helperObjects.SQLConnection;
import assignment.team._387a2.rowGateways.PersonGateway;
import assignment.team._387a2.rowGateways.StudentGateway;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class StudentTableGateway
{
    public StudentTableGateway()
    {
    }

    public StudentGateway findById(int pId)
    {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM Student WHERE studentID = " + pId + ";";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<StudentGateway> students = DataMapper.ConvertToStudents(result);

        StudentGateway student = students == null || students.size() == 0 ? null : students.get(0);

        connection.Close();

        return student;
    }

    public StudentGateway findByPersonId(int pId)
    {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM Student WHERE personID = " + pId + ";";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<StudentGateway> students = DataMapper.ConvertToStudents(result);

        StudentGateway student = students == null || students.size() == 0 ? null : students.get(0);

        connection.Close();

        return student;
    }

    public List<StudentGateway> getAll()
    {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM Student;";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<StudentGateway> students = DataMapper.ConvertToStudents(result);

        connection.Close();

        return students;
    }

    public void updateStudent(StudentGateway pStudent)
    {
        SQLConnection connection = new SQLConnection();

        String updateQuery = "UPDATE Student " +
                "SET personId = " + pStudent.getPersonId() +
                " WHERE studentID = " + Integer.toString(pStudent.getStudentId()) + ";";

        connection.ExecuteNoReturn(updateQuery);

        connection.Close();
    }

    public void insertStudent(StudentGateway pStudent)
    {
        SQLConnection connection = new SQLConnection();

        String insertQuery = "INSERT INTO Student(personID) " +
                "VALUES (" + pStudent.getPersonId() + ");";

        connection.ExecuteNoReturn(insertQuery);

        // Updating person ID to the proper value.
        StudentGateway newStudent = findByPersonId(pStudent.getPersonId());
        pStudent.setStudentId(newStudent.getStudentId());

        connection.Close();
    }

    public void deleteStudent(StudentGateway pStudent)
    {
        SQLConnection connection = new SQLConnection();

        String deleteQuery = "DELETE FROM Student WHERE studentID = " + pStudent.getStudentId() + ";";

        connection.ExecuteNoReturn(deleteQuery);

        connection.Close();
    }

    public void deleteStudentById(int pStudentID)
    {
        SQLConnection connection = new SQLConnection();

        String deleteQuery = "DELETE FROM Student WHERE studentID = " + pStudentID + ";";

        connection.ExecuteNoReturn(deleteQuery);

        connection.Close();
    }
}
