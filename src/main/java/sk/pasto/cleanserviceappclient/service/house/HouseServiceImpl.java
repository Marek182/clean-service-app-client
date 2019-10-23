package sk.pasto.cleanserviceappclient.service.house;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sk.pasto.cleanserviceappclient._core.service.AbstractService;
import sk.pasto.cleanserviceappclient.modelDTO.House;
import sk.pasto.cleanserviceappclient.modelDTO.Person;

import java.util.*;

@Service
public class HouseServiceImpl extends AbstractService<House> implements HouseService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public HouseServiceImpl(RestTemplate restTemplate, @Value("${base.api.path}") String basePath) {
        super(restTemplate, basePath + "houses");
    }

    public ResponseEntity<House> getForEntity(int id) {
        String url = BASE_API_PATH + "/" + id;
        ResponseEntity<House> entity = restTemplate.getForEntity(url,
                House.class,
                Integer.toString(id));
        logger.info("Status code value: {}", entity.getStatusCodeValue());
        logger.info("HTTP Header 'ContentType': {}", entity.getHeaders().getContentType());
        return entity;
    }

        public House findByIdV1(int id) {
        String url = BASE_API_PATH + "/" + id;
        House house = restTemplate.getForObject(url, House.class);
        return house;
    }

    // add person to house
    public void addPersonToHouse(int id, Person person) {
        Resource<House> houseResource = findByIdV2(id);
        // link na ludi na dome http://localhost:8080/houses/{id}/persons
        String link = houseResource.getLink("persons").getHref();

        ResponseEntity<Resources<Resource<Person>>> responseEntities = restTemplate.exchange(
                link, HttpMethod.GET, null,
                new ParameterizedTypeReference<Resources<Resource<Person>>>() {});

        Collection<Resource<Person>> content = responseEntities.getBody().getContent();
        Set<String> urls = new HashSet<>();
        for (Resource<Person> res: content) {
            urls.add(res.getLink("self").getHref());
        }

    }

    public Resource<House> findByIdV2(int id) {
        String url = BASE_API_PATH + "/" + id;
        ResponseEntity<Resource<House>> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Resource<House>>() {});
        Resource<House> houseResource = responseEntity.getBody();
        return houseResource;
    }

    public List<Person> findPersonByHouseId(int id) {
        String url = BASE_API_PATH + "/" + id + "/persons";
        ResponseEntity<Resources<Resource<Person>>> responseEntities = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<Resources<Resource<Person>>>() {});
        Resources<Resource<Person>> resource = responseEntities.getBody();
        Collection<Resource<Person>> content = resource.getContent();
        List<Person> persons = new ArrayList<>();
        for (Resource<Person> res: content) {
            persons.add(res.getContent());
        }
        return persons;
    }

    @Override
    public void save(House house) {
        if ((house.getResourceId()) == null) {
            String url = BASE_API_PATH;
            HttpEntity<House> entity = new HttpEntity<>(house);
            House response = restTemplate.postForObject(url, entity, House.class);
//            restTemplate.exchange(url, HttpMethod.POST, entity, House.class);
        } else {
            String url = BASE_API_PATH + "/" + house.getResourceId();
            HttpEntity<House> entity = new HttpEntity<>(house);
//            restTemplate.exchange(url, HttpMethod.PUT, entity, House.class);
            restTemplate.put(url, entity);
        }

    }

}






