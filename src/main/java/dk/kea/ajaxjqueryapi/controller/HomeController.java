package dk.kea.ajaxjqueryapi.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
    @GetMapping("/")
    public String index(Model model){
        return "index";
    }

}
