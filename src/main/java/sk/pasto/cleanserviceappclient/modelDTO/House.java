package sk.pasto.cleanserviceappclient.modelDTO;

import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true)
public class House {

    private int resourceId;

    private String street;

    private int houseNumber;

    private int entryNumber;

    private String city;

    private List<Person> persons;

    private List<Service> services;



}
