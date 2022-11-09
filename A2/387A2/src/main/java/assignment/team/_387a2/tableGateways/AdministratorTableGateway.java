package assignment.team._387a2.tableGateways;

import assignment.team._387a2.DataMapper;
import assignment.team._387a2.helperObjects.SQLConnection;
import assignment.team._387a2.rowGateways.AdministratorGateway;
import assignment.team._387a2.rowGateways.PersonGateway;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class AdministratorTableGateway {
    public AdministratorTableGateway() {
    }

    public AdministratorGateway findByEmploymentId(int employmentId) {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM Administrator WHERE employmentID = " + employmentId + ";";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<AdministratorGateway> administrators = DataMapper.ConvertToAdministrators(result);

        AdministratorGateway administrator = administrators == null || administrators.size() == 0 ? null
                : administrators.get(0);

        connection.Close();

        return administrator;
    }

    public AdministratorGateway findByPersonId(int personId) {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM Administrator WHERE personID = " + personId + ";";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<AdministratorGateway> administrators = DataMapper.ConvertToAdministrators(result);

        AdministratorGateway administrator = administrators == null || administrators.size() == 0 ? null
                : administrators.get(0);

        connection.Close();

        return administrator;
    }

    public List<AdministratorGateway> getAll() {
        SQLConnection connection = new SQLConnection();

        String findQuery = "SELECT * FROM Administrator;";

        ResultSet result = connection.ExecuteQuery(findQuery);

        List<AdministratorGateway> administrators = DataMapper.ConvertToAdministrators(result);

        connection.Close();

        return administrators;
    }

    public void insertAdministrator(AdministratorGateway pAdministrator) {
        SQLConnection connection = new SQLConnection();

        String insertQuery = "INSERT INTO Administrator(personID)" +
                "VALUES (" + pAdministrator.getPersonID() + ")";

        connection.ExecuteNoReturn(insertQuery);
    }

    public void deleteAdministrator(AdministratorGateway pAdministrator) {
        SQLConnection connection = new SQLConnection();

        String deleteQuery = "DELETE FROM Administrator WHERE EmploymentID = " + pAdministrator.getEmploymentID()
                + ";";

        connection.ExecuteNoReturn(deleteQuery);

        connection.Close();
    }

    public void deleteAdministratorById(int pEmploymentID) {
        SQLConnection connection = new SQLConnection();

        String deleteQuery = "DELETE FROM Administrator WHERE EmploymentID = " + pEmploymentID + ";";

        connection.ExecuteNoReturn(deleteQuery);

        connection.Close();
    }

}