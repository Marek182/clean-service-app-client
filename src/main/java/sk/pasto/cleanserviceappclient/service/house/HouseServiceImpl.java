package sk.pasto.cleanserviceappclient.service.house;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sk.pasto.cleanserviceappclient._core.service.AbstractService;
import sk.pasto.cleanserviceappclient.modelDTO.House;
import sk.pasto.cleanserviceappclient.modelDTO.Person;
import sk.pasto.cleanserviceappclient.service.person.PersonService;
import sk.pasto.cleanserviceappclient.utils.Utils;

import java.util.*;

@Service
public class HouseServiceImpl extends AbstractService<House> implements HouseService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PersonService personService;

    public HouseServiceImpl(RestTemplate restTemplate, @Value("${base.api.path}") String basePath) {
        super(restTemplate, basePath + "houses");
    }

    // retrieve house by id
    public House findById(int id) {
        House house = getHouseById(id).getContent();
        return house;
    }

    public List<Person> findPersonsByHouseId(int id) {
        Resources<Resource<Person>> resource = getPersonResourcesByHouseId(id);
        Collection<Resource<Person>> content = resource.getContent();
        List<Person> persons = new ArrayList<>();
        for (Resource<Person> res : content) {
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

    public void addPersonToHouse(int houseId, int personId) {
        String houseUrl = BASE_API_PATH + "/" + houseId + "/persons";
        // retrieve exists persons for house
        Resources<Resource<Person>> personResources = getPersonResourcesByHouseId(houseId);
        // retrieve new person who will by add
        Resource<Person> personResource = personService.findPersonById(personId);
        // create string of all persons
        String links = Utils.getLinksForAssociatedEntity(personResources, personResource);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-type", "text/uri-list");
        HttpEntity<String> httpEntity = new HttpEntity<>(
                links, requestHeaders);

        ResponseEntity<String> exchange = restTemplate.exchange(houseUrl,
                HttpMethod.PUT, httpEntity, String.class);

    }

    /**
     *
     * HELPER METHODS
     *
     */

    public Resources<Resource<Person>> getPersonResourcesByHouseId(int id) {
        String url = BASE_API_PATH + "/" + id + "/persons";
        ResponseEntity<Resources<Resource<Person>>> responseEntities = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<Resources<Resource<Person>>>() {});
        Resources<Resource<Person>> resources = responseEntities.getBody();
        return resources;
    }

    public Resource<House> getHouseById(int id) {
        String url = BASE_API_PATH + "/" + id;
        ResponseEntity<Resource<House>> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<Resource<House>>() {});
        Resource<House> resource = responseEntity.getBody();
        return resource;
    }
}







