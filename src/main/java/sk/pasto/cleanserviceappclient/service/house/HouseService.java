package sk.pasto.cleanserviceappclient.service.house;

import sk.pasto.cleanserviceappclient.modelDTO.House;
import sk.pasto.cleanserviceappclient.modelDTO.Person;

import java.util.List;

public interface HouseService {
    public List<House> findAll();
    public List<House> findAll(String sort);
    public House findById(int id);
    public List<Person> findPersonByHouseId(int id);
    public void save(House house);
}
