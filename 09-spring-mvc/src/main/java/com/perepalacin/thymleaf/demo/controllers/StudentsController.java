package com.perepalacin.thymleaf.demo.controllers;

import com.perepalacin.thymleaf.demo.models.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentsController {

    public List<String> countries;

    public StudentsController() {
        countries = new ArrayList<>();
        countries.add("Spain");
        countries.add("Italy");
        countries.add("Germany");
        countries.add("Belguim");
    }


    @GetMapping("/register-student")
    public String registerStudentPage(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        model.addAttribute("countries", countries);
        return "studentForm";
    }

    @PostMapping("/process-student-form")
    public String processStudent(@ModelAttribute("student") Student student) {

        return "studentConfirmation";

    }
}
