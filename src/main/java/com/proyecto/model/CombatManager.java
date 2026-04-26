package com.proyecto.model;

import com.proyecto.model.skills.Skill;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CombatManager {
    private Pokemon jugador;
    private Pokemon enemigo;
    private Random random = new Random();

    public CombatManager(Pokemon jugador, Pokemon enemigo) {
        this.jugador = jugador;
        this.enemigo = enemigo;
    }

    public List<String> executeJugadorTurno(Skill skill) {
        List<String> messages = new ArrayList<>();
        if (jugador.getSpeed() >= enemigo.getSpeed()) {
            messages.add(skill.execute(jugador, enemigo));
            if (!enemigo.isDefeated())
                messages.add(executeEnemigoAction());
        } else {
            messages.add(executeEnemigoAction());
            if (!jugador.isDefeated())
                messages.add(skill.execute(jugador, enemigo));
        }
        return messages;
    }

    public String executeEnemigoAction() {
        return "";
    }

    public boolean combatePerdido() {
        return jugador.isDefeated() || enemigo.isDefeated();
    }
    public Pokemon getGanador() {
        if (jugador.isDefeated()) return enemigo;
        if (enemigo.isDefeated()) return jugador;
        return null;
    }
    public Pokemon getJugador() {
        return jugador;
    }
    public Pokemon getEnemigo() {
        return enemigo;
    }
}