package com.proyecto.model;

import java.util.List;

public record PokemonDTO(String name, String type, int maxHp, int attack, int defense, int speed, List<String> skills) {
}

