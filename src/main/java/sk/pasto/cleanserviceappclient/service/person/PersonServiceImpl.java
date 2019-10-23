package sk.pasto.cleanserviceappclient.service.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sk.pasto.cleanserviceappclient.modelDTO.Person;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final RestTemplate restTemplate;

    @Autowired
    public PersonServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Person findById(int id) {
        String url = "http://localhost:8080/api/persons/" + id;
        Person person = restTemplate.getForObject(url, Person.class);
        return person;
    }

}
