package br.com.course_rocket.api_course_rocket_seat.modules.user.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.course_rocket.api_course_rocket_seat.modules.user.UserEntity;
import br.com.course_rocket.api_course_rocket_seat.modules.user.constants.UserRole;
import br.com.course_rocket.api_course_rocket_seat.modules.user.dto.CreateUserRequestDTO;
import br.com.course_rocket.api_course_rocket_seat.modules.user.excetions.UserDoesExistException;
import br.com.course_rocket.api_course_rocket_seat.modules.user.repositories.UserRepository;

@Service
public class CreateUserUseCase {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public void createUser(CreateUserRequestDTO dto) throws UserDoesExistException {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new UserDoesExistException(String.format("User with email %s already exists", dto
                    .getEmail()));
        }

        var passwordHash = this.passwordEncoder.encode(dto.getPassword());

        var user = UserEntity.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .passwordHash(passwordHash)
                .role(UserRole.valueOf(dto.getRole()))
                .build();

        userRepository.save(user);
    }

}
