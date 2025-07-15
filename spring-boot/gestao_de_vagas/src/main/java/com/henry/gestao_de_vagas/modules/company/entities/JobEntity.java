package com.henry.gestao_de_vagas.modules.company.entities;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;
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

@Data
@Entity(name = "job")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private CompanyEntity companyEntity;

    @Column(name = "company_id", nullable = false)
    private UUID companyId;

    @Schema(example = "Vale refeição, Vale transporte, plano de saúde e gym pass", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "Benefícios da vaga")
    private String benefits;

    @Schema(example = "Desenvolvedor back end java spring boot junior", requiredMode = Schema.RequiredMode.REQUIRED, description = "Descrição da vaga")
    private String description;

    @Schema(example = "JUNIOR", requiredMode = Schema.RequiredMode.REQUIRED, description = "Nível da vaga")
    private String level;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDate createdAt;
}
