package com.perepalacin.restproject.controllers;

import com.perepalacin.restproject.exceptions.StudentErrorResponse;
import com.perepalacin.restproject.exceptions.StudentNotFoundException;
import com.perepalacin.restproject.models.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    private List<Student> studentList;

    @PostConstruct
    public void loadData() {
        this.studentList = new ArrayList<>();
        this.studentList.add(new Student("Pedro", "Piqué"));
        this.studentList.add(new Student("Maria", "Lopez"));
        this.studentList.add(new Student("Jose", "Calvo"));
        this.studentList.add(new Student("Miguel", "Andrés"));
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return this.studentList;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) throws StudentNotFoundException {
        if (studentId >= this.studentList.size() ||studentId < 0) {
            throw new StudentNotFoundException("Student id not found - "+ studentId);
        }
        return studentList.get(studentId);
    }
}
