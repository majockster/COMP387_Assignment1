package assignment.team._387a2.rowGateways;

public class StudentGateway
{
    private int studentId;
    private int personId;

    public StudentGateway()
    {
        this.studentId = -1;
        this.personId = -1;
    }

    public StudentGateway(int pStudentId, int pPersonId)
    {
        this.studentId = pStudentId;
        this.personId = pPersonId;
    }

    public int getPersonId()
    {
        return personId;
    }

    public int getStudentId()
    {
        return studentId;
    }

    public void setPersonId(int pPersonId)
    {
        personId = pPersonId;
    }

    public void setStudentId(int pStudentId)
    {
        studentId = pStudentId;
    }
}
