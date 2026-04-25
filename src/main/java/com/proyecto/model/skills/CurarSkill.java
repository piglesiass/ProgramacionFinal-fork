package com.proyecto.model.skills;

import com.proyecto.model.Pokemon;

// Cura HP propios
public class CurarSkill implements Skill {

    private String name;
    private String description;
    private int healAmount;

    public CurarSkill(String name, String description, int healAmount) {
        this.name = name;
        this.description = description;
        this.healAmount = healAmount;
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
        int healed = user.heal(healAmount);
        return user.getName() + " usa " + name + " y recupera " + healed + " HP!";
    }
}