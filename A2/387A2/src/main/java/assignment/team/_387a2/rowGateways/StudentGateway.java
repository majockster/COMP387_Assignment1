package assignment.team._387a2.rowGateways;

import java.util.Date;

public class StudentGateway extends PersonGateway
{
    private int studentId;

    public StudentGateway()
    {
        super();
        this.studentId = -1;
    }

    public StudentGateway(int pStudentId, int personId, String firstName, String lastName, String password,
                          String address, String email, String phoneNumber, Date dateOfBirth)
    {
        super(personId, firstName, lastName, password, address, email, phoneNumber, dateOfBirth);
        this.studentId = pStudentId;
    }


    public int getStudentId()
    {
        return studentId;
    }

    public void setStudentId(int pStudentId)
    {
        studentId = pStudentId;
    }
}
