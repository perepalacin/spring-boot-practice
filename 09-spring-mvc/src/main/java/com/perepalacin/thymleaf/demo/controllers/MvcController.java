package com.perepalacin.thymleaf.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MvcController {

    @GetMapping("/hello")
    public String sayHello(Model model) {
        model.addAttribute("date", java.time.LocalDateTime.now());
        return "helloworld";
    }

}
