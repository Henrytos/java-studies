package com.estudo.hexagonal_ddd.infrastructure.adapters.outbound.persistence.database.repository;

import com.estudo.hexagonal_ddd.domain.entities.User;
import com.estudo.hexagonal_ddd.domain.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JPAUserRepositoryImpl implements UserRepository {

    private List<User> users=  new ArrayList<>();

    @Override
    public void save(User user) {
        System.out.println("save");
        this.users.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        System.out.println("findByEmail");
        System.out.println("find by email");

        return this.users
                .stream()
                .filter(user-> user.getEmail().getValue().equals(email))
                .findFirst();
    }
}
