package com.proyecto.model.skills;

import com.proyecto.model.Pokemon;
import java.util.Random;

public class CriticoSkill implements Skill {

    private String name;
    private String description;
    private double multiplier;
    private Random random = new Random();

    public CriticoSkill(String name, String description, double multiplier) {
        this.name = name;
        this.description = description;
        this.multiplier = multiplier;
    }

    @Override
    public String getName(){
        return name; 
    }

    @Override
    public String getDescription(){ 
        return description; 
    }

    @Override
    public String execute(Pokemon user, Pokemon target) {
        double variacion = 0.8 + random.nextDouble() * 0.4;
        int damage = (int)(user.getAttack() * multiplier * variacion);
        if (damage < 1) damage = 1;
        target.takeDamage(damage);
        return "CRITICO! " + user.getName() + " usa " + name + " y hace " + damage + " de daño! (ignora defensa)";
    }
}
