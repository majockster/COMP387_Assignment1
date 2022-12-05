package assignment.team._387a2.tableGateways;

import assignment.team._387a2.mappers.StudentMapper;
import assignment.team._387a2.domainObjects.Student;

import java.util.List;

public class StudentTableGateway
{
    private StudentMapper studentMapper;

    public StudentTableGateway()
    {
        studentMapper = new StudentMapper();
    }

    public Student findById(int pId)
    {
        return studentMapper.find(pId);
    }

    public Student findByPersonId(int pId, int concurrencyValue)
    {
        return studentMapper.findByPersonId(pId, concurrencyValue);
    }

    public List<Student> getAll()
    {
        return studentMapper.getAllStudents();
    }

    public void updateStudent(Student pStudent)
    {
        studentMapper.update(pStudent);
    }

    public void insertStudent(Student pStudent)
    {
        studentMapper.insert(pStudent);
    }

    public void deleteStudent(Student pStudent)
    {
        studentMapper.delete(pStudent);
    }

    public void deleteStudentById(int pStudentID)
    {
        studentMapper.deleteStudentById(pStudentID);
    }
}
