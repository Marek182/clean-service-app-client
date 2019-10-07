package sk.pasto.cleanserviceappclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sk.pasto.cleanserviceappclient.modelDTO.House;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<House> findAll() {

        String url = "http://localhost:8080/api/houses";

        ResponseEntity<PagedResources<House>> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<PagedResources<House>>() {});
        PagedResources<House> resources = responseEntity.getBody();
        List<House> houses = new ArrayList(resources.getContent());

        return houses;
    }
}
