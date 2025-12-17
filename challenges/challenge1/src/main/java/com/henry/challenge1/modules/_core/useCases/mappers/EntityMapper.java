package com.henry.challenge1.modules._core.useCases.mappers;

public interface EntityMapper<Domain, Infra> {

    Domain toDomain(Infra infra);

    Infra toInfra(Domain domain);

}
