package sk.pasto.cleanserviceappclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.pasto.cleanserviceappclient.modelDTO.House;
import sk.pasto.cleanserviceappclient.service.HouseService;

import java.util.List;

@Controller
@RequestMapping("/api")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @GetMapping("/houses")
    public String showHouses(Model model) {
        List<House> houses = houseService.findAll();
        model.addAttribute("houses", houses);
        return "list-houses";
    }

}
