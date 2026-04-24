package com.proyecto.controllers;

import com.proyecto.DataProvider;
import com.proyecto.components.TaskListCell;
import com.proyecto.model.Task;
import com.proyecto.model.TaskManager;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TaskController {
    @FXML
    TextField nameTxt;

    @FXML
    TextField descriptionTxt;

    @FXML
    Slider prioritySlider;

    @FXML
    Button addBtn;

    @FXML
    ListView<Task> listView;

    DataProvider provider;

    @FXML
    public void initialize() {
        provider = DataProvider.getInstance();
        initAddBtn();
        initTaskListView();
    }

    public void initAddBtn() {
        addBtn.setOnAction(event -> {
            if (!nameTxt.getText().isBlank() && !descriptionTxt.getText().isBlank())
                provider.getTaskManager().addTask(
                        new Task(nameTxt.getText(), descriptionTxt.getText(), (int) prioritySlider.getValue()));
        });
    }

    public void initTaskListView() {

        TaskManager manager = provider.getTaskManager();

        listView.setItems(manager.getTasks());

        listView.setCellFactory(lv -> new TaskListCell());
    }
}
