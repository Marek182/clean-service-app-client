package sk.pasto.cleanserviceappclient.modelDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {

    private int resourceId;

    private String firstName;

    private String lastName;

    private List<House> houses;

}
