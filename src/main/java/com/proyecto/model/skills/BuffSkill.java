package com.proyecto.model.skills;

import com.proyecto.model.Pokemon;

// Sube el ataque del usuario
public class BuffSkill implements Skill {

    private String name;
    private String description;
    private int boost;

    public BuffSkill(String name, String description, int boost) {
        this.name = name;
        this.description = description;
        this.boost = boost;
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
        user.boostAttack(boost);
        return user.getName() + " usa " + name + "! Su ataque sube " + boost + " puntos.";
    }
}
