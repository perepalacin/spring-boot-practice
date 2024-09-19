package com.perepalacin.thymleaf.demo.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

    @GetMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }

    @GetMapping("/processForm")
    public String processForm(Model model, @RequestParam String studentName) {
        model.addAttribute("name", studentName);
        return "helloworld";
    }

    @GetMapping("/process-form-v2")
    public String letsShoud(HttpServletRequest request, Model model) {
        String name = request.getParameter("studentName"); //This is the same as adding the @RequestParam annotation on the method arguments!
        String result = "YO! " + name.toUpperCase();
        model.addAttribute("name", result);
        return "helloworld";
    }
}
