package sk.pasto.cleanserviceappclient.service.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sk.pasto.cleanserviceappclient._core.service.AbstractService;
import sk.pasto.cleanserviceappclient.modelDTO.House;
import sk.pasto.cleanserviceappclient.modelDTO.Person;
import sk.pasto.cleanserviceappclient.util.Utils;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseServiceImpl extends AbstractService<House> implements HouseService {

    public HouseServiceImpl(RestTemplate restTemplate, @Value("${base.api.path}") String basePath) {
        super(restTemplate, basePath + "houses");
    }


    //    @Override
//    public List<House> findAll() {
//
//        String url = BASE_REST_URL + "/houses";
//
//        ResponseEntity<PagedResources<House>> responseEntity = restTemplate.exchange(
//                url, HttpMethod.GET, null,
//                new ParameterizedTypeReference<PagedResources<House>>() {});
//        PagedResources<House> resources = responseEntity.getBody();
//        List<House> houses = new ArrayList(resources.getContent());
//
//        return houses;
//    }

//    @Override
//    public List<House> findAll(String sort) {
//
////        String url = BASE_REST_URL + "/houses?sort=" + sort;
//        String url = "http://localhost:8080/api/houses?sort=" + sort;
//
//        ResponseEntity<PagedResources<House>> responseEntity = restTemplate.exchange(
//                url, HttpMethod.GET, null,
//                new ParameterizedTypeReference<PagedResources<House>>() {});
//        PagedResources<House> resources = responseEntity.getBody();
//        List<House> houses = new ArrayList<>(resources.getContent());
//
//        return houses;
//    }

//    public House findById(int id) {
//
////        String url = BASE_REST_URL + "/houses/" + id;
//        String url = "http://localhost:8080/api/houses/" + id;
//
//        House house = restTemplate.getForObject(url, House.class);
//        return house;
//    }

    public List<Person> findPersonByHouseId(int id) {

//        String url = BASE_REST_URL + "/houses/" + id + "/persons";
        String url = "http://localhost:8080/api/houses/" + id + "/persons";

        ResponseEntity<PagedResources<Person>> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<PagedResources<Person>>() {});
        PagedResources<Person> resources = responseEntity.getBody();
        List<Person> persons = new ArrayList<>(resources.getContent());

        return persons;
    }

    @Override
    public void save(House house) {

//        String url = BASE_REST_URL + "/houses";
        String url = "http://localhost:8080/api/houses";

        HttpEntity<House> entity = new HttpEntity<>(house);
        House response = restTemplate.postForObject(url, entity, House.class);
    }
}





