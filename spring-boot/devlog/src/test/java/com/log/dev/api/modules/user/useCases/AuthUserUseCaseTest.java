package com.log.dev.api.modules.user.useCases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.Instant;
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
import org.springframework.test.util.ReflectionTestUtils;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
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

    @Nested
    @DisplayName("Success Cases")
    class SuccessCases {

        private UserEntity user;

        @BeforeEach
        public void setup() {
            this.user = makeUserEntityFactory.make();
            user.setId(UUID.randomUUID());
        }

        @Test
        @DisplayName("should_authenticate_user")
        public void should_authenticate_user() {
            when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
            when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

            AuthUserRequestDTO dto = AuthUserRequestDTO.builder().username(user.getUsername())
                    .password(user.getPassword())
                    .build();

            AuthUserResponseDTO response = authUserUseCase.execute(dto);

            // Validations response use case
            assertNotNull(response.token());
            assertNotNull(response.expireAt());
            assertTrue(JWTProvider.validateTokenStatic(response.token(), secretKey));

            // Validations Token
            DecodedJWT decodedJWT = JWTProvider.getDecodedTokenStatic(response.token(), secretKey);

            assertEquals(decodedJWT.getSubject(), user.getId().toString());

            Long expect = decodedJWT.getExpiresAt().toInstant().toEpochMilli();
            Long actual = response.expireAt();
            Long differenceInMilliseconds = Duration.between(Instant.ofEpochMilli(expect), Instant.ofEpochMilli(actual))
                    .toMillis();
            assertTrue(differenceInMilliseconds < 1_000L); // tolerance 1 second

            Claim roles = decodedJWT.getClaim("roles");
            assertFalse(roles.isNull());

            String[] rolesInArray = roles.asArray(String.class);
            int QUANTITY_ROLES_TO_USER = 1;
            assertEquals(rolesInArray.length, QUANTITY_ROLES_TO_USER);
            assertEquals(rolesInArray[0], "USER");

            // Validations duration token

            Duration differenceInMinutes = Duration.between(Instant.now(), Instant.ofEpochMilli(response.expireAt()));

            Duration MIN_DURATION_TOKEN_IN_MINUTES = Duration.ofMinutes(9);
            Duration MAX_DURATION_TOKEN_IN_MINUTES = Duration.ofMinutes(10);

            assertEquals(differenceInMinutes.compareTo(MIN_DURATION_TOKEN_IN_MINUTES), 1);
            assertEquals(differenceInMinutes.compareTo(MAX_DURATION_TOKEN_IN_MINUTES), -1);

        }

    }

    @Nested
    @DisplayName("Failure Cases")
    public class FailureCases {

        private UserEntity user;

        @BeforeEach
        public void setup() {
            this.user = makeUserEntityFactory.make();
            user.setId(UUID.randomUUID());
        }

        @Test
        @DisplayName("should_not_authenticate_user_if_username_not_found")
        public void should_not_authenticate_user_if_username_not_found() {
            when(userRepository.findByUsername(user.getUsername()))
                    .thenReturn(Optional.ofNullable(null));

            AuthUserRequestDTO dto = AuthUserRequestDTO.builder().username(user.getUsername())
                    .password(user.getPassword())
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
            when(userRepository.findByUsername(user.getUsername()))
                    .thenReturn(Optional.of(user));

            AuthUserRequestDTO dto = AuthUserRequestDTO.builder().username(user.getUsername())
                    .password(user.getPassword())
                    .build();

            when(passwordEncoder.matches(dto.getPassword(), user.getPassword())).thenReturn(false);

            WrongCredentialsException exception = assertThrows(WrongCredentialsException.class, () -> {
                authUserUseCase.execute(dto);
            });

            assertEquals(exception.getMessage(), "Wrong credentials");
            assertEquals(exception.getStatusCode(), 401);
        }
    }

}
