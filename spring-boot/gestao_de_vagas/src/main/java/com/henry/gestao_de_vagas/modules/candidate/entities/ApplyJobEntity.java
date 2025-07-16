package com.henry.gestao_de_vagas.modules.candidate.entities;

import java.util.UUID;

import com.henry.gestao_de_vagas.modules.candidate.CandidateEntity;
import com.henry.gestao_de_vagas.modules.company.entities.JobEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "apply_jobs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyJobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "candidate_id", insertable = false, updatable = false)
    private JobEntity jobEntity;

    @ManyToOne
    @JoinColumn(name = "job_id", insertable = false, updatable = false)
    private CandidateEntity candidateEntity;

    @Column(name = "job_id")
    private UUID jobId;

    @Column(name = "candidate_id")
    private UUID candidateId;

}
