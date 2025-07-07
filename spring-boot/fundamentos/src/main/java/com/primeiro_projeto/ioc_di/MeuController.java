package com.primeiro_projeto.ioc_di;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/meuController")
public class MeuController {

    @Autowired
    MeuComponente meuComponente;

    // Exemplo de método POST que chama um componente TOTALMENTE ERRADO!!!!
    // @PostMapping("/")
    // public String postMethodName() {
    //     MeuCompoente meuCompoente = new MeuCompoente();

    //     return meuCompoente.chamarComponenete();
    // }    

    // Exemplo de método POST que chama um componente CORRETO
    @PostMapping("/")
    public String postMethodName() {

        return this.meuComponente.chamarComponente();
    }    
}