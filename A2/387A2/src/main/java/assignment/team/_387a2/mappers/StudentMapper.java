package assignment.team._387a2.mappers;

import assignment.team._387a2.domainObjects.Administrator;
import assignment.team._387a2.domainObjects.DomainObject;
import assignment.team._387a2.domainObjects.Student;
import assignment.team._387a2.helperObjects.SQLConnection;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class StudentMapper extends PersonMapper {

    @Override
    public Student find(int studentId)
    {
        SQLConnection connection = new SQLConnection();

        String findQuery = """
                SELECT studentID, Student.personID, firstName, lastName, password, address, email, phoneNumber, dateOfBirth
                FROM Student
                INNER JOIN Person
                ON Student.personID = Person.personID
                WHERE studentID = """ + studentId;

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<Student> students = convertToStudents(result);

        Student student = students == null || students.size() == 0 ? null
                : students.get(0);

        connection.Close();

        return student;
    }

    @Override
    public void insert(DomainObject s)
    {
        SQLConnection connection = new SQLConnection();
        Student pStudent = (Student) s;

        String insertQuery = "INSERT INTO Student(personID) " +
                "VALUES (" + pStudent.getPersonId() + ");";

        connection.ExecuteNoReturn(insertQuery);

        // Updating person ID to the proper value.
        Student newStudent = findByPersonId(pStudent.getPersonId(), ResultSet.CONCUR_UPDATABLE);
        pStudent.setStudentId(newStudent.getId());

        connection.Close();
    }

    @Override
    public void update(DomainObject s) {
        SQLConnection connection = new SQLConnection();
        Student student = (Student) s;

        // Converting the date to SQL format
        String format = "yyyy-MM-dd HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(format);
        String formattedDate = dateFormat.format(student.getDateOfBirth());

        String updateStudentQuery = "UPDATE Student" +
                "SET personID = '" + student.getPersonId() + "'" +
                "WHERE studentID = " + Integer.toString(student.getId()) + ";";

        connection.ExecuteNoReturn(updateStudentQuery);

        super.update(student);

        connection.Close();
    }

    @Override
    public void delete(DomainObject s) {
        SQLConnection connection = new SQLConnection();
        Student student = (Student) s;

        String deleteQuery = "DELETE FROM Student WHERE studentID = " + student.getId()
                + ";";

        connection.ExecuteNoReturn(deleteQuery);

        connection.Close();
    }

    public Student findByPersonId(int pId, int concurrencyValue)
    {
        SQLConnection connection = new SQLConnection();

        String findQuery = """
                SELECT studentID, Student.personID, firstName, lastName, password, address, email, phoneNumber, dateOfBirth
                FROM Student
                INNER JOIN Person
                ON Student.personID = Person.personID
                WHERE Student.personID = """ + pId;

        ResultSet result = connection.ExecuteQuery(findQuery, concurrencyValue);

        List<Student> students = convertToStudents(result);

        Student student = students == null || students.size() == 0 ? null : students.get(0);

        connection.Close();

        return student;
    }

    public List<Student> getAllStudents()
    {
        SQLConnection connection = new SQLConnection();

        String findQuery = """
                SELECT studentID, Student.personID, firstName, lastName, password, address, email, phoneNumber, dateOfBirth
                FROM Student
                INNER JOIN Person
                ON Student.personID = Person.personID
                """;

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<Student> students = convertToStudents(result);

        connection.Close();

        return students;
    }


    public void deleteStudentById(int pStudentID)
    {
        SQLConnection connection = new SQLConnection();

        String deleteQuery = "DELETE FROM Student WHERE studentID = " + pStudentID + ";";

        connection.ExecuteNoReturn(deleteQuery);

        connection.Close();
    }

    public List<Student> convertToStudents(ResultSet pResult)
    {
        try
        {
            List<Student> students = new ArrayList<>();
            while(pResult.next())
            {
                Student student = new Student(
                        pResult.getInt("studentID"),
                        pResult.getInt("personID"),
                        pResult.getString("firstName"),
                        pResult.getString("lastName"),
                        pResult.getString("password"),
                        pResult.getString("address"),
                        pResult.getString("email"),
                        pResult.getString("phoneNumber"),
                        pResult.getDate("dateOfBirth")
                );

                students.add(student);
            }

            return students;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }
    }
}
