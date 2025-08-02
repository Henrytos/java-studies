package com.log.dev.api.modules.user.useCases;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import com.log.dev.api.dtos.AuthUserRequestDTO;
import com.log.dev.api.dtos.AuthUserResponseDTO;
import com.log.dev.api.modules.user.UserEntity;
import com.log.dev.api.modules.user.repositories.UserRepository;
import com.log.dev.api.utils.factories.MakeUserEntityFactory;

@ExtendWith(MockitoExtension.class)
public class AuthUserUseCaseTest {

    @InjectMocks
    private AuthUserUseCase authUserUseCase;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private String secretKey = "312312";

    private MakeUserEntityFactory makeUserEntityFactory;

    @BeforeEach
    public void setup() {
        makeUserEntityFactory = new MakeUserEntityFactory();
        ReflectionTestUtils.setField(authUserUseCase, "secretKey", secretKey);
    }

    @Test
    @DisplayName("should_authenticate_user")
    public void should_authenticate_user() {
        UserEntity user = makeUserEntityFactory.make();
        user.setId(UUID.randomUUID());

        when(this.userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        when(this.passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

        AuthUserRequestDTO dto = AuthUserRequestDTO.builder().username(user.getUsername()).password(user.getPassword())
                .build();

        AuthUserResponseDTO response = authUserUseCase.execute(dto);

    }

}
