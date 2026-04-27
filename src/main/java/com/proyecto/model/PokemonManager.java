package com.proyecto.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pablo
 */

public class PokemonManager {

    private List<Pokemon> pokemons;

    public PokemonManager() {
        pokemons = new ArrayList<>();
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public List<Pokemon> getPokemons() {
        return this.pokemons;
    }

    public void removeTask(Task task) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeTask'");
    }
}
