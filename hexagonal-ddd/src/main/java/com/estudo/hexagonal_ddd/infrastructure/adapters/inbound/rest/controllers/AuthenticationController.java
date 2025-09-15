package com.estudo.hexagonal_ddd.infrastructure.adapters.inbound.rest.controllers;

import com.estudo.hexagonal_ddd.application.ports.inbound.UserServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserServicePort userService;


    @PostMapping()
    public ResponseEntity<String> auth(
            @RequestBody() String email,
            @RequestBody() String password
    ){
        String token = this.userService.authenticate(
                email,
                password
        );

        return ResponseEntity.ok(token);
    }
}
