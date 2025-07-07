package com.henry.gestao_de_vagas.modules.candidate;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping ("/candidate")
public class CandidateController {
    

    @PostMapping("/")
    public String create(@Valid @RequestBody CandidateEntity entity) {
        System.out.println(entity.getEmail());
        System.out.println(entity.getName());
        System.out.println(entity.getUsername());
        System.out.println(entity.getPassword());
        System.out.println(entity.getDescription());
        System.out.println(entity.getCurriculum());

        return "Candidate created with username: " + entity.getUsername();
    }
    
}
