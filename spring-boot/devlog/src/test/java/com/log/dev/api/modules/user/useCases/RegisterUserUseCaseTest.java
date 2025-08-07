
package com.log.dev.api.modules.user.useCases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.log.dev.api.dtos.RegisterUserRequestDTO;
import com.log.dev.api.exceptions.WrongCredentialsException;
import com.log.dev.api.modules.user.entities.UserEntity;
import com.log.dev.api.modules.user.repositories.UserRepository;
import com.log.dev.api.utils.factories.entities.MakeUserEntityFactory;

@ExtendWith(MockitoExtension.class)
public class RegisterUserUseCaseTest {

    @InjectMocks
    private RegisterUserUseCase registerUserUseCase;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private MakeUserEntityFactory makeUserEntityFactory;

    @BeforeEach
    public void setup() {
        makeUserEntityFactory = new MakeUserEntityFactory();
    }

    @Nested
    @DisplayName("Success Tests")
    public class SuccessTests {

        @Test
        @DisplayName("should be able a create new user")
        public void should_be_able_a_create_new_user() {
            UserEntity user = makeUserEntityFactory.make();

            RegisterUserRequestDTO dto = RegisterUserRequestDTO
                    .builder()
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .build();

            when(userRepository.findByUsernameOrEmail(dto.getUsername(), dto.getEmail())).thenReturn(Optional.empty());
            when(passwordEncoder.encode(dto.getPassword())).thenReturn(user.getPassword().concat("HASH"));

            user.setPassword(user.getPassword().concat("HASH"));

            UserEntity userSaved = UserEntity.builder().id(UUID.randomUUID()).username(user.getUsername())
                    .email(user.getEmail()).password(user.getPassword()).build();

            when(userRepository.save(user)).thenReturn(userSaved);

            UserEntity response = registerUserUseCase.execute(dto);

            assertNotNull(response);
            assertNotNull(response.getId());
            assertEquals(response.getUsername(), dto.getUsername());
            assertEquals(response.getEmail(), dto.getEmail());
            assertNotEquals(response.getPassword(), dto.getPassword());

        }
    }

    @Nested
    @DisplayName("Failed Tests")
    public class FailedTests {

        private UserEntity user;

        private RegisterUserRequestDTO dto;

        @BeforeEach
        public void setup() {
            this.user = makeUserEntityFactory.make();

            dto = RegisterUserRequestDTO
                    .builder()
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .build();
        }

        @Test
        @DisplayName("should not be able a create new user if username exists")
        public void should_not_be_able_a_create_new_user_if_username_exists() {
            when(userRepository.findByUsernameOrEmail(eq(dto.getUsername()), anyString()))
                    .thenReturn(Optional.of(user));

            WrongCredentialsException exception = assertThrows(WrongCredentialsException.class, () -> {
                registerUserUseCase.execute(dto);
            });

            assertEquals(exception.getMessage(), "Wrong credentials");
            assertEquals(exception.getStatusCode(), 401);
        }

        @Test
        @DisplayName("should not be able a create new user if email exists")
        public void should_not_be_able_a_create_new_user_if_email_exists() {
            when(userRepository.findByUsernameOrEmail(anyString(), eq(dto.getEmail()))).thenReturn(Optional.of(user));

            WrongCredentialsException exception = assertThrows(WrongCredentialsException.class, () -> {
                registerUserUseCase.execute(dto);
            });

            assertEquals(exception.getMessage(), "Wrong credentials");
            assertEquals(exception.getStatusCode(), 401);
        }
    }

}
