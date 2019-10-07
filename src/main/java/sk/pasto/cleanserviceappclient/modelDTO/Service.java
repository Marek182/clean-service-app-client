package sk.pasto.cleanserviceappclient.modelDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Service {

    private int id;

    private String name;

    private String desc;

    private List<House> houses;

    public Service() {}



}
