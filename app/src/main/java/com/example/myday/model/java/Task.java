package com.example.myday.model.java;

import java.util.UUID;
import java.util.Objects;

/**
 * Representação da entidade Task em Java.
 * 
 * Esta classe demonstra a proficiência na linguagem Java e a plena interoperabilidade
 * entre Java e Kotlin no ecossistema Android moderno. Embora o projeto utilize
 * Jetpack Compose (que requer Kotlin), a lógica de negócio e os modelos de dados
 * mantêm compatibilidade total com Java.
 */
public class Task {
    private final UUID id;
    private String description;
    private boolean isCompleted;

    public Task(String description) {
        this(UUID.randomUUID(), description, false);
    }

    public Task(UUID id, String description, boolean isCompleted) {
        this.id = id;
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return isCompleted == task.isCompleted &&
                Objects.equals(id, task.id) &&
                Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, isCompleted);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
