package com.proyecto.model;
import java.util.Objects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Task {
    private static int lastId;
    private int id;
    private String name;
    private String description;
    private IntegerProperty priority;


    public Task() {
    }

    public Task(String name, String description, int priority) {
        this.id = lastId++;
        this.name = name;
        this.description = description;
        this.priority = new SimpleIntegerProperty(priority);
    }

    public void increasePriority(){
        priority.set(Math.min(priority.get()+1, 5));
    }

    public void decresePriority(){
        priority.set(Math.max(priority.get()-1, 1));
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return this.priority.get();
    }

    public IntegerProperty getPriorityProperty(){
        return this.priority;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return id == task.id && Objects.equals(name, task.name) && Objects.equals(description, task.description) && priority == task.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, priority);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", priority='" + getPriority() + "'" +
            "}";
    }
    
}
