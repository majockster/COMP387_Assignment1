package assignment.team._387a2.mappers;

import assignment.team._387a2.domainObjects.DomainObject;

public abstract class DataMapper {
    public abstract DomainObject find(int id);

    public abstract void update(DomainObject o);

    public abstract void insert(DomainObject o);

    public abstract void delete(DomainObject o);



}
