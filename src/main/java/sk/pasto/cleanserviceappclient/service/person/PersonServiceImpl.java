package sk.pasto.cleanserviceappclient.service.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sk.pasto.cleanserviceappclient._core.service.AbstractService;
import sk.pasto.cleanserviceappclient.modelDTO.Person;

@Service
public class PersonServiceImpl extends AbstractService<Person> implements PersonService {

    @Autowired
    public PersonServiceImpl(RestTemplate restTemplate, @Value("${base.api.path}") String basePath) {
        super(restTemplate, basePath + "persons");
    }

    @Override
    public Person findById(int id) {
        String url = BASE_API_PATH + "/" + id;
        Person person = restTemplate.getForObject(url, Person.class);
        return person;
    }

    public Resource<Person> findPersonById(int id) {
        String url = "http://localhost:8080/api/persons/" + id;
        ResponseEntity<Resource<Person>> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Resource<Person>>() {
                });
        Resource<Person> personResource = responseEntity.getBody();
        return personResource;
    }

}
