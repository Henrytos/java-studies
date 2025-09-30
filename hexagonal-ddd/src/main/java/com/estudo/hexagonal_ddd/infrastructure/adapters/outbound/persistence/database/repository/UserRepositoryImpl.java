package com.estudo.hexagonal_ddd.infrastructure.adapters.outbound.persistence.database.repository;

import com.estudo.hexagonal_ddd.domain.entities.User;
import com.estudo.hexagonal_ddd.domain.repositories.UserRepository;
import com.estudo.hexagonal_ddd.infrastructure.adapters.outbound.persistence.database.entities.UserEntity;
import com.estudo.hexagonal_ddd.infrastructure.adapters.outbound.persistence.database.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JpaUserRepository jpaUserRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void save(User user) {
        Optional<UserEntity> userFind = this.jpaUserRepository.findById(user.getId());

        if (userFind.isEmpty()) {
            user.setId(null);
        }

        this.jpaUserRepository.save(userMapper.toEntity(user));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return this.jpaUserRepository.findByEmail(email)
                .map(userMapper::toDomain);
    }
}
