package sk.pasto.cleanserviceappclient.service.house;

import sk.pasto.cleanserviceappclient._core.service.AbstractService;
import sk.pasto.cleanserviceappclient.modelDTO.House;
import sk.pasto.cleanserviceappclient.modelDTO.Person;

import java.util.List;

public interface HouseService {
    List<House> findAll();
    List<House> findAll(String sort);
    House findById(int id);
    List<Person> findPersonByHouseId(int id);
    void save(House house);
}
