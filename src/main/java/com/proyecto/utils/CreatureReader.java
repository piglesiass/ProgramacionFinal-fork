package com.proyecto.utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.proyecto.App;
import com.proyecto.model.Pokemon;
import com.proyecto.model.PokemonDTO;

/**
 * @author Pablo
 */

public class CreatureReader {

    private static final String pokemonsUrl = "data/pokemons.json";

    public static List<Pokemon> readPokemons(){
        Gson gson = new Gson();
        List<Pokemon> pokemons = new ArrayList<>();

        try {
            String json = Files.readString(Path.of(App.class.getResource(pokemonsUrl).toURI()));
            Type listType = new TypeToken<List<PokemonDTO>>(){}.getType();

            List<PokemonDTO> pokemonsDTO = gson.fromJson(json, listType);

            for (PokemonDTO c : pokemonsDTO) {
                Pokemon pokemon = new Pokemon(c);
                pokemons.add(pokemon);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return pokemons;
    }
}
