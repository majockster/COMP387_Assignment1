package assignment.team._387a2.rowGateways;

import java.util.Date;

public class PersonGateway
{
    private int personId;
    private String firstName;
    private String lastName;
    private String password;
    private String address;
    private String email;
    private String phoneNumber;
    private Date dateOfBirth;

    public PersonGateway()
    {
        this.personId = -1;
        this.firstName = "";
        this.lastName = "";
        this.password = "";
        this.address = "";
        this.email = "";
        this.phoneNumber = "";
        this.dateOfBirth = new Date();
    }

    public PersonGateway(int personId, String firstName, String lastName, String password, String address, String email, String phoneNumber, Date dateOfBirth) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public int getPersonId() {
        return personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
