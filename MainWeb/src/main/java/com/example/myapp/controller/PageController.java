package com.example.myapp.controller;

import com.example.myapp.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/")
    public String home(Model model) {
        String weather = weatherService.getWeather("Kyiv");
        model.addAttribute("message", "Welcome to the Home Page!");
        model.addAttribute("weather", weather);
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("message", "About Us Page");
        return "about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("message", "Contact Us Page");
        return "contact";
    }
}
