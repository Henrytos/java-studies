package com.henry.challenge1.modules.auth.useCases;

import com.henry.challenge1.modules.auth.repositories.JpaUserRepository;
import com.henry.challenge1.modules._core.exceptions.WrongCredentialsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsUseCase implements UserDetailsService {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.jpaUserRepository.findByUsername(username).orElseThrow(WrongCredentialsException::new);
    }
}
