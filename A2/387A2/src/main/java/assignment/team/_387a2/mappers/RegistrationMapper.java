package assignment.team._387a2.mappers;

import assignment.team._387a2.domainObjects.DomainObject;
import assignment.team._387a2.domainObjects.Registration;
import assignment.team._387a2.helperObjects.SQLConnection;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RegistrationMapper extends assignment.team._387a2.mappers.DataMapper {
    @Override
    public Registration find(int id) {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM Registrations WHERE registrationId = " + id + ";";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<Registration> registrations = convertToRegistrations(result);

        Registration registration = registrations == null || registrations.size() == 0 ? null : registrations.get(0);

        connection.Close();

        return registration;
    }

    @Override
    public void update(DomainObject r) {
        SQLConnection connection = new SQLConnection();
        Registration pRegistration = (Registration) r;

        String updateQuery = "UPDATE Registrations " +
                "SET studentID = " + pRegistration.getStudentID() + ", " +
                "courseID = " + pRegistration.getCourseID() +
                " WHERE registrationID = " + Integer.toString(pRegistration.getId()) + ";";

        connection.ExecuteNoReturn(updateQuery);

        connection.Close();
    }

    @Override
    public void insert(DomainObject r) {
        SQLConnection connection = new SQLConnection();
        Registration pRegistration = (Registration) r;


        String insertQuery = "INSERT INTO Registrations(studentID, courseID) " +
                "VALUES (" + pRegistration.getStudentID() + ", " + pRegistration.getCourseID() + " );";

        connection.ExecuteNoReturn(insertQuery);

        // Updating person ID to the proper value.
        Registration newReg = findByStudentIdAndCourseId(pRegistration.getStudentID(), pRegistration.getCourseID(), ResultSet.CONCUR_UPDATABLE);
        pRegistration.setRegistrationID(newReg.getId());

        connection.Close();
    }

    @Override
    public void delete(DomainObject r) {
        SQLConnection connection = new SQLConnection();
        Registration pRegistration = (Registration) r;

        String deleteQuery = "DELETE FROM Registrations WHERE registrationID = " + pRegistration.getId() + ";";

        connection.ExecuteNoReturn(deleteQuery);

        connection.Close();
    }

    public Registration findByStudentIdAndCourseId(int pStudentId, int pCourseId, int concurrencyValue)
    {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM Registrations WHERE studentID = " + pStudentId + " AND courseID = " + pCourseId + ";";

        ResultSet result = connection.ExecuteQuery(findQuery, concurrencyValue);

        List<Registration> registrations = convertToRegistrations(result);

        Registration registration = registrations == null || registrations.size() == 0 ? null : registrations.get(0);

        connection.Close();

        return registration;
    }

    public List<Registration> getAll()
    {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM Registrations;";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<Registration> registrations = convertToRegistrations(result);

        connection.Close();

        return registrations;
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

    public List<Registration> convertToRegistrations(ResultSet pResult)
    {
        try
        {
            List<Registration> registrations = new ArrayList<>();
            while(pResult.next())
            {
                Registration registration = new Registration(
                        pResult.getInt("registrationID"),
                        pResult.getInt("studentID"),
                        pResult.getInt("courseID")
                );

                registrations.add(registration);
            }

            return registrations;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }
    }




}
