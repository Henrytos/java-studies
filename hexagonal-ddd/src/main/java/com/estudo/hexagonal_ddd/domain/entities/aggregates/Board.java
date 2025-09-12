package com.estudo.hexagonal_ddd.domain.entities.aggregates;

import com.estudo.hexagonal_ddd.domain.entities.User;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Board {
    final private UUID id;
    private String title;
    private String description;
    private User owner;
    private List<User> members;

    public Board() {
        this.id = UUID.randomUUID();
    }

    public Board(String title, String description, User owner, List<User> members) {
        this();
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.members = members;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }


    @Override
    public String toString() {
        return "BoardAggregate{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", owner=" + owner +
                ", members=" + members +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Objects.equals(id, board.id) && Objects.equals(title, board.title) && Objects.equals(description, board.description) && Objects.equals(owner, board.owner) && Objects.equals(members, board.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, owner, members);
    }
}
