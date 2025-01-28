package domain.delivery.entities;

import java.util.UUID;

public abstract  class User {
    private UUID id = UUID.randomUUID();
    private  String name;
    private String email;
    private  String password;

    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UUID getId() {
        return this.id;
    }
}
