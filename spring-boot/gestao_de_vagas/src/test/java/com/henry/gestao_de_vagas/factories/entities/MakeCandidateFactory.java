package com.henry.gestao_de_vagas.factories.entities;

import com.henry.gestao_de_vagas.modules.candidate.CandidateEntity;

public class MakeCandidateFactory {

    public static CandidateEntity makeFactorEntity() {
        return CandidateEntity.builder().build();
    }

}
