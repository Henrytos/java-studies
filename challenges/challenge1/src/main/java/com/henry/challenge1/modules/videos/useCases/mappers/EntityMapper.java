package com.henry.challenge1.modules.videos.useCases.mappers;

public interface EntityMapper<Domain, Infra> {

    Domain toDomain(Infra infra);

    Infra toInfra(Domain domain);

}
