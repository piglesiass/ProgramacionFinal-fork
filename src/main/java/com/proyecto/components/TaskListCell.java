package com.proyecto.components;

import com.proyecto.DataProvider;
import com.proyecto.model.Task;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TaskListCell extends ListCell<Task> {

    private HBox root = new HBox(10);
    private VBox textBox = new VBox();

    private Label nameLabel = new Label();
    private Label descLabel = new Label();
    private Label priorityLabel = new Label();

    private Button incBtn = new Button("+");
    private Button decBtn = new Button("-");
    private Button deleteBtn = new Button("Eliminar");

    public TaskListCell() {

        textBox.getChildren().addAll(nameLabel, descLabel, priorityLabel);
        root.getChildren().addAll(incBtn, decBtn, deleteBtn, textBox);

        incBtn.setOnAction(e -> {
            Task task = getItem();
            if (task != null) {
                task.increasePriority();
            }
        });

        decBtn.setOnAction(e -> {
            Task task = getItem();
            if (task != null) {
                task.decresePriority();
            }
        });

        deleteBtn.setOnAction(e -> {
            Task task = getItem();
            if (task != null) {
                DataProvider.getInstance()
                        .getPokemonManager()
                        .removeTask(task);
            }
        });
    }

    @Override
    protected void updateItem(Task task, boolean empty) {
        super.updateItem(task, empty);

        if (empty || task == null) {
            setGraphic(null);
            priorityLabel.textProperty().unbind();
            return;
        }

        nameLabel.setText(task.getName());
        descLabel.setText(task.getDescription());

        priorityLabel.textProperty().unbind(); // 👈 evita leaks
        priorityLabel.textProperty().bind(
                task.getPriorityProperty().asString("Prioridad: %d"));

        setGraphic(root);
    }
}