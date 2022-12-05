package assignment.team._387a2.mappers;

import assignment.team._387a2.domainObjects.Administrator;
import assignment.team._387a2.domainObjects.DomainObject;
import assignment.team._387a2.helperObjects.SQLConnection;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AdministratorMapper extends PersonMapper {
    @Override
    public Administrator find(int employmentId){
        SQLConnection connection = new SQLConnection();

        String findQuery = """
                SELECT employmentID, Administrator.personID, firstName, lastName, password, address, email, phoneNumber, dateOfBirth
                FROM Administrator
                INNER JOIN Person
                ON Administrator.personID = Person.personID
                WHERE employmentID = """ + employmentId;

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<Administrator> administrators = convertToAdministrators(result);

        Administrator administrator = administrators == null || administrators.size() == 0 ? null
                : administrators.get(0);

        connection.Close();

        return administrator;
    }

    @Override
    public void update(DomainObject a) {
        SQLConnection connection = new SQLConnection();
        Administrator admin = (Administrator) a;

        // Converting the date to SQL format
        String format = "yyyy-MM-dd HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(format);
        String formattedDate = dateFormat.format(admin.getDateOfBirth());

        String updateAdministratorQuery = "UPDATE Administrator" +
                "SET personID = '" + admin.getPersonId() + "'" +
                "WHERE employmentID = " + Integer.toString(admin.getId()) + ";";

        connection.ExecuteNoReturn(updateAdministratorQuery);

        super.update(admin);

        connection.Close();
    }

    @Override
    public void insert(DomainObject a) {
        SQLConnection connection = new SQLConnection();

        Administrator admin = (Administrator) a;
        super.insert(admin);

        String insertQuery = "INSERT INTO Administrator(personID)" +
                "VALUES (" + admin.getPersonID() + ")";

        connection.ExecuteNoReturn(insertQuery);
    }

    @Override
    public void delete(DomainObject a) {
        SQLConnection connection = new SQLConnection();
        Administrator admin = (Administrator) a;

        String deleteQuery = "DELETE FROM Administrator WHERE EmploymentID = " + admin.getId()
                + ";";

        connection.ExecuteNoReturn(deleteQuery);

        connection.Close();
    }

    public void deleteByEmploymentId(int pEmploymentID) {
        SQLConnection connection = new SQLConnection();

        String deleteQuery = "DELETE FROM Administrator WHERE EmploymentID = " + pEmploymentID + ";";

        connection.ExecuteNoReturn(deleteQuery);

        connection.Close();
    }


    public Administrator findByPersonId(int personId) {
        SQLConnection connection = new SQLConnection();

        String findQuery = """
                SELECT employmentID, Administrator.personID, firstName, lastName, password, address, email, phoneNumber, dateOfBirth
                FROM Administrator
                INNER JOIN Person
                ON Administrator.personID = Person.personID
                WHERE Administrator.personID = """ + personId;
        ResultSet result = connection.ExecuteQuery(findQuery);

        List<Administrator> administrators = convertToAdministrators(result);

        Administrator administrator = administrators == null || administrators.size() == 0 ? null
                : administrators.get(0);

        connection.Close();

        return administrator;
    }

    public List<Administrator> getAllAdministrators()
    {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM Administrator;";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<Administrator> administrators = convertToAdministrators(result);

        connection.Close();

        return administrators;
    }


    private List<Administrator> convertToAdministrators(ResultSet pResult)
    {
        try
        {
            List<Administrator> administrators = new ArrayList<>();
            while(pResult.next())
            {
                Administrator admin = new Administrator(
                        pResult.getInt("employmentID"),
                        pResult.getInt("personID"),
                        pResult.getString("firstName"),
                        pResult.getString("lastName"),
                        pResult.getString("password"),
                        pResult.getString("address"),
                        pResult.getString("email"),
                        pResult.getString("phoneNumber"),
                        pResult.getDate("dateOfBirth")
                );

                administrators.add(admin);
            }

            return administrators;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

}
