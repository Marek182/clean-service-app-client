package sk.pasto.cleanserviceappclient.modelDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;


import javax.validation.constraints.*;
import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class House {

    private Integer resourceId;

    @Pattern(message="Zlý formát názvu ulice",
            regexp="^[A-ZÁ-Ž]([a-zá-ž]){0,1}(\\s[A-ZÁ-Ža-zá-ž]){0,1}[a-zá-ž]{2,10}(\\s[A-ZÁ-Ž][a-zá-ž]{2,7}){0,1}$")
    private String street;

    @Digits(integer=4, fraction=0, message = "Zlá hodnota, alebo mimo rozsah <1..9999>")
    private String houseNumber;

    @Digits(integer=2, fraction=0, message = "Zlá hodnota, alebo mimo rozsah <1..99>")
    private String entryNumber;

    @Pattern(message="Zlý formát názvu mesta", regexp="^[A-ZÁ-Ž][a-zá-ž]{2,7}(\\s[A-ZÁ-Ž][a-zá-ž]{2,7}){0,1}$")
    private String city;

    private List<Person> persons;

    private List<Service> services;

}
