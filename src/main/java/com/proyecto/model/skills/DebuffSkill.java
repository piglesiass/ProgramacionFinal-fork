package com.proyecto.model.skills;

import com.proyecto.model.Pokemon;

/**
 * @author Pablo
 */

public class DebuffSkill implements Skill {

    private String name;
    private String description;
    private int reducirDefensa;

    public DebuffSkill(String name, String description, int reducirDefensa) {
        this.name = name;
        this.description = description;
        this.reducirDefensa = reducirDefensa;
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
        target.reduceDefense(reducirDefensa);
        return user.getName() + " usa " + name + "! La defensa de " + target.getName() + " baja " + reducirDefensa + ".";
    }
}
