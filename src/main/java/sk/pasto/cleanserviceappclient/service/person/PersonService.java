package sk.pasto.cleanserviceappclient.service.person;

import sk.pasto.cleanserviceappclient.modelDTO.Person;

import java.util.List;

public interface PersonService {
//    public List<Person> findAll();
    public Person findById(int id);
}

