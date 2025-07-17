package com.henry.gestao_de_vagas.factories.entities;

import org.springframework.stereotype.Service;

import com.henry.gestao_de_vagas.modules.candidate.CandidateEntity;
import com.henry.gestao_de_vagas.utils.UtilTest;

@Service
public class MakeCandidateEntityFactory {

    public CandidateEntity makeFactorEntity() {

        var faker = UtilTest.faker();

        var candidate = CandidateEntity.builder().name(
                faker.name().name()).username(faker.name().username()).email(faker.internet().emailAddress())
                .password(faker.internet().password()).description(faker.lorem().characters()).build();

        return candidate;

    }

    public static CandidateEntity staticMakeFactorEntity() {

        var faker = UtilTest.faker();

        var candidate = CandidateEntity.builder().name(
                faker.name().name()).username(faker.name().username()).email(faker.internet().emailAddress())
                .password(faker.internet().password()).description(faker.lorem().characters()).build();

        return candidate;

    }

}
