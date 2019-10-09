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
    public List<Person> findAll() {


        return null;
    }
}
