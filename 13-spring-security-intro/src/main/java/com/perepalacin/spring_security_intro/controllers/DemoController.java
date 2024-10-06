package com.perepalacin.spring_security_intro.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/demo")
@RequiredArgsConstructor
public class DemoController {

    @GetMapping("")
    public ResponseEntity<String> getHelloWorld () {
        return ResponseEntity.ok("Hello world from secured endpoint!");
    }

}
