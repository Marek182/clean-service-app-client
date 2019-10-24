package sk.pasto.cleanserviceappclient.service.person;

import org.springframework.hateoas.Resource;
import sk.pasto.cleanserviceappclient.modelDTO.Person;

import java.util.List;

public interface PersonService {

    List<Person> findAll();
    Person findById(int id);
    Resource<Person> findPersonById(int id);

}

