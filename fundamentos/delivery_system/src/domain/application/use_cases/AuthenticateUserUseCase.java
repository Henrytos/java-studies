package testes.src.delivery.src.domain.application.use_cases;


import testes.src.delivery.src.domain.application.encryption.Encryptor;
import testes.src.delivery.src.domain.application.repositories.*;
import testes.src.delivery.src.domain.application.use_cases.errors.UserNotFoundException;
import testes.src.delivery.src.domain.application.use_cases.errors.WrongCredentialsException;
import testes.src.delivery.src.domain.enterprise.entities.User;

import java.util.Objects;


class AuthenticateUserUseCaseRequest {
    protected String email;
    protected String password;

    AuthenticateUserUseCaseRequest(String email, String password){
        this.email = email;
        this.password = password;
    }
}

class AuthenticateUserUseCaseResponse {
    protected String token;

    AuthenticateUserUseCaseResponse(String token){
        this.token = token;
    }
}

public class AuthenticateUserUseCase {

    private final UsersRepository usersRepository;
    private final Encryptor encryptor;

    public AuthenticateUserUseCase(
            UsersRepository usersRepository,
            Encryptor encryptor
    ){
        this.usersRepository = usersRepository;
        this.encryptor = encryptor;
    }

    public AuthenticateUserUseCaseResponse execute(AuthenticateUserUseCaseRequest authenticateUserUseCaseRequest) throws UserNotFoundException, WrongCredentialsException {
        User user = this.usersRepository.findByEmail(authenticateUserUseCaseRequest.email);
        if(!Objects.equals(user.getEmail(), "")){
            throw new UserNotFoundException();
        }

        Boolean passwordMatch = this.encryptor.compare(user.getPassword(), authenticateUserUseCaseRequest.password);
        if(!passwordMatch){
            throw new WrongCredentialsException();
        }

        String token = this.encryptor.encrypt(user.getEmail());
        AuthenticateUserUseCaseResponse response = new AuthenticateUserUseCaseResponse(token);

        return response;
    }
}
