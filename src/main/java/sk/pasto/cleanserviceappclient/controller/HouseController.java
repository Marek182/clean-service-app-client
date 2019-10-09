package sk.pasto.cleanserviceappclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sk.pasto.cleanserviceappclient.modelDTO.House;
import sk.pasto.cleanserviceappclient.modelDTO.Person;
import sk.pasto.cleanserviceappclient.service.house.HouseService;

import java.util.List;

@Controller
@RequestMapping("/api/houses")
public class HouseController {

    private HouseService houseService;

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
    public String showHouseById(@PathVariable("id") int id, Model model) {
        House house = houseService.findById(id);
        model.addAttribute("house", house);
        return "house";
    }

    @GetMapping("/{id}/persons")
    public String showPersonsByHouseId(@PathVariable("id") int id, Model model) {
        List<Person> persons = houseService.findPersonByHouseId(id);
        model.addAttribute("persons", persons);
        return "house-person";
    }

    @GetMapping("/addForm")
    public String showFormForAdd(Model model) {
        House house = new House();
        model.addAttribute("house", house);
        return "form-house";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("house") House house) {
        houseService.save(house);
        return "redirect:/api/houses";
    }
}
