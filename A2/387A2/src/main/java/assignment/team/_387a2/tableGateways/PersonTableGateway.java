package assignment.team._387a2.tableGateways;

import assignment.team._387a2.DataMapper;
import assignment.team._387a2.helperObjects.SQLConnection;
import assignment.team._387a2.rowGateways.PersonGateway;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class PersonTableGateway
{
    public PersonTableGateway()
    {
    }

    public PersonGateway findById(int pId)
    {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM Person WHERE personID = " + pId + ";";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<PersonGateway> persons = DataMapper.ConvertToPersons(result);

        PersonGateway person = persons == null || persons.size() == 0 ? null : persons.get(0);

        connection.Close();

        return person;
    }

    public PersonGateway findByLastName(String pLastName)
    {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM Person WHERE lastName = '" + pLastName + "' ;";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<PersonGateway> persons = DataMapper.ConvertToPersons(result);

        PersonGateway person = persons == null || persons.size() == 0 ? null : persons.get(0);

        connection.Close();

        return person;
    }

    public List<PersonGateway> getAll()
    {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM Person;";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<PersonGateway> persons = DataMapper.ConvertToPersons(result);

        connection.Close();

        return persons;
    }

    public PersonGateway findByEmail(String pEmail)
    {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM Person WHERE email = '" + pEmail + "' ;";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<PersonGateway> persons = DataMapper.ConvertToPersons(result);

        PersonGateway person = persons == null || persons.size() == 0 ? null : persons.get(0);

        connection.Close();

        return person;
    }

    public void updatePerson(PersonGateway pPerson)
    {
        SQLConnection connection = new SQLConnection();

        // Converting the date to SQL format
        String format = "yyyy-MM-dd HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(format);
        String formattedDate = dateFormat.format(pPerson.getDateOfBirth());

        String updateQuery = "UPDATE Person " +
                "SET firstName = '" + pPerson.getFirstName() + "' , " +
                "lastName = '" + pPerson.getLastName() + "' , " +
                "password = '" + pPerson.getPassword() + "' , " +
                "address = '" + pPerson.getAddress() + "' , " +
                "email = '" + pPerson.getEmail() + "' , " +
                "phoneNumber = '" + pPerson.getPhoneNumber() + "' , " +
                "dateOfBirth = '" + formattedDate + "'  " +
                "WHERE personID = " + Integer.toString(pPerson.getPersonId()) + ";";

        connection.ExecuteNoReturn(updateQuery);

        connection.Close();
    }

    public void insertPerson(PersonGateway pPerson)
    {
        SQLConnection connection = new SQLConnection();

        // Converting the date to SQL format
        String format = "yyyy-MM-dd HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(format);
        String formattedDate = dateFormat.format(pPerson.getDateOfBirth());

        String insertQuery = "INSERT INTO Person(firstName, lastName, password, address, email, phoneNumber, dateOfBirth) " +
                "VALUES ('" + pPerson.getFirstName() + "', " +
                "'" + pPerson.getLastName() + "', " +
                "'" + pPerson.getPassword() + "', " +
                "'" + pPerson.getAddress() + "', " +
                "'" + pPerson.getEmail() + "', " +
                "'" + pPerson.getPhoneNumber() + "', " +
                "'" + formattedDate + "');";

        connection.ExecuteNoReturn(insertQuery);

        // Updating person ID to the proper value.
        PersonGateway newPerson = findByEmail(pPerson.getEmail());
        pPerson.setPersonId(newPerson.getPersonId());

        connection.Close();
    }

    public void deletePerson(PersonGateway pPerson)
    {
        SQLConnection connection = new SQLConnection();

        String deleteQuery = "DELETE FROM Person WHERE personID = " + pPerson.getPersonId() + ";";

        connection.ExecuteNoReturn(deleteQuery);

        connection.Close();
    }

    public void deletePersonById(int pPersonId)
    {
        SQLConnection connection = new SQLConnection();

        String deleteQuery = "DELETE FROM Person WHERE personID = " + pPersonId + ";";

        connection.ExecuteNoReturn(deleteQuery);

        connection.Close();
    }
}
