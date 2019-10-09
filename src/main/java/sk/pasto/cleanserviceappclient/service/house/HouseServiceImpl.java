package sk.pasto.cleanserviceappclient.service.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sk.pasto.cleanserviceappclient.modelDTO.House;
import sk.pasto.cleanserviceappclient.modelDTO.Person;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {

    private final String BASE_REST_URL = "http://localhost:8080/api/houses";

    //http://localhost:8080/api/houses?sort=street

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<House> findAll() {
        ResponseEntity<PagedResources<House>> responseEntity = restTemplate.exchange(
                BASE_REST_URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<PagedResources<House>>() {});
        PagedResources<House> resources = responseEntity.getBody();
        List<House> houses = new ArrayList(resources.getContent());

        return houses;
    }

    @Override
    public List<House> findAll(String sort) {

        String url = BASE_REST_URL + "?sort=" + sort;

        ResponseEntity<PagedResources<House>> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<PagedResources<House>>() {});
        PagedResources<House> resources = responseEntity.getBody();
        List<House> houses = new ArrayList<>(resources.getContent());

        return houses;
    }

    public House findById(int id) {
        House house = restTemplate.getForObject(BASE_REST_URL + "/" + id, House.class);
        return house;
    }

    public List<Person> findPersonByHouseId(int id) {
        ResponseEntity<PagedResources<Person>> responseEntity = restTemplate.exchange(
                BASE_REST_URL + "/" + id + "/persons", HttpMethod.GET, null,
                new ParameterizedTypeReference<PagedResources<Person>>() {});
        PagedResources<Person> resources = responseEntity.getBody();
        List<Person> persons = new ArrayList<>(resources.getContent());

        return persons;
    }

    @Override
    public void save(House house) {
        HttpEntity<House> entity = new HttpEntity<>(house);
        House response = restTemplate.postForObject(BASE_REST_URL, entity, House.class);
    }
}






