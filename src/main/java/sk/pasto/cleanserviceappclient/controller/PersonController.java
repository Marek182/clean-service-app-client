package sk.pasto.cleanserviceappclient.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sk.pasto.cleanserviceappclient.modelDTO.House;
import sk.pasto.cleanserviceappclient.modelDTO.Person;
import sk.pasto.cleanserviceappclient.service.house.HouseService;
import sk.pasto.cleanserviceappclient.service.person.PersonService;
import sk.pasto.cleanserviceappclient.utils.ID;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/persons")
public class PersonController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private PersonService personService;
    private HouseService houseService;

    public PersonController(PersonService personService, HouseService houseService) {
        this.personService = personService;
        this.houseService = houseService;
    }

    @GetMapping(value = {"", "/"})
    public String showPersons(Model model) {
        List<Person> persons = personService.findAll();
        model.addAttribute("persons", persons);
        return "persons-list";
    }

    @GetMapping("/addForm")
    public String showFormForAdd(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);
        return "person-form";
    }

    @GetMapping("/{id}")
    public String showPersonById(@PathVariable int id, Model model) {
        Resource<Person> personResource = personService.findPersonById(id);
        Person person = personResource.getContent();
        List<House> oldHouses = personService.findHousesByPersonId(id);
        List<House> notAddedHouses = personService.findNotAddedHousesByPersonId(id);
        ID newHouseId = new ID();

        model.addAttribute("person", person);
        model.addAttribute("oldHouses", oldHouses);
        model.addAttribute("allHouses", notAddedHouses);
        model.addAttribute("newHouseId", newHouseId);
        return "person";
    }

    @GetMapping("/{id}/delete")
    public String deletePerson(@PathVariable Integer id) {
        personService.deleteById(id);
        return "redirect:/api/persons";
    }

    @GetMapping("/{id}/updateForm")
    public String showFormForUpdate(@PathVariable int id, Model model) {
        Resource<Person> personResource = personService.findPersonById(id);
        Person person = personResource.getContent();
        model.addAttribute("person", person);
        return "person-form";
    }

    @PostMapping("/{personId}/addHouseToPerson")
    public String addHouseToPerson(@PathVariable int personId, @ModelAttribute("houseId") ID houseId) {
        Resource<House> houseResource = houseService.findById(houseId.getId());
        personService.addHouseToPerson(personId, houseResource);
        return "redirect:/api/persons/{personId}";
    }


    @GetMapping("/{personId}/houses/{houseId}/delete")
    public String deleteHouseFromPerson(@PathVariable Integer personId, @PathVariable Integer houseId) {
        houseService.deletePersonFromHouse(houseId, personId);
        return "redirect:/api/persons/{personId}";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("mPerson") Person person,
                       BindingResult result) {
        if (result.hasErrors()) {
            return "person-form";
        } else {
            personService.save(person);
            return "redirect:/api/persons";
        }

    }
}
