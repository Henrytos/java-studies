package com.estudo.hexagonal_ddd.infrastructure.adapters.inbound.rest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

//    @Autowired
//    private UserServicePort userService;
//
//
//    @PostMapping()
//    public ResponseEntity<String> auth(
//            @RequestBody() String email,
//            @RequestBody() String password
//    ){
//        String token = this.userService.authenticate(
//                email,
//                password
//        );
//
//        return ResponseEntity.ok(token);
//    }
}
