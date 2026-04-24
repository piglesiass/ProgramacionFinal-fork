package com.proyecto.controllers;

import com.proyecto.SceneManager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WelcomeController {
    
    @FXML
    Button enterBtn;

    @FXML
    public void initialize(){
        enterBtn.setOnAction( event -> {
            SceneManager.getInstance().loadScene("task");
        });
    }
}
