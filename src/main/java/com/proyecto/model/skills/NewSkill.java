package com.proyecto.model.skills;

import com.proyecto.model.Pokemon;

public class NewSkill implements Skill {

    private String name;
    private String description;
    private double multiplier;

    public NewSkill(String name, String description, double multiplier) {
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
        if(user.getCurrentHp()%2==0){
            int damage = (int)(target.getMaxHp()/2);
            if (damage < 1) damage = 1;
            if((target.getCurrentHp()-damage)<=0){
                damage= target.getCurrentHp()-1;
            };
            target.takeDamage(damage);
            return user.getName() + " usa " + name + " y hace " + damage + " de daño";
        }
        return user.getName() + " no puede usar " + name + " porque no tiene puntos de vida pares";
    }
}
