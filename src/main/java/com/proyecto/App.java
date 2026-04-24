package com.proyecto;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        stage.setResizable(false);
        stage.getIcons().add(new Image(App.class.getResource("images/logo.png").toExternalForm()));
        SceneManager sm = SceneManager.getInstance();
        sm.init(stage, "views", 0.5, 0.7, "styles");
        sm.addScene("welcome", "welcomeView");
        sm.addScene("task", "taskView");

        sm.loadScene("welcome");
    }

    public static void main(String[] args) {
        launch();
    }

}