package sk.pasto.cleanserviceappclient;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;

import org.springframework.hateoas.hal.Jackson2HalModule;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class CleanserviceappclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CleanserviceappclientApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.registerModule(new Jackson2HalModule());

		MappingJackson2HttpMessageConverter converter1 = new MappingJackson2HttpMessageConverter();
		converter1.setSupportedMediaTypes(MediaType.parseMediaTypes("application/hal+json"));
		converter1.setObjectMapper(mapper);

		StringHttpMessageConverter converter2 = new StringHttpMessageConverter();

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(converter1);
		messageConverters.add(converter2);

		restTemplate.setMessageConverters(messageConverters);
//		return new RestTemplate(Arrays.asList(converter));
		return restTemplate;
	}


	@Bean
	public TestRestTemplate template() {
		return new TestRestTemplate();
	}

}
