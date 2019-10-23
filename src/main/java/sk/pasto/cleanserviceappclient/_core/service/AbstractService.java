package sk.pasto.cleanserviceappclient._core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractService<T> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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

//    public Resource<T> findByIdV2(int id) {
//        String url = BASE_API_PATH +"/"+ id;
//        logger.info("{}", url);
//        ResponseEntity<Resource<T>> responseEntity = restTemplate.exchange(
//                url, HttpMethod.GET, null, new ParameterizedTypeReference<Resource<T>>() {});
//        Resource<T> t = responseEntity.getBody();
//        return t;
//    }


}
