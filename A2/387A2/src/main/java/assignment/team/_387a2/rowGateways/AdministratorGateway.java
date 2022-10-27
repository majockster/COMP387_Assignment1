package assignment.team._387a2.rowGateways;

public class AdministratorGateway
{
    int employmentID;
    int personID;

    public AdministratorGateway()
    {
        employmentID = -1;
        personID = -1;
    }

    public AdministratorGateway(int employmentID, int personID)
    {
        this.employmentID = employmentID;
        this.personID = personID;
    }

    public int getEmploymentID() {
        return employmentID;
    }

    public int getPersonID() {
        return personID;
    }

    public void setEmploymentID(int employmentID) {
        this.employmentID = employmentID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }
}
