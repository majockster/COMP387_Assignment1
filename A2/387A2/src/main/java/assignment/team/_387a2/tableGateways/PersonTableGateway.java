package assignment.team._387a2.tableGateways;

import assignment.team._387a2.mappers.PersonMapper;
import assignment.team._387a2.domainObjects.Person;

import java.util.List;

public class PersonTableGateway
{
    private PersonMapper personMapper;

    public PersonTableGateway()
    {
        personMapper = new PersonMapper();
    }

    public Person findById(int pId)
    {
        return personMapper.find(pId);
    }

    public Person findByLastName(String pLastName)
    {
        return personMapper.findByLastName(pLastName);
    }

    public List<Person> getAll()
    {
        return personMapper.getAll();
    }

    public Person findByEmail(String pEmail)
    {
        return personMapper.findByEmail(pEmail);
    }

    public void updatePerson(Person pPerson)
    {
        personMapper.update(pPerson);
    }

    public void insertPerson(Person pPerson)
    {
        personMapper.insert(pPerson);
    }

    public void deletePerson(Person pPerson)
    {
        personMapper.delete(pPerson);
    }

    public void deletePersonById(int pPersonId)
    {
        personMapper.deletePersonById(pPersonId);
    }
}
