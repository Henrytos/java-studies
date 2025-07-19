package com.henry.gestao_de_vagas.factories.entities;

import org.springframework.stereotype.Service;

import com.henry.gestao_de_vagas.modules.company.entities.CompanyEntity;
import com.henry.gestao_de_vagas.utils.UtilTest;

@Service
public class MakeCompanyEntityFactory {

    public CompanyEntity makeFactorEntity() {
        var faker = UtilTest.faker();
        var company = CompanyEntity.builder()
                .name(faker.name().name())
                .username(faker.name().username())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .description(faker.lorem().characters())
                .website(faker.internet().url())
                .build();

        return company;
    }

}
