package com.alea.aleapokemontest.models;

import com.fasterxml.jackson.annotation.JsonAlias;


//Value in API is 'base_experience'. Annotation used to keep camelCase convention.
public record PokemonDetails(String name, int height, int weight,
                             @JsonAlias(value = "base_experience") int baseExperience) {
}
