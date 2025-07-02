package domain.application.repositories;

import domain.enterprise.entities.User;

public interface UsersRepository {
    public User findById(String id);
    public User findByEmail(String id);
}

