package sk.pasto.cleanserviceappclient.modelDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {

    private int respurceId;

    private String firstName;

    private String lastName;

    private List<House> houses;

    public Person() {}

}
