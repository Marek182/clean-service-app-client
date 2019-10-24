package sk.pasto.cleanserviceappclient.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/houses")
public class HouseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private HouseService houseService;
    @Autowired
    private PersonService personService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping(value = {"/", ""})
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

    @GetMapping("/{id}")
    public String showHouseById(@PathVariable int id, Model model) {
        House house = houseService.findById(id);
        model.addAttribute("house", house);
        return "house";
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
        ID newPersonId = new ID();

        model.addAttribute("houseId", id);
        model.addAttribute("oldPersons", oldPersons);
        model.addAttribute("allPersons", allPersons);
        model.addAttribute("newPersonId", newPersonId);
        return "house-person";
    }

    @PostMapping("/{houseId}/addPersonToHouse")
    public String addPersonToHouse(@PathVariable int houseId, @ModelAttribute("personId") ID personId) {
        houseService.addPersonToHouse(houseId, personId.getId());
        return "redirect:/api/houses/{houseId}/persons";
    }

    @GetMapping("/addForm")
    public String showFormForAdd(Model model) {
        House house = new House();
        model.addAttribute("house", house);
        return "form-house";
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

    @GetMapping("/adperson")
    public String addPersons() {
        houseService.addPersonToHouse(3, 8);
        return "list-houses";
    }

}






