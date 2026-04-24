package com.proyecto.model;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TaskManager {
    ObservableList<Task> tasks;

    public TaskManager() {
        tasks = FXCollections.observableArrayList();
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public void removeTask(Task task){
        tasks.remove(task);
    }


    public ObservableList<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks.clear();
        this.tasks.addAll(tasks);
    }
}