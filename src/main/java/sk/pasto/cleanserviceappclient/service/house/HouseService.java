package sk.pasto.cleanserviceappclient.service.house;

import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import sk.pasto.cleanserviceappclient._core.service.AbstractService;
import sk.pasto.cleanserviceappclient.modelDTO.House;
import sk.pasto.cleanserviceappclient.modelDTO.Person;

import java.util.List;

public interface HouseService {
    List<House> findAll();
    List<House> findAll(String sort);
    Resource<House> findByIdV2(int id);
    House findByIdV1(int id);
    List<Person> findPersonByHouseId(int id);
    void save(House house);
    ResponseEntity<House> getForEntity(int id);
}
