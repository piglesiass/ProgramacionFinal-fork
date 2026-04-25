package com.proyecto.controllers;

import com.proyecto.DataProvider;
import com.proyecto.SceneManager;
import com.proyecto.model.Pokemon;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.List;

public class MenuController {

    @FXML
    ComboBox<String> pokemonComboBox;

    @FXML
    Label infoLabel;

    @FXML
    Button startBtn;

    List<Pokemon> pokemons;

    @FXML
    public void initialize() {
        pokemons = DataProvider.getInstance().getPokemonManager().getPokemons();

        for (Pokemon p : pokemons) {
            pokemonComboBox.getItems().add(p.getName());
        }

        pokemonComboBox.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
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

        pokemonComboBox.getSelectionModel().selectFirst();

        startBtn.setOnAction(event -> {
            int idx = pokemonComboBox.getSelectionModel().getSelectedIndex();
            if (idx >= 0) {
                Pokemon elegido = new Pokemon(pokemons.get(idx));
                SceneManager.getInstance().loadScene("combat", elegido);
            }
        });
    }
}