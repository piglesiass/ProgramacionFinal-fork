package com.proyecto.controllers;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.proyecto.DataProvider;
import com.proyecto.SceneManager;
import com.proyecto.model.Pokemon;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class MenuController {

    @FXML
    ComboBox<String> creatureComboBox;

    @FXML
    Label infoLabel;

    @FXML
    Button startBtn;

    @FXML
    Button desktopBtn;

    List<Pokemon> pokemons;

    @FXML
    public void initialize() {
        pokemons = DataProvider.getInstance().getPokemonManager().getPokemons();

        for (Pokemon p : pokemons) {
            creatureComboBox.getItems().add(p.getName());
        }

        creatureComboBox.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            int idx = newVal.intValue();
            if (idx >= 0) {
                Pokemon p = pokemons.get(idx);
                infoLabel.setText(
                    "Tipo: " + p.getType() + "\n" +
                    "HP: " + p.getMaxHp() + "\n" +
                    "Ataque: " + p.getAttack() + "\n" +
                    "Defensa: " + p.getDefense() + "\n" +
                    "Velocidad: " + p.getSpeed()
                );
            }
        });

        creatureComboBox.getSelectionModel().selectFirst();

        startBtn.setOnAction(event -> {
            int idx = creatureComboBox.getSelectionModel().getSelectedIndex();
            if (idx >= 0) {
                Pokemon elegido = new Pokemon(pokemons.get(idx));
                SceneManager.getInstance().loadScene("combat", elegido);
            }
        });

        desktopBtn.setOnAction(event ->{
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/piglesiass/ProgramacionFinal-fork"));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });
    }
}