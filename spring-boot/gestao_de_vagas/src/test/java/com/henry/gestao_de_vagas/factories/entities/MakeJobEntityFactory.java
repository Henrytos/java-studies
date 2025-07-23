package com.henry.gestao_de_vagas.factories.entities;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.henry.gestao_de_vagas.modules.company.entities.JobEntity;
import com.henry.gestao_de_vagas.utils.UtilTest;

@Service
public class MakeJobEntityFactory {

        public JobEntity makeFactoryEntity() {
                var faker = UtilTest.faker();

                String[] levels = {
                                "TRAINER",
                                "ESTAGIARIO",
                                "JUNIOR",
                                "PLENO",
                                "SENIOR",
                                "TECH LEAD"
                };
                var random = Math.floor(Math.random() * levels.length);
                var level = levels[(int) random];

                var jobEntity = JobEntity
                                .builder()
                                .benefits(faker.lorem().sentence(1))
                                .description(faker.lorem().paragraph())
                                .level(level)
                                .build();
                return jobEntity;
        }

        public JobEntity makeFactoryEntity(UUID companyId) {
                var faker = UtilTest.faker();

                String[] levels = {
                                "TRAINER",
                                "ESTAGIARIO",
                                "JUNIOR",
                                "PLENO",
                                "SENIOR",
                                "TECH LEAD"
                };
                var random = Math.floor(Math.random() * levels.length);
                var level = levels[(int) random];

                var jobEntity = JobEntity
                                .builder()
                                .companyId(companyId)
                                .benefits(faker.lorem().sentence(10))
                                .description(faker.lorem().paragraph())
                                .level(level)
                                .build();
                return jobEntity;
        }
}
