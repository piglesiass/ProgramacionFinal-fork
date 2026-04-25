package com.proyecto.model.skills;

import com.proyecto.model.Pokemon;

/**
 * @author Pablo
 */

public interface Skill {
    String getName();
    String getDescription();
    String execute(Pokemon user, Pokemon target);
}
