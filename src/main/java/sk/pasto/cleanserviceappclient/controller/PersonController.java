package sk.pasto.cleanserviceappclient.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.pasto.cleanserviceappclient.modelDTO.Person;
import sk.pasto.cleanserviceappclient.service.house.HouseService;
import sk.pasto.cleanserviceappclient.service.person.PersonService;

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

    @GetMapping("/{id}")
    public String showPersonById(@PathVariable int id, Model model) {
        Resource<Person> personResource = personService.findPersonById(id);
        Person person = personResource.getContent();
        model.addAttribute("person", person);
        return "person";
    }

    @GetMapping("/{id}/delete")
    public String deletePerson(@PathVariable Integer id) {
        personService.deleteById(id);
        return "redirect:/api/persons";
    }
}
