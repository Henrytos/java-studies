package com.log.dev.api.utils.factories;

import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;
import com.log.dev.api.modules.user.UserEntity;

@Service
public class MakeUserEntityFactory {
    private final Faker faker = new Faker();

    public UserEntity make() {
        UserEntity user = UserEntity
                .builder()
                .username(faker.name().username())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .build();

        return user;
    }
}
