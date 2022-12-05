package assignment.team._387a2.mappers;

import assignment.team._387a2.domainObjects.DomainObject;
import assignment.team._387a2.domainObjects.Person;
import assignment.team._387a2.helperObjects.SQLConnection;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PersonMapper extends assignment.team._387a2.mappers.DataMapper {

    @Override
    public Person find(int id) {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM Person WHERE personID = " + id + ";";

        ResultSet result = connection.ExecuteQuery(findQuery, ResultSet.CONCUR_READ_ONLY);

        List<Person> persons = convertToPersons(result);

        Person person = persons == null || persons.size() == 0 ? null : persons.get(0);

        connection.Close();

        return person;
    }

    @Override
    public void update(DomainObject pPerson) {
        SQLConnection connection = new SQLConnection();
        Person p = (Person) pPerson;

        // Converting the date to SQL format
        String format = "yyyy-MM-dd HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(format);
        String formattedDate = dateFormat.format(p.getDateOfBirth());

        String updateQuery = "UPDATE Person " +
                "SET firstName = '" + p.getFirstName() + "' , " +
                "lastName = '" + p.getLastName() + "' , " +
                "password = '" + p.getPassword() + "' , " +
                "address = '" + p.getAddress() + "' , " +
                "email = '" + p.getEmail() + "' , " +
                "phoneNumber = '" + p.getPhoneNumber() + "' , " +
                "dateOfBirth = '" + formattedDate + "'  " +
                "WHERE personID = " + Integer.toString(p.getPersonId()) + ";";

        connection.ExecuteNoReturn(updateQuery);

        connection.Close();

    }

    @Override
    public void insert(DomainObject pPerson) {
        SQLConnection connection = new SQLConnection();
        Person p = (Person) pPerson;

        // Converting the date to SQL format
        String format = "yyyy-MM-dd HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(format);
        String formattedDate = dateFormat.format(p.getDateOfBirth());

        String insertQuery = "INSERT INTO Person(firstName, lastName, password, address, email, phoneNumber, dateOfBirth) " +
                "VALUES ('" + p.getFirstName() + "', " +
                "'" + p.getLastName() + "', " +
                "'" + p.getPassword() + "', " +
                "'" + p.getAddress() + "', " +
                "'" + p.getEmail() + "', " +
                "'" + p.getPhoneNumber() + "', " +
                "'" + formattedDate + "');";

        connection.ExecuteNoReturn(insertQuery);

        // Updating person ID to the proper value.
        Person newPerson = findByEmail(p.getEmail());
        p.setPersonId(newPerson.getPersonId());

        connection.Close();
    }

    @Override
    public void delete(DomainObject pPerson) {
        Person p = (Person) pPerson;

        SQLConnection connection = new SQLConnection();

        String deleteQuery = "DELETE FROM Person WHERE personID = " + p.getPersonId() + ";";

        connection.ExecuteNoReturn(deleteQuery);

        connection.Close();
    }

    public List<Person> getAll()
    {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM Person;";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<Person> persons = convertToPersons(result);

        connection.Close();

        return persons;
    }

    public Person findByEmail(String pEmail)
    {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM Person WHERE email = '" + pEmail + "' ;";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<Person> persons = convertToPersons(result);

        Person person = persons == null || persons.size() == 0 ? null : persons.get(0);

        connection.Close();

        return person;
    }

    public Person findByLastName(String pLastName)
    {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM Person WHERE lastName = '" + pLastName + "' ;";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<Person> persons = convertToPersons(result);

        Person person = persons == null || persons.size() == 0 ? null : persons.get(0);

        connection.Close();

        return person;
    }

    public void deletePersonById(int pPersonId)
    {
        SQLConnection connection = new SQLConnection();

        String deleteQuery = "DELETE FROM Person WHERE personID = " + pPersonId + ";";

        connection.ExecuteNoReturn(deleteQuery);

        connection.Close();
    }

    private List<Person> convertToPersons(ResultSet pResult) {
        try {
            List<Person> persons = new ArrayList<>();
            while (pResult.next()) {
                Person person = new Person(
                        pResult.getInt("personID"),
                        pResult.getString("firstName"),
                        pResult.getString("lastName"),
                        pResult.getString("password"),
                        pResult.getString("address"),
                        pResult.getString("email"),
                        pResult.getString("phoneNumber"),
                        pResult.getDate("dateOfBirth")
                );

                persons.add(person);
            }

            return persons;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
