package assignment.team._387a2.tableGateways;

import assignment.team._387a2.mappers.AdministratorMapper;
import assignment.team._387a2.domainObjects.Administrator;

import java.util.List;

public class AdministratorTableGateway {
    private AdministratorMapper administratorMapper;

    public AdministratorTableGateway() {
        administratorMapper = new AdministratorMapper();
    }

    public void updateAdministrator(Administrator pAdministrator) {
        administratorMapper.update(pAdministrator);
    }

    public Administrator findByEmploymentId(int employmentId) {
        return administratorMapper.find(employmentId);
    }

    public Administrator findByPersonId(int personId) {
        return administratorMapper.findByPersonId(personId);
    }

    public List<Administrator> getAll() {
        return administratorMapper.getAllAdministrators();
    }

    public void insertAdministrator(Administrator pAdministrator) {
        administratorMapper.insert(pAdministrator);
    }

    public void deleteAdministrator(Administrator pAdministrator) {
        administratorMapper.delete(pAdministrator);
    }

    public void deleteAdministratorById(int pEmploymentID) {
        administratorMapper.deleteByEmploymentId(pEmploymentID);
    }

}