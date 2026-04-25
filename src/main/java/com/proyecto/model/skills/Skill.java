package com.proyecto.model.skills;

import com.proyecto.model.Creature;

/**
 * @author Pablo
 */

public interface Skill {
    String getName();
    String getDescription();
    String execute(Creature user, Creature target);
}
