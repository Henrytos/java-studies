package com.henry.challenge1.modules.auth;

import com.henry.challenge1.modules.auth.dtos.CreateAccountRequestDTO;
import com.henry.challenge1.modules.auth.dtos.CreateAccountResponseDTO;
import com.henry.challenge1.modules.auth.dtos.SignInAccountRequestDTO;
import com.henry.challenge1.modules.auth.dtos.SignInAccountResponseDTO;
import com.henry.challenge1.modules.auth.useCases.CreateAccountUseCase;
import com.henry.challenge1.modules.auth.useCases.SignInAccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CreateAccountUseCase createAccountUseCase;

    private final SignInAccountUseCase signInAccountUseCase;


    @PostMapping("/sign-up")
    public ResponseEntity<CreateAccountResponseDTO> singUp(
            @RequestBody CreateAccountRequestDTO body,
            UriComponentsBuilder uriComponentsBuilder
    ){
        CreateAccountResponseDTO response = this.createAccountUseCase.execute(body);

        URI uri = uriComponentsBuilder.buildAndExpand("/api/v1/auth/sing-in").toUri();

        return ResponseEntity.created(uri).body(response);
    }


    @PostMapping("/sign-in")
    public ResponseEntity<SignInAccountResponseDTO> signIn(
            @RequestBody SignInAccountRequestDTO body,
            UriComponentsBuilder uriComponentsBuilder
    ){

        SignInAccountResponseDTO responseDTO = this.signInAccountUseCase.execute(body);



        return ResponseEntity.ok(responseDTO);
    }

}
