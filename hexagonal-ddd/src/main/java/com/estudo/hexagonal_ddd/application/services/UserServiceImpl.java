package com.estudo.hexagonal_ddd.application.services;

import java.util.UUID;
import com.estudo.hexagonal_ddd.domain.entities.User;
import com.estudo.hexagonal_ddd.domain.entities.value_objects.Role;
import com.estudo.hexagonal_ddd.domain.entities.value_objects.Email;
import com.estudo.hexagonal_ddd.application.ports.outbound.TokenServicePort;
import com.estudo.hexagonal_ddd.application.ports.outbound.HashEncoder;
import com.estudo.hexagonal_ddd.application.ports.inbound.UserServicePort;
import com.estudo.hexagonal_ddd.application.dtos.RegisterUserDTO;
import com.estudo.hexagonal_ddd.domain.repositories.UserRepository;
import com.estudo.hexagonal_ddd.domain.services.AuthorizationService;
import com.estudo.hexagonal_ddd.application.exceptions.*;

import org.springframework.stereotype.Service;




@Service

public class UserServiceImpl implements UserServicePort {

    private final UserRepository userRepository;

    private final HashEncoder hashEncoder;

    private final TokenServicePort tokenService;

    private final AuthorizationService authorizationService;


    public UserServiceImpl(
            UserRepository userRepository,
            HashEncoder hashEncoder,
            TokenServicePort tokenService,
            AuthorizationService authorizationService
    ) {

        this.userRepository = userRepository;
        this.hashEncoder = hashEncoder;
        this.tokenService = tokenService;
        this.authorizationService = authorizationService;
    }


    @Override

    public void register(

            RegisterUserDTO dto

    ) {


        boolean isValidEmail = Email.isValid(dto.email);

        if (!isValidEmail) {

            throw new InvalidInputException("email invalid");

        }


        boolean isValidRole = Role.isValid(dto.role);

        if (!isValidRole) {

            throw new InvalidInputException("role invalid");

        }


        User user = new User(dto.name, Email.of(dto.email), dto.password, Role.valueOf(dto.role));


        boolean userAlreadyExists = this.userRepository.findByEmail(user.getEmail().getValue()).isPresent();


        if (userAlreadyExists) {

            throw new UserAlreadyExistsException();

        }


        String passwordEncoder = this.hashEncoder.encode(user.getPassword());

        user.setPassword(passwordEncoder);


        this.userRepository.save(user);

    }


    @Override

    public String authenticate(String email, String password) {

        User userFind = this.userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);


        boolean isPasswordInvalid = !this.hashEncoder.valid(password, userFind.getPassword());


        if (isPasswordInvalid) {

            throw new WrongCredentialException("email/password invalid");

        }


        return this.tokenService.generate(userFind.getId().toString(), userFind.getRole());

    }


    @Override
    public void delete(UUID adminId, UUID userId) {

        User user = this.userRepository.findById(adminId).orElseThrow(WithoutAuthorizationException::new);

        if (user.isAdmin())

            throw new WithoutAuthorizationException();


        this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);


        this.userRepository.delete(userId);

    }


    @Override

    public void update(UUID adminId, User user) {
        User userFind = this.userRepository.findById(adminId).orElseThrow(UnauthorizedException::new);

        if (!this.authorizationService.canManegeUsers(userFind))
            throw new WithoutAuthorizationException();

        this.userRepository.findById(user.getId()).orElseThrow(UserNotFoundException::new);

        String passwordHash = this.hashEncoder.encode(user.getPassword());
        user.setPassword(passwordHash);


        this.userRepository.save(user);
    }


    @Override
    public User getProfile(UUID userId) {
        return this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }


    @Override
    public User getAsAdmin(UUID adminId, UUID userId) {
        User user = this.userRepository.findById(adminId).orElseThrow(UnauthorizedException::new);

        if (!this.authorizationService.canManegeUsers(user)) {
            throw new WithoutAuthorizationException();
        }

        return getProfile(userId);
    }


}