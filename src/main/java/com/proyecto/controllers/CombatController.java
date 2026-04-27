package com.proyecto.controllers;

import com.proyecto.DataProvider;
import com.proyecto.DataReceiver;
import com.proyecto.SceneManager;
import com.proyecto.model.CombatManager;
import com.proyecto.model.Pokemon;
import com.proyecto.model.skills.Skill;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Random;

public class CombatController implements DataReceiver<Pokemon> {

    @FXML Label playerNameLabel;
    @FXML Label playerHpLabel;
    @FXML ProgressBar playerHpBar;

    @FXML Label enemyNameLabel;
    @FXML Label enemyHpLabel;
    @FXML ProgressBar enemyHpBar;

    @FXML VBox skillsVBox;
    @FXML TextArea combatLog;
    @FXML Button returnBtn;

    CombatManager combat;

    @Override
    public void receiveData(Pokemon playerCreature) {
        List<Pokemon> all = DataProvider.getInstance().getPokemonManager().getPokemons();
        List<Pokemon> candidates = all.stream()
                .filter(c -> !c.getName().equals(playerCreature.getName()))
                .toList();
        Pokemon enemy = new Pokemon(candidates.get(new Random().nextInt(candidates.size())));

        combat = new CombatManager(playerCreature, enemy);
        setupUI();
    }

    public void setupUI() {
        Pokemon p = combat.getJugador();
        Pokemon e = combat.getEnemigo();

        playerNameLabel.setText(p.getName() + " [" + p.getType() + "]");
        enemyNameLabel.setText(e.getName() + " [" + e.getType() + "]");

        updateHp(p, playerHpLabel, playerHpBar);
        updateHp(e, enemyHpLabel, enemyHpBar);

        // binding reactivo de HP igual que el profesor hace con priority
        p.currentHpProperty().addListener((obs, oldVal, newVal) -> updateHp(p, playerHpLabel, playerHpBar));
        e.currentHpProperty().addListener((obs, oldVal, newVal) -> updateHp(e, enemyHpLabel, enemyHpBar));

        // crear botones de habilidades dinamicamente (igual que TaskListCell pero mas simple)
        skillsVBox.getChildren().clear();
        for (Skill skill : p.getSkills()) {
            Button btn = new Button(skill.getName());
            btn.setMaxWidth(Double.MAX_VALUE);
            btn.setOnAction(ev -> onSkillClick(skill));
            skillsVBox.getChildren().add(btn);
        }

        returnBtn.setVisible(false);
        returnBtn.setOnAction(ev -> SceneManager.getInstance().loadScene("menu"));

        combatLog.appendText("Comienza el combate! " + p.getName() + " vs " + e.getName() + "\n");
    }

    public void onSkillClick(Skill skill) {
        if (combat.combatePerdido()) return;

        List<String> messages = combat.executeJugadorTurno(skill);
        for (String msg : messages) {
            combatLog.appendText(msg + "\n");
        }

        if (combat.combatePerdido()) {
            Pokemon winner = combat.getGanador();
            if (winner == combat.getJugador()) {
                combatLog.appendText("Has ganado! " + winner.getName() + " es el vencedor.\n");
            } else {
                combatLog.appendText("Has perdido... " + winner.getName() + " gana el combate.\n");
            }
            skillsVBox.getChildren().forEach(n -> n.setDisable(true));
            returnBtn.setVisible(true);
        }
    }

    public void updateHp(Pokemon c, Label label, ProgressBar bar) {
        label.setText(c.getCurrentHp() + " / " + c.getMaxHp() + " HP");
        double ratio = (double) c.getCurrentHp() / c.getMaxHp();
        bar.setProgress(ratio);
    }
}