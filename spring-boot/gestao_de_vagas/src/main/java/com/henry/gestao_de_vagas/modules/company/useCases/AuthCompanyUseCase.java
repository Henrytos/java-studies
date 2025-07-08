package com.henry.gestao_de_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.henry.gestao_de_vagas.modules.company.dto.AuthCompanyDTO;
import com.henry.gestao_de_vagas.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {
    
    @Autowired
    private CompanyRepository  companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void execute(AuthCompanyDTO authCompanyDTO){

        // 1 verificar se o usuário existe
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(()->{
            throw new UsernameNotFoundException("Usuário não encontrado");
        });

        // 2 verificar se a senha está correta
        var passwordMatch = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if(!passwordMatch){
            throw new UsernameNotFoundException("Usuário ou senha inválidos");
        }

        // 3 retornar token JWT ou algo do tipo


    }
}
