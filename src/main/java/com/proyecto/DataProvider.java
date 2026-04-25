package com.proyecto;

import com.proyecto.model.Pokemon;
import com.proyecto.model.PokemonManager;
import com.proyecto.utils.PokemonReader;

public class DataProvider {
    private static DataProvider instance;

    private PokemonManager pokemonManager;

    private DataProvider(){
        pokemonManager = new PokemonManager();
        pokemonManager.setPokemons(PokemonReader.readPokemons());
    }

    public static DataProvider getInstance(){
        if (instance==null) instance = new DataProvider();
        return instance;
    }

    public PokemonManager getPokemonManager() {
        return this.pokemonManager;
    }
}