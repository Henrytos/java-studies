package com.estudo.hexagonal_ddd.application.services;

import com.estudo.hexagonal_ddd.application.dtos.RegisterUserDTO;
import com.estudo.hexagonal_ddd.application.exceptions.InvalidInputException;
import com.estudo.hexagonal_ddd.application.exceptions.UserAlreadyExistsException;
import com.estudo.hexagonal_ddd.application.exceptions.UserNotFoundException;
import com.estudo.hexagonal_ddd.application.exceptions.WrongCredentialException;

import com.estudo.hexagonal_ddd.application.ports.inbound.TokenService;
import com.estudo.hexagonal_ddd.application.ports.inbound.UserServicePort;
import com.estudo.hexagonal_ddd.application.ports.outbound.HashEncoder;

import com.estudo.hexagonal_ddd.domain.entities.value_objects.Email;
import com.estudo.hexagonal_ddd.domain.entities.value_objects.Role;
import com.estudo.hexagonal_ddd.domain.repositories.UserRepository;

import com.estudo.hexagonal_ddd.domain.entities.User;

public class UserServiceImpl implements UserServicePort {
    private final UserRepository userRepository;
//    private final HashEncoder hashEncoder;
//    private final TokenService tokenService;


    public UserServiceImpl(
            UserRepository userRepository
//            HashEncoder hashEncoder,
//            TokenService tokenService
    ) {
        this.userRepository = userRepository;
//        this.hashEncoder = hashEncoder;
//        this.tokenService = tokenService;

    }


    @Override
    public void register(
            RegisterUserDTO dto
    ) {
        System.out.println("register user service");

        boolean isValidEmail = Email.isValid(dto.email);
        if (!isValidEmail) {
            System.out.println("register user throw new InvalidInputException(\"email invalid\")");
            throw new InvalidInputException("email invalid");
        }

        boolean isValidRole = Role.isValid(dto.role);
        if (!isValidRole) {
            System.out.println("register user throw new InvalidInputException(\"role invalid\")");
            throw new InvalidInputException("role invalid");
        }

        System.out.println("register user service2");

        User user = new User(dto.name, Email.of(dto.email), dto.password, Role.valueOf(dto.role));

        boolean userAlreadyExists = this.userRepository.findByEmail(user.getEmail().getValue()).isPresent();

        if (userAlreadyExists) {
            System.out.println("register user service userAlreadyExists");

            throw new UserAlreadyExistsException();
        }


//        String passwordEncoder = this.hashEncoder.encode(user.getPassword());
//        user.setPassword(passwordEncoder);

        System.out.println("register user service save");

        System.out.println(user);
        this.userRepository.save(user);
    }
    @Override
    public String authenticate(String email, String password) {
    return "toke";
    }
//    @Override
//    public String authenticate(String email, String password) {
//        User userFind = this.userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
//
//        boolean isPasswordInvalid = !this.hashEncoder.decode(userFind.getPassword(), password);
//
//        if (isPasswordInvalid) {
//            throw new WrongCredentialException("email/password invalid");
//        }
//
//        return this.tokenService.generate(userFind.getId().toString());
//    }

}
