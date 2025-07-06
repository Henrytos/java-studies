package com.primeiro_projeto.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController()
@RequestMapping("/primeiroCotroller")
public class PrimeiroController {

    @GetMapping("/primeiroMetodo")
    public String primeiroMetodo() {
        return "Parabéns henry você conseguiu!";
    }

    @GetMapping("/queryParam")
    public String queryParam(@RequestParam(name = "id") String id) {
        return "O valor do parâmetro é(queryParam): " + id;
    }
    
    @GetMapping("/routeParam/{id}")
    public String routeParam(@PathVariable(name = "id") String id) {
        return "O valor do parâmetro é(routeParam): " + id;
    }

    @GetMapping("/queryParamAll")
    public String queryParamAll(@RequestParam Map<String, String> allParams) {
        return "Todos os parâmetros são: " + allParams.toString(); // allParams.entrySet()
    }
}
