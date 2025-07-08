package com.henry.gestao_de_vagas.modules.company.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.henry.gestao_de_vagas.modules.company.entities.JobEntity;

public interface JobEntityRepository extends JpaRepository<JobEntity, UUID> {

}
