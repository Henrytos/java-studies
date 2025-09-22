package com.stefanini.latam.spring_security_studies.service;

import com.stefanini.latam.spring_security_studies.model.UserEntity;
import com.stefanini.latam.spring_security_studies.repository.JpaUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    final JpaUserRepository userRepository;

    public UserDetailsServiceImpl(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);

        UserEntity userModel = userRepository.findByUsername(username)
                .orElseThrow(() ->{
                    System.out.println("Não encontamos usuarios com este nome");
                    throw new UsernameNotFoundException("Não encontamos usuarios com este nome");
                });

        System.out.println(userModel);

        return new User(userModel.getUsername(), userModel.getPassword(), true, true, true,true, userModel.getAuthorities());
    }
}

