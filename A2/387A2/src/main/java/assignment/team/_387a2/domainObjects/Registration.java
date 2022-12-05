package assignment.team._387a2.domainObjects;

public class Registration extends DomainObject {
    int registrationID;
    int studentID;
    int courseID;

    public Registration()
    {
        registrationID = -1;
        studentID = -1;
        courseID = -1;
    }


    public Registration(int registrationID, int studentID, int courseID) {
        this.registrationID = registrationID;
        this.studentID = studentID;
        this.courseID = courseID;
    }

    public int getId() {
        return getRegistrationID();
    }

    public int getRegistrationID() {
        return registrationID;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setRegistrationID(int registrationID) {
        this.registrationID = registrationID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}
