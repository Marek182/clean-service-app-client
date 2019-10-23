package sk.pasto.cleanserviceappclient.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sk.pasto.cleanserviceappclient.modelDTO.House;
import sk.pasto.cleanserviceappclient.modelDTO.Person;
import sk.pasto.cleanserviceappclient.service.house.HouseService;
import sk.pasto.cleanserviceappclient.service.person.PersonService;

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

//    @GetMapping("/{id}")
//    public String showHouseById(@PathVariable("id") int id, Model model) {
//        House house = houseService.findByIdV1(id);
//        model.addAttribute("house", house);
//        return "house";
//    }

//    @GetMapping("/{id}")
//    public String showHouseById(@PathVariable("id") int id, Model model) {
//        Resource<House> houseResource = houseService.findByIdV2(id);
//        logger.info("{}", houseResource);
//        House house = houseResource.getContent();
//        Link link = houseResource.getLink("persons");
//        String l = link.getHref();
//        model.addAttribute("house", house);
//        model.addAttribute("link", l);
//        return "house";
//    }

    @GetMapping("/{id}")
    public String showHouseById(@PathVariable("id") int id, Model model) {
        ResponseEntity<House> houseResource = houseService.getForEntity(id);
        logger.info("{}", houseResource);
        House house = houseResource.getBody();
//        Link link = houseResource.getLink("persons");
//        String l = link.getHref();
        model.addAttribute("house", house);
//        model.addAttribute("link", l);
        return "house";
    }

    @GetMapping("/{id}/updateForm")
    public String showFormForUpdate(@PathVariable("id") int id, Model model) {
        House house = houseService.findByIdV1(id);

        model.addAttribute("house", house);

        return "form-house";
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






