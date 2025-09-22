package com.stefanini.latam.spring_security_studies.model;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

@Entity(name = "roles")
@Data
public class RoleEntity implements GrantedAuthority {

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private UUID id;

    @Enumerated(EnumType.STRING)
    private RoleUser roleName;

    @Override
    public String getAuthority() {
        return this.roleName.toString();
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "id=" + id +
                ", roleName=" + roleName +
                '}';
    }
}
