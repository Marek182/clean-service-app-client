package sk.pasto.cleanserviceappclient.service.person;

import org.springframework.hateoas.Resource;
import sk.pasto.cleanserviceappclient.modelDTO.House;
import sk.pasto.cleanserviceappclient.modelDTO.Person;

import java.util.List;

public interface PersonService {

    List<Person> findAll();
    Resource<Person> findPersonById(int id);
    List<House> findHousesByPersonId(Integer id);
    List<House> findNotAddedHousesByPersonId(Integer id);
    void deleteById(Integer id);
    void save(Person person);
    void addHouseToPerson(int personId, Resource<House> houseResource);
}

