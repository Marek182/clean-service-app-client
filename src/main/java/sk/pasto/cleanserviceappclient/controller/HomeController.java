package sk.pasto.cleanserviceappclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping(value = {"", "/", "/home"})
    public String showHome() {
        return "index";
    }

    @GetMapping("/api/dashboard")
    public String showDashboard() {
        return "dashboard";
    }
}
