package com.henry.gestao_de_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henry.gestao_de_vagas.modules.company.entities.JobEntity;
import com.henry.gestao_de_vagas.modules.company.repositories.JobEntityRepository;

@Service
public class CreateJobUseCase {
    
    @Autowired
    private JobEntityRepository jobEntityRepository;

    public JobEntity execute(JobEntity entity){
        return this.jobEntityRepository.save(entity);
    }

}
