package com.stefanini.latam.spring_security_studies.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class HelloController {


    @PostMapping
    public ResponseEntity<ResponseAuthenticate> auth(
            @RequestBody RequestAuthenticate request
    ){
        System.out.println(request);
        return ResponseEntity.ok().body(new ResponseAuthenticate(UUID.randomUUID().toString()));
    }

    public record RequestAuthenticate(String email, String password){
        @Override
        public String toString() {
            return "RequestAuthenticate{" +
                    "email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

    @GetMapping("/admin")
    public ResponseEntity<ResponseMessage> adminResource(){
        return ResponseEntity.ok().body(new ResponseMessage("congratulations admin resource"));
    }

    public record ResponseMessage(String message){}

    public record ResponseAuthenticate(String token){}
}
