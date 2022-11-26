package assignment.team._387a2.rowGateways;

import java.util.Date;

public class AdministratorGateway extends PersonGateway
{
    int employmentID;

    public AdministratorGateway()
    {
        super();
        employmentID = -1;
    }

    public AdministratorGateway(int employmentID, int personId, String firstName, String lastName, String password,
                                String address, String email, String phoneNumber, Date dateOfBirth)
    {
        super(personId, firstName, lastName, password, address, email, phoneNumber, dateOfBirth);
        this.employmentID = employmentID;
    }

    public int getEmploymentID()
    {
        return employmentID;
    }

    public void setEmploymentID(int employmentID)
    {
        this.employmentID = employmentID;
    }

}
