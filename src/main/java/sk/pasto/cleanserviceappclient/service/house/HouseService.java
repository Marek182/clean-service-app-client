package sk.pasto.cleanserviceappclient.service.house;

import org.springframework.hateoas.Resource;
import sk.pasto.cleanserviceappclient.modelDTO.House;
import sk.pasto.cleanserviceappclient.modelDTO.Person;

import java.util.List;

public interface HouseService {

    List<House> findAll();
    List<House> findAll(String sort);
    Resource<House> findById(Integer id);
    List<Person> findNotAddedPersonsByHouseId(Integer id);
    void deleteById(Integer id);
    List<Person> findPersonsByHouseId(int id);
    void save(House house);
    void addPersonToHouse(int houseId, Resource<Person> personResource);
    void deletePersonFromHouse(Integer houseId, Integer personId);

}
