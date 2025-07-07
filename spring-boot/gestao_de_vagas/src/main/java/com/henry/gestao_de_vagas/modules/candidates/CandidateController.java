package com.henry.gestao_de_vagas.modules.candidates;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping ("/candidates")
public class CandidateController {
    

    @PostMapping("path")
    public String create(@RequestBody CandidateEntity entity) {

        return "Candidate created with username: " + entity.getUsername();
    }
    
}
