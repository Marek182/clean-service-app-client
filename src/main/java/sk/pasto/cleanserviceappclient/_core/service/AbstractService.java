package sk.pasto.cleanserviceappclient._core.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractService<T> {

    protected String BASE_API_PATH;
    protected RestTemplate restTemplate;

    protected AbstractService(RestTemplate restTemplate, String baseUrl) {
        this.restTemplate = restTemplate;
        this.BASE_API_PATH = baseUrl;
    }

    public List<T> findAll() {
        ResponseEntity<PagedResources<T>> response = restTemplate.exchange(
                BASE_API_PATH, HttpMethod.GET, null, new ParameterizedTypeReference<PagedResources<T>>() {});
        PagedResources<T> resources = response.getBody();
        List<T> result = new ArrayList<>(resources.getContent());
        return result;
    }

    public List<T> findAll(String sort) {
        String url = BASE_API_PATH + "?sort=" + sort;
        ResponseEntity<PagedResources<T>> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<PagedResources<T>>() {});
        PagedResources<T> resources = responseEntity.getBody();
        List<T> result = new ArrayList<>(resources.getContent());

        return result;
    }

//    public T findById(int id) {
//        String url = "http://localhost:8080/api/houses/" + id;
//        ResponseEntity<T> responseEntity = restTemplate.exchange(
//                url, HttpMethod.GET, null, new ParameterizedTypeReference<T>() {});
//        return responseEntity.getBody();
//    }


}
