package assignment.team._387a2.tableGateways;

import assignment.team._387a2.DataMapper;
import assignment.team._387a2.helperObjects.SQLConnection;
import assignment.team._387a2.rowGateways.RegistrationGateway;
import assignment.team._387a2.rowGateways.StudentGateway;

import java.sql.ResultSet;
import java.util.List;

public class RegistrationTableGateway
{
    public RegistrationTableGateway()
    {
    }

    public RegistrationGateway findById(int pId)
    {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM Registrations WHERE registrationId = " + pId + ";";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<RegistrationGateway> registrations = DataMapper.ConvertToRegistrations(result);

        RegistrationGateway registration = registrations == null || registrations.size() == 0 ? null : registrations.get(0);

        connection.Close();

        return registration;
    }

    public RegistrationGateway findByStudentIdAndCourseId(int pStudentId, int pCourseId)
    {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM Registrations WHERE studentID = " + pStudentId + " AND courseID = " + pCourseId + ";";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<RegistrationGateway> registrations = DataMapper.ConvertToRegistrations(result);

        RegistrationGateway registration = registrations == null || registrations.size() == 0 ? null : registrations.get(0);

        connection.Close();

        return registration;
    }

    public List<RegistrationGateway> getAll()
    {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM Registrations;";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<RegistrationGateway> registrations = DataMapper.ConvertToRegistrations(result);

        connection.Close();

        return registrations;
    }

    public void updateRegistration(RegistrationGateway pRegistration)
    {
        SQLConnection connection = new SQLConnection();

        String updateQuery = "UPDATE Registrations " +
                "SET studentID = " + pRegistration.getStudentID() + ", " +
                "courseID = " + pRegistration.getCourseID() +
                " WHERE registrationID = " + Integer.toString(pRegistration.getRegistrationID()) + ";";

        connection.ExecuteNoReturn(updateQuery);

        connection.Close();
    }

    public void insertRegistration(RegistrationGateway pRegistration)
    {
        SQLConnection connection = new SQLConnection();

        String insertQuery = "INSERT INTO Registrations(studentID, courseID) " +
                "VALUES (" + pRegistration.getStudentID() + ", " + pRegistration.getCourseID() + " );";

        connection.ExecuteNoReturn(insertQuery);

        // Updating person ID to the proper value.
        RegistrationGateway newReg = findByStudentIdAndCourseId(pRegistration.getStudentID(), pRegistration.getCourseID());
        pRegistration.setRegistrationID(newReg.getRegistrationID());

        connection.Close();
    }

    public void deleteRegistration(RegistrationGateway pRegistration)
    {
        SQLConnection connection = new SQLConnection();

        String deleteQuery = "DELETE FROM Registrations WHERE registrationID = " + pRegistration.getRegistrationID() + ";";

        connection.ExecuteNoReturn(deleteQuery);

        connection.Close();
    }

    public void deleteRegistrationById(int pRegistrationId)
    {
        SQLConnection connection = new SQLConnection();

        String deleteQuery = "DELETE FROM Registrations WHERE registrationID = " + pRegistrationId + ";";

        connection.ExecuteNoReturn(deleteQuery);

        connection.Close();
    }

    public void deleteRegistrationByStudentAndCourseId(int pStudentId, int pCourseId)
    {
        SQLConnection connection = new SQLConnection();

        String deleteQuery = "DELETE FROM Registrations WHERE studentID = " + pStudentId + " AND courseID = " + pCourseId + " ;";

        connection.ExecuteNoReturn(deleteQuery);

        connection.Close();
    }
}
