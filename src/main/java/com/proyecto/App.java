package com.proyecto;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 * @author javi
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        stage.setResizable(false);
        stage.setTitle("Juego de Combates");
        SceneManager sm = SceneManager.getInstance();
        sm.init(stage, "views", 0.5, 0.75, "styles");
        sm.addScene("menu", "menuView");
        sm.addScene("combat", "combatView");
        sm.loadScene("menu");
    }

    public static void main(String[] args) {
        launch();
    }

}