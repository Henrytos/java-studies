package com.estudo.hexagonal_ddd.infrastructure.adapters.outbound.persistence.database.mapper;

import com.estudo.hexagonal_ddd.domain.entities.User;
import com.estudo.hexagonal_ddd.domain.entities.value_objects.Email;
import com.estudo.hexagonal_ddd.domain.entities.value_objects.Role;
import com.estudo.hexagonal_ddd.infrastructure.adapters.outbound.persistence.database.entities.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JpaUserMapper {

    @Autowired
    private ModelMapper mapper;

    public  User toDomain(UserEntity userEntity) {
        this.mapper.typeMap(UserEntity.class, User.class).addMappings(
                mapper ->{
                 mapper.map(UserEntity::getName, User::setName);
                 mapper.map(src-> Email.of(src.getEmail()) ,User::setEmail);
                 mapper.map(UserEntity::getPassword, User::setPassword);
                 mapper.map(src-> Role.valueOf(src.getRole()),User::setRole);
                }
        );

        return this.mapper.map(userEntity, User.class);
//        return new User(
//                userEntity.getName(),
//                Email.of(userEntity.getEmail()),
//                userEntity.getPassword(),
//                Role.valueOf(userEntity.getRole())
//        );
    }

    public UserEntity toEntity(User user) {
        this.mapper.typeMap(User.class, UserEntity.class)
                .addMappings(mapper->{
                    mapper.map(User::getId, UserEntity::setId);
                    mapper.map(User::getName, UserEntity::setName);
                    mapper.map(User::getPassword, UserEntity::setPassword);
                    mapper.map(src->src.getEmail().getValue(), UserEntity::setEmail);
                    mapper.map(src->src.getRole().toString(), UserEntity::setRole);

                });
        return this.mapper.map(user, UserEntity.class);
//        return new UserEntity(
//                user.getId(),
//                user.getName(),
//                user.getEmail().getValue(),
//                user.getPassword(),
//                user.getRole().toString()
//        );
    }
}
