package com.primeiro_projeto.ioc_di;

import org.springframework.stereotype.Component;

// MANEIRA ERRADA DE CRIAR UM COMPONENTE
// public class MeuComponente {
    
//     public String chamarComponenete(){
//         return "Componente chamado com sucesso!";
//     }
// }

// MANEIRA CORRETA DE CRIAR UM COMPONENTE
@Component
public class MeuComponente {
    
    public String chamarComponente(){
        return "Componente chamado com sucesso!";
    }
}
