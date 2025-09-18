package com.estudo.hexagonal_ddd.infrastructure.adapters.outbound.persistence.database.mapper;

import com.estudo.hexagonal_ddd.domain.entities.User;
import com.estudo.hexagonal_ddd.domain.entities.value_objects.Email;
import com.estudo.hexagonal_ddd.infrastructure.adapters.outbound.persistence.database.entities.UserEntity;

public class JpaUserMapper {

    public static User toDomain(UserEntity userEntity) {
        return new User(
                userEntity.getName(),
                Email.of(userEntity.getEmail()),
                userEntity.getPassword(),
                userEntity.getRole()
        );
    }

    public static UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getName(),
                user.getEmail().toString(),
                user.getPassword(),
                user.getRole()
        );
    }
}
