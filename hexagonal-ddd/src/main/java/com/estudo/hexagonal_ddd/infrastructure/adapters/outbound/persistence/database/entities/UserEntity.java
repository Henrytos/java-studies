package com.estudo.hexagonal_ddd.infrastructure.adapters.outbound.persistence.database.entities;

import com.estudo.hexagonal_ddd.domain.entities.value_objects.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private  UUID id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private Role role;
}
