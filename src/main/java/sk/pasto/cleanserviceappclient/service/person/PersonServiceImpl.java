package sk.pasto.cleanserviceappclient.service.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sk.pasto.cleanserviceappclient._core.service.AbstractService;
import sk.pasto.cleanserviceappclient.modelDTO.House;
import sk.pasto.cleanserviceappclient.modelDTO.HouseResource;
import sk.pasto.cleanserviceappclient.modelDTO.Person;
import sk.pasto.cleanserviceappclient.utils.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PersonServiceImpl extends AbstractService<Person> implements PersonService {

    public PersonServiceImpl(RestTemplate restTemplate, @Value("${base.api.path}") String basePath) {
        super(restTemplate, basePath + "persons");
    }

    public Resource<Person> findPersonById(int id) {
        String url = BASE_API_PATH + "/" + id;
        ResponseEntity<Resource<Person>> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Resource<Person>>() {
                });
        Resource<Person> personResource = responseEntity.getBody();
        return personResource;
    }

    @Override
    public List<House> findHousesByPersonId(Integer id) {
        String url = BASE_API_PATH + "/" + id + "/houses";
        ResponseEntity<Resources<Resource<House>>> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<Resources<Resource<House>>>(){
                });
        Collection<Resource<House>> housesResources = responseEntity.getBody().getContent();
        List<House> houses = new ArrayList<>();
        for (Resource<House> resource: housesResources) {
            houses.add(resource.getContent());
        }
        return houses;
    }

    @Override
    public List<House> findNotAddedHousesByPersonId(Integer id) {
        String url = BASE_API_PATH + "/" + id + "/houses/notadded";
        ResponseEntity<Resources<Resource<HouseResource>>> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<Resources<Resource<HouseResource>>>(){
                        });
        Collection<Resource<HouseResource>> resources = responseEntity.getBody().getContent();
        List<House> houses = new ArrayList<>();
        for (Resource<HouseResource> rp: resources) {
            houses.add(rp.getContent().getHouse());
        }
        return houses;
    }

    @Override
    public void save(Person person) {
        if ((person.getResourceId()) == null) {
            String url = BASE_API_PATH;
            HttpEntity<Person> entity = new HttpEntity<>(person);
            Person response = restTemplate.postForObject(url, entity, Person.class);
        } else {
            String url = BASE_API_PATH + "/" + person.getResourceId();
            HttpEntity<Person> entity = new HttpEntity<>(person);
            restTemplate.put(url, entity);
        }

    }

    @Override
    public void addHouseToPerson(int personId, Resource<House> houseResource) {
        String houseUrl = BASE_API_PATH + "/" + personId + "/houses";
        Resources<Resource<House>> houseResources = getHousesByPersonId(personId);
        String links = Utils.getLinksForAssociatedEntity(houseResources, houseResource);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-type", "text/uri-list");
        HttpEntity<String> httpEntity = new HttpEntity<>(
                links, requestHeaders);

        ResponseEntity<String> exchange = restTemplate.exchange(houseUrl,
                HttpMethod.PUT, httpEntity, String.class);
    }

    /**
     *
     * HELPER
     *
     */

    private Resources<Resource<House>> getHousesByPersonId(Integer id) {
        String url = BASE_API_PATH + "/" + id + "/houses";
        ResponseEntity<Resources<Resource<House>>> responseEntities = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<Resources<Resource<House>>>() {});
        Resources<Resource<House>> resources = responseEntities.getBody();
        return resources;
    }

}
