package assignment.team._387a2.domainObjects;

import java.util.Date;

public class Administrator extends Person
{
    int employmentID;

    public Administrator()
    {
        super();
        employmentID = -1;
    }

    public Administrator(int employmentID, int personId, String firstName, String lastName, String password,
                         String address, String email, String phoneNumber, Date dateOfBirth)
    {
        super(personId, firstName, lastName, password, address, email, phoneNumber, dateOfBirth);
        this.employmentID = employmentID;
    }

    @Override
    public int getId() {
        return getEmploymentID();
    }

    public int getPersonID() {
        return super.getId();
    }

    public String getFirstName() { return super.getFirstName(); }

    public String getLastName() { return super.getLastName(); }

    public int getEmploymentID()
    {
        return employmentID;
    }

    public void setEmploymentID(int employmentID)
    {
        this.employmentID = employmentID;
    }

}
