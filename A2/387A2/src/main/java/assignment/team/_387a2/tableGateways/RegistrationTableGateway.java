package assignment.team._387a2.tableGateways;

import assignment.team._387a2.domainObjects.Registration;
import assignment.team._387a2.mappers.RegistrationMapper;

import java.util.List;

public class RegistrationTableGateway
{
    private RegistrationMapper registrationMapper;

    public RegistrationTableGateway()
    {
        registrationMapper = new RegistrationMapper();
    }

    public Registration findById(int pId)
    {
        return registrationMapper.find(pId);
    }

    public Registration findByStudentIdAndCourseId(int pStudentId, int pCourseId, int concurrencyValue)
    {
        return registrationMapper.findByStudentIdAndCourseId(pStudentId, pCourseId, concurrencyValue);
    }

    public List<Registration> getAll()
    {
        return registrationMapper.getAll();
    }

    public void updateRegistration(Registration pRegistration)
    {
        registrationMapper.update(pRegistration);
    }

    public void insertRegistration(Registration pRegistration)
    {
        registrationMapper.insert(pRegistration);
    }

    public void deleteRegistration(Registration pRegistration)
    {
        registrationMapper.delete(pRegistration);
    }

    public void deleteRegistrationById(int pRegistrationId)
    {
        registrationMapper.deleteRegistrationById(pRegistrationId);
    }

    public void deleteRegistrationByStudentAndCourseId(int pStudentId, int pCourseId)
    {
        registrationMapper.deleteRegistrationByStudentAndCourseId(pStudentId, pCourseId);
    }
}
