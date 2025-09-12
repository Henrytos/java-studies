package com.estudo.hexagonal_ddd.domain.entities;

import com.estudo.hexagonal_ddd.domain.value_objects.Email;

import java.util.Objects;
import java.util.UUID;

public class User {
    private final UUID id;
    private String name;
    private Email email;
    private String password;
    private String role;

    public User() {
        this.id = UUID.randomUUID();
    }


    public User(String name, Email email, String password, String role) {
        this();
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, role);
    }
}
