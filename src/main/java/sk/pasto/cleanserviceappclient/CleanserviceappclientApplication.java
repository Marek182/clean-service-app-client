package sk.pasto.cleanserviceappclient;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.hateoas.hal.Jackson2HalModule;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
public class CleanserviceappclientApplication {

	@Autowired
	RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(CleanserviceappclientApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.registerModule(new Jackson2HalModule());

		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/hal+json"));
		converter.setObjectMapper(mapper);
		return new RestTemplate(Arrays.asList(converter));
	}

//	@Override
//	public void run(String... args) throws Exception {
//		RestTemplate restTemplate = restTemplate();
//
//		String url = "http://localhost:8080/api/houses/1/persons";
//
//		ResponseEntity<PagedResources<Person>> responseEntity = restTemplate.exchange(
//				url, HttpMethod.GET, null,
//				new ParameterizedTypeReference<PagedResources<Person>>() {});
//		PagedResources<Person> resources = responseEntity.getBody();
//		List<Person> houses = new ArrayList(resources.getContent());
//		System.out.println(houses);
//		System.out.println(houses.get(0).getClass());

//		ResponseEntity<PagedResources<House>> responseEntity = restTemplate.exchange(
//				url, HttpMethod.GET, null,
//				new ParameterizedTypeReference<PagedResources<House>>() {});
//		PagedResources<House> resources = responseEntity.getBody();
//		List<House> houses = new ArrayList(resources.getContent());
//		System.out.println(houses);
//		System.out.println(houses.get(0).getClass());

//	}

}
