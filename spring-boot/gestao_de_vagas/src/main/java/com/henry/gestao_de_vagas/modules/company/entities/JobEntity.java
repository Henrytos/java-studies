package com.henry.gestao_de_vagas.modules.company.entities;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity(name = "job")
public class JobEntity {
 
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @ManyToOne()
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private CompanyEntity companyEntity;

    @NotBlank(message = "Job title cannot be blank")
    @Column(name = "company_id",nullable = false)
    private UUID companyId;

    private String benefits;
    private String description;
    private String level;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDate createdAt;
}
