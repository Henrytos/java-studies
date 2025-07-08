package com.henry.gestao_de_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henry.gestao_de_vagas.exceptions.UserAlreadyExists;
import com.henry.gestao_de_vagas.modules.company.entities.CompanyEntity;
import com.henry.gestao_de_vagas.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {
    
    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity entity)throws UserAlreadyExists{
        
        this.companyRepository
            .findByUsernameOrEmail(entity.getUsername(), entity.getEmail())
            .ifPresent((user)->{
                 throw new UserAlreadyExists();
            });
        
        return this.companyRepository.save(entity);
    }

}
