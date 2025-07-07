package com.primeiro_projeto.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController()
@RequestMapping("/primeiraCotroller")
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

    @PostMapping("/primeiroMetodoPost")
    public String primeiroMetodoPost(@RequestBody Usuario usuario) {        
        return "Parabéns " + usuario.username() + ", você conseguiu fazer um POST!";
    }

    @PostMapping("/primeiroMetodoComHeader")
    public String primeiroMetodoComHeader(@RequestHeader("username") String username) {
        
        return "Parabéns " + username + ", você conseguiu fazer um POST com header!";
    }


    @PostMapping("/primeiroMetodoComMultiplosHeader")
    public String primeiroMetodoComMultiplosHeader(@RequestHeader Map<String,String> headers) {

        return "Parabéns , você conseguiu fazer um POST com múltiplos headers!" + headers.entrySet();
    }
    
    @PostMapping("/metodoComResponseEntity/{id}")
    public ResponseEntity<Object> metodoComResponseEntity(@PathVariable(name = "id") Long id) {
        
        Usuario entity = new Usuario("henry");
        
        if(id> 5 ){
            return ResponseEntity.badRequest().body(new Response("O ID não pode ser maior que 5"));
        }

        return ResponseEntity.ok(entity);
    }
    

    record Usuario(String username) {
    }
    
    public record Response(String message) {
    }
}
