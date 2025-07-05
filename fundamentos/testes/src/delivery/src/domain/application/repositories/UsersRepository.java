package testes.src.delivery.src.domain.application.repositories;


import testes.src.delivery.src.domain.enterprise.entities.User;

public interface UsersRepository {
    public User findById(String id);
    public User findByEmail(String id);
}

