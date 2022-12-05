package assignment.team._387a2.domainObjects;

import java.util.Date;

public class Student extends Person
{
    private int studentId;

    public Student()
    {
        super();
        this.studentId = -1;
    }

    public Student(int pStudentId, int personId, String firstName, String lastName, String password,
                   String address, String email, String phoneNumber, Date dateOfBirth)
    {
        super(personId, firstName, lastName, password, address, email, phoneNumber, dateOfBirth);
        this.studentId = pStudentId;
    }

    @Override
    public int getId() {
        return getStudentId();
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
