package assignment.team._387a2;

import assignment.team._387a2.rowGateways.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DataMapper
{
    public static List<PersonGateway> ConvertToPersons(ResultSet pResult)
    {
        try
        {
            List<PersonGateway> persons = new ArrayList<>();
            while(pResult.next())
            {
                PersonGateway person = new PersonGateway(
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
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public static List<StudentGateway> ConvertToStudents(ResultSet pResult)
    {
        try
        {
            List<StudentGateway> students = new ArrayList<>();
            while(pResult.next())
            {
                StudentGateway student = new StudentGateway(
                        pResult.getInt("studentID"),
                        pResult.getInt("personID")
                );

                students.add(student);
            }

            return students;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public static List<AdministratorGateway> ConvertToAdministrators(ResultSet pResult)
    {
        try
        {
            List<AdministratorGateway> administrators = new ArrayList<>();
            while(pResult.next())
            {
                AdministratorGateway administrator = new AdministratorGateway(
                        pResult.getInt("employmentID"),
                        pResult.getInt("personID")
                );

                administrators.add(administrator);
            }

            return administrators;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public static List<RegistrationGateway> ConvertToRegistrations(ResultSet pResult)
    {
        try
        {
            List<RegistrationGateway> registrations = new ArrayList<>();
            while(pResult.next())
            {
                RegistrationGateway registration = new RegistrationGateway(
                        pResult.getInt("registrationID"),
                        pResult.getInt("studentID"),
                        pResult.getInt("courseID")
                );

                registrations.add(registration);
            }

            return registrations;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public static List<CourseGateway> ConvertToCourses(ResultSet pResult)
    {
        try
        {
            List<CourseGateway> courses = new ArrayList<>();
            while(pResult.next())
            {
                CourseGateway course = new CourseGateway(
                        pResult.getInt("courseID"),
                        pResult.getString("courseCode"),
                        pResult.getString("title"),
                        pResult.getString("semester"),
                        pResult.getString("instructor"),
                        pResult.getDate("startDate"),
                        pResult.getDate("endDate")
                );

                courses.add(course);
            }

            return courses;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public static List<CourseTimeGateway> ConvertToCourseTimes(ResultSet pResult)
    {
        try
        {
            List<CourseTimeGateway> courseTimes = new ArrayList<>();
            while(pResult.next())
            {
                CourseTimeGateway courseTime = new CourseTimeGateway(
                        pResult.getInt("courseTimeID"),
                        pResult.getInt("courseID"),
                        pResult.getString("startTime"),
                        pResult.getString("endTime"),
                        pResult.getString("day"),
                        pResult.getString("section"),
                        pResult.getString("room")
                );

                courseTimes.add(courseTime);
            }

            return courseTimes;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }
    }
}
