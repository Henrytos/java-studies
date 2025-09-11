package com.estudo.hexagonal_ddd.infrastructure.adapters.inbound.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {

    @GetMapping()
    String hello(){
        return "hello world";
    }

}
