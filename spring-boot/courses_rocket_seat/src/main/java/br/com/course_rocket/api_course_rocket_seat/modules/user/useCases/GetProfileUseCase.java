package br.com.course_rocket.api_course_rocket_seat.modules.user.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.course_rocket.api_course_rocket_seat.modules.user.UserEntity;
import br.com.course_rocket.api_course_rocket_seat.modules.user.repositories.UserRepository;

@Service
public class GetProfileUseCase {

    @Autowired
    private UserRepository userRepository;

    public UserEntity execute(UUID userId) throws UsernameNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with id: %s", userId)));
    }

}
