package com.henry.challenge1.modules.auth.mappers;

import com.henry.challenge1.modules.auth.UserEntity;
import com.henry.challenge1.modules.auth.dtos.CreateAccountRequestDTO;
import com.henry.challenge1.modules.auth.dtos.CreateAccountResponseDTO;
import com.henry.challenge1.modules.videos.useCases.mappers.EntityMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements EntityMapper<UserEntity, CreateAccountRequestDTO> {


    @Override
    public UserEntity toDomain(CreateAccountRequestDTO createAccountRequestDTO) {
        return UserEntity.builder()
                .username(createAccountRequestDTO.username())
                .email(createAccountRequestDTO.email())
                .password(createAccountRequestDTO.password())
                .build();
    }

    @Override
    public CreateAccountRequestDTO toInfra(UserEntity userEntity) {
        return new CreateAccountRequestDTO(
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getPassword()
        );
    }

    public CreateAccountResponseDTO toInfraResponse(UserEntity userEntity){
        return new CreateAccountResponseDTO(userEntity.getId(), userEntity.getUsername(), userEntity.getPassword());
    }
}
