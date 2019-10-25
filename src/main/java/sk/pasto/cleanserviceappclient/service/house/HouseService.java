package sk.pasto.cleanserviceappclient.service.house;

import sk.pasto.cleanserviceappclient.modelDTO.House;
import sk.pasto.cleanserviceappclient.modelDTO.Person;

import java.util.List;

public interface HouseService {

    List<House> findAll();
    List<House> findAll(String sort);
    House findById(int id);
    void deleteById(Integer id);
    List<Person> findPersonsByHouseId(int id);
    void save(House house);
    void addPersonToHouse(int houseId, int personId);
    void deletePersonFromHouse(Integer houseId, Integer personId);

}
