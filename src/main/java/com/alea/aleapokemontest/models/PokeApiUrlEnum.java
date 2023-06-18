package com.alea.aleapokemontest.models;

public enum PokeApiUrlEnum {
    ABILITY("https://pokeapi.co/api/v2/ability"), BERRY("https://pokeapi.co/api/v2/berry"),GENERATION("https://pokeapi.co/api/v2/generation"), POKEMON("https://pokeapi.co/api/v2/pokemon");

    private final String url;

    PokeApiUrlEnum(String url) {
        this.url = url;
    }
    public String limitResults(int limit){
        return this.url + "?limit=" + limit;
    }

    public String getUrl() {
        return url;
    }
}
