package com.log.dev.api.modules.user.useCases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.Instant;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import com.log.dev.api.dtos.AuthUserRequestDTO;
import com.log.dev.api.dtos.AuthUserResponseDTO;
import com.log.dev.api.exceptions.UserNotFoundException;
import com.log.dev.api.exceptions.WrongCredentialsException;
import com.log.dev.api.modules.user.UserEntity;
import com.log.dev.api.modules.user.repositories.UserRepository;
import com.log.dev.api.providers.JWTProvider;
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

        assertNotNull(response.token());
        assertNotNull(response.expireAt());
        assertTrue(JWTProvider.validateTokenStatic(response.token(), secretKey));

        Instant differenceInInstant = Instant.ofEpochMilli(response.expireAt())
                .minusMillis(Instant.now().toEpochMilli());
        Duration differenceInMinutes = Duration.ofMillis(differenceInInstant.toEpochMilli()).abs();

    }

    @Test
    @DisplayName("should_not_authenticate_user_if_username_not_found")
    public void should_not_authenticate_user_if_username_not_found() {
        UserEntity user = makeUserEntityFactory.make();
        user.setId(UUID.randomUUID());

        when(this.userRepository.findByUsername(user.getUsername()))
                .thenReturn(Optional.ofNullable(null));

        AuthUserRequestDTO dto = AuthUserRequestDTO.builder().username(user.getUsername()).password(user.getPassword())
                .build();

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            authUserUseCase.execute(dto);
        });

        assertEquals(exception.getMessage(), "User not found");
        assertEquals(exception.getStatusCode(), 404);
    }

    @Test
    @DisplayName("should_not_authenticate_user_if_wrong_credentials")
    public void should_not_authenticate_user_if_wrong_credentials() {
        UserEntity user = makeUserEntityFactory.make();

        user.setId(UUID.randomUUID());

        when(this.userRepository.findByUsername(user.getUsername()))
                .thenReturn(Optional.of(user));

        AuthUserRequestDTO dto = AuthUserRequestDTO.builder().username(user.getUsername())
                .password(user.getPassword())
                .build();

        when(this.passwordEncoder.matches(dto.getPassword(), user.getPassword())).thenReturn(false);

        WrongCredentialsException exception = assertThrows(WrongCredentialsException.class, () -> {
            authUserUseCase.execute(dto);
        });

        assertEquals(exception.getMessage(), "Wrong credentials");
        assertEquals(exception.getStatusCode(), 401);
    }
}
