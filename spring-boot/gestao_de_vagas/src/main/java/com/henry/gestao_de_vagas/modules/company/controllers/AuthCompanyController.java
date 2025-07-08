package com.henry.gestao_de_vagas.modules.company.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.henry.gestao_de_vagas.modules.company.dto.AuthCompanyDTO;
import com.henry.gestao_de_vagas.modules.company.useCases.AuthCompanyUseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {

    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @PostMapping("/company")
    public ResponseEntity<Object> login(@RequestBody AuthCompanyDTO authCompanyDTO) {
        try {
            System.out.println(authCompanyDTO.getPassword());
            System.out.println(authCompanyDTO.getUsername());

            var token = this.authCompanyUseCase.execute(authCompanyDTO);

            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
