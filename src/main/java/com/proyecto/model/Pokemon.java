package com.proyecto.model;

import java.util.ArrayList;
import java.util.List;

import com.proyecto.model.skills.MenuSkill;
import com.proyecto.model.skills.Skill;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Pokemon {

    private String name;
    private String type;
    private int maxHp;
    private IntegerProperty currentHp;
    private int attack;
    private int defense;
    private int speed;
    private List<Skill> skills;

    public Pokemon(PokemonDTO dto) {
        this.name = dto.name();
        this.type = dto.type();
        this.maxHp = dto.maxHp();
        this.currentHp = new SimpleIntegerProperty(dto.maxHp());
        this.attack = dto.attack();
        this.defense = dto.defense();
        this.speed = dto.speed();
        this.skills = new ArrayList<>();
        for (String s : dto.skills())
            this.skills.add(MenuSkill.create(s));
    }

    public Pokemon(Pokemon pkm) {
        this.name = pkm.name;
        this.type = pkm.type;
        this.maxHp = pkm.maxHp;
        this.currentHp = new SimpleIntegerProperty(pkm.maxHp);
        this.attack = pkm.attack;
        this.defense = pkm.defense;
        this.speed = pkm.speed;
        this.skills = new ArrayList<>(pkm.skills);
    }

    public void takeDamage(int amount) {
        currentHp.set(Math.max(0, currentHp.get() - amount));
    }

    public int heal(int amount) {
        int before = currentHp.get();
        currentHp.set(Math.min(maxHp, currentHp.get() + amount));
        return currentHp.get() - before;
    }

    public void boostAttack(int amount) {
        this.attack += amount;
    }

    public void reduceDefense(int amount) {
        this.defense = Math.max(0, this.defense - amount);
    }

    public boolean isDefeated() {
        return currentHp.get() <= 0;
    }

    public String getName(){ 
        return name;
    }
    public String getType(){ 
        return type;
    }
    public int getMaxHp(){ 
        return maxHp; 
    }
    public int getCurrentHp(){
        return currentHp.get();
    }
    public IntegerProperty currentHpProperty(){
        return currentHp;
    }
    public int getAttack(){ 
        return attack; 
    }
    public int getDefense(){ 
        return defense;
    }
    public int getSpeed(){ 
        return speed; 
    }
    public List<Skill> getSkills(){
         return skills;
    }

    public void setCurrentHp(IntegerProperty currentHp) {
        this.currentHp = currentHp;
    }
}
