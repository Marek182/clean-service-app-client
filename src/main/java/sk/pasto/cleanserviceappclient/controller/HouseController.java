package sk.pasto.cleanserviceappclient.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/api/houses")
public class HouseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private HouseService houseService;
    private PersonService personService;

    public HouseController(HouseService houseService, PersonService personService) {
        this.houseService = houseService;
        this.personService = personService;
    }

    @GetMapping(value = {"", "/"})
    public String showHouses(Model model) {
        List<House> houses = houseService.findAll();
        model.addAttribute("houses", houses);
        return "list-houses";
    }

    @GetMapping("/sort")
    public String showHousesAscDesc(Model model, @RequestParam("param") String param)  {
        List<House> houses = houseService.findAll(param);
        model.addAttribute("houses", houses);
        return "list-houses";
    }

    @GetMapping("/addForm")
    public String showFormForAdd(Model model) {
        House house = new House();
        model.addAttribute("house", house);
        return "form-house";
    }

    @GetMapping("/{id}")
    public String showHouseById(@PathVariable int id, Model model) {
        House house = houseService.findById(id);
        model.addAttribute("house", house);
        return "house";
    }

    @GetMapping("/{id}/delete")
    public String deleteHouse(@PathVariable Integer id) {
        houseService.deleteById(id);
        return "redirect:/api/houses";
    }

    @GetMapping("/{id}/updateForm")
    public String showFormForUpdate(@PathVariable int id, Model model) {
        House house = houseService.findById(id);
        model.addAttribute("house", house);
        return "form-house";
    }

    @GetMapping("/{id}/persons")
    public String showPersonsByHouseId(@PathVariable int id, Model model) {
        List<Person> oldPersons = houseService.findPersonsByHouseId(id);
        List<Person> allPersons = personService.findAll();
        House house = houseService.findById(id);
        ID newPersonId = new ID();

        model.addAttribute("oldPersons", oldPersons);
        model.addAttribute("allPersons", allPersons);
        model.addAttribute("house", house);
        model.addAttribute("newPersonId", newPersonId);
        return "house-person";
    }

    @PostMapping("/{houseId}/addPersonToHouse")
    public String addPersonToHouse(@PathVariable int houseId, @ModelAttribute("personId") ID personId) {
        houseService.addPersonToHouse(houseId, personId.getId());
        return "redirect:/api/houses/{houseId}/persons";
    }

    @GetMapping("/{houseId}/persons/{personId}/delete")
    public String deletePersonFromHouse(@PathVariable Integer houseId, @PathVariable Integer personId) {
        houseService.deletePersonFromHouse(houseId, personId);
        return "redirect:/api/houses/{houseId}/persons";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("house") House house,
                       BindingResult result) {
        if (result.hasErrors()) {
            return "form-house";
        } else {
            houseService.save(house);
            return "redirect:/api/houses";
        }

    }
}






