package com.henry.gestao_de_vagas.factories.entities;

import org.springframework.stereotype.Service;

import com.henry.gestao_de_vagas.modules.company.entities.JobEntity;

@Service
public class MakeJobEntityFactory {

    public JobEntity makeFactoryEntity() {
        var jobEntity = JobEntity.builder().build();
        return jobEntity;
    }

}
