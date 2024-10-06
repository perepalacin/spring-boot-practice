package com.pere_palacin.OAuth.controllers;

import com.pere_palacin.OAuth.models.RegisteredUser;
import com.pere_palacin.OAuth.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public RegisteredUser register (RegisteredUser user) {
        return authService.register(user);
    }

}
