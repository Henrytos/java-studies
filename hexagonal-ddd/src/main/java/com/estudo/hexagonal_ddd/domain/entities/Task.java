package com.estudo.hexagonal_ddd.domain.entities;

import com.estudo.hexagonal_ddd.domain.entities.value_objects.TaskStatus;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Task {
    final private UUID id;
    private  String title;
    private  String description;
    private TaskStatus status;
    private UUID boardId;
    private  User assignee;
    private LocalDateTime dueDate;
    final private  LocalDateTime createAt;

    public Task(){
        this.id = UUID.randomUUID();
        this.createAt = LocalDateTime.now();
    }

    public Task( String title, String description, UUID boardId, User assignee, LocalDateTime dueDate) {
        this();
        this.title = title;
        this.description = description;
        this.status = TaskStatus.TO_DO;
        this.boardId = boardId;
        this.assignee = assignee;
        this.dueDate = dueDate;
    }

    public Task( String title, String description, TaskStatus taskStatus,UUID boardId, User assignee, LocalDateTime dueDate) {
        this();
        this.title = title;
        this.description = description;
        this.status = taskStatus;
        this.boardId = boardId;
        this.assignee = assignee;
        this.dueDate = dueDate;
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

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public UUID getBoardId() {
        return boardId;
    }

    public void setBoardId(UUID boardId) {
        this.boardId = boardId;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(title, task.title) && Objects.equals(description, task.description) && status == task.status && Objects.equals(boardId, task.boardId) && Objects.equals(assignee, task.assignee) && Objects.equals(dueDate, task.dueDate) && Objects.equals(createAt, task.createAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, status, boardId, assignee, dueDate, createAt);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", boardId=" + boardId +
                ", assignee=" + assignee +
                ", dueDate=" + dueDate +
                ", createAt=" + createAt +
                '}';
    }
}
