package com.proyecto.model.skills;

import com.proyecto.model.Pokemon;

public class AtaqueSkill implements Skill {

    private String name;
    private String description;
    private double multiplier;

    public AtaqueSkill(String name, String description, double multiplier) {
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
        int damage = (int)(user.getAttack() * multiplier) - target.getDefense();
        if (damage < 1) damage = 1;
        target.takeDamage(damage);
        return user.getName() + " usa " + name + " y hace " + damage + " de daño";
    }
}
