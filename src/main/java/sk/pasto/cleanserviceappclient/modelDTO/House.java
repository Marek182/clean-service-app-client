package sk.pasto.cleanserviceappclient.modelDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.*;
import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class House {

    private Integer resourceId;

    @Size(message = "Názov ulice musí byť v rozsahu 2-20 písmen", min = 2, max = 20)
    private String street;

    @Min(message = "Cislo domu musi byť v rozsahu 1 až 9999", value = 1)
    @Max(message = "Cislo domu musi byť v rozsahu 1 až 9999", value = 9999)
    private int houseNumber;

    @Min(message = "Cislo vchodu musi byť v rozsahu 1 až 99", value = 1)
    @Max(message = "Cislo vchodu musí byť v rozsahu 1 až 99", value = 99)
    private int entryNumber;

    @Size(message = "Názov mesta musí byť v rozsahu 3 až 20 písmen", min = 3, max = 20)
    private String city;

    private List<Person> persons;

    private List<Service> services;

}
