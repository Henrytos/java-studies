package com.log.dev.api.modules.user.useCases;

import java.util.UUID;
import org.springframework.stereotype.Service;

import com.log.dev.api.exceptions.UserNotFoundException;
import com.log.dev.api.modules.user.entities.UserEntity;
import com.log.dev.api.modules.user.repositories.UserRepository;

@Service
public class GetProfileUseCase {

    final private UserRepository userRepository;

    public GetProfileUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity execute(UUID userId) throws UserNotFoundException {
        return this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

}
