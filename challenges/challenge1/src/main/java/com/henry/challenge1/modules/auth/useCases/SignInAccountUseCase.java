package com.henry.challenge1.modules.auth.useCases;

import com.henry.challenge1.modules.auth.UserEntity;
import com.henry.challenge1.modules.auth.dtos.SignInAccountRequestDTO;
import com.henry.challenge1.modules.auth.dtos.SignInAccountResponseDTO;
import com.henry.challenge1.modules.auth.repositories.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInAccountUseCase {

    private final TokenUseCase tokenUseCase;

    private final JpaUserRepository jpaUserRepository;

    private final AuthenticationManager authenticationManager;


    public SignInAccountResponseDTO execute(SignInAccountRequestDTO requestDTO) {
        UserEntity user = jpaUserRepository.findByEmail(requestDTO.email())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        requestDTO.password()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);



        return tokenUseCase.generate(user);
    }

}
