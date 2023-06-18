package com.alea.aleapokemontest.services;

import com.alea.aleapokemontest.models.PokeApiUrlEnum;
import com.alea.aleapokemontest.models.PokeApiResult;
import com.alea.aleapokemontest.models.PokemonDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonDetailService {
    private final PokeApiResultService pokeApiResultService;

    public PokemonDetailService(PokeApiResultService pokeApiResultService) {

        this.pokeApiResultService = pokeApiResultService;
    }

    public PokemonDetails getPokemonDetails(PokeApiResult pokeApiResult) {
        if (!pokeApiResult.url().startsWith(PokeApiUrlEnum.POKEMON.name())) {
            throw new IllegalArgumentException("PokeApiResult must be a Pokemon");
        }
        RestTemplate template = new RestTemplate();
        return template.getForObject(pokeApiResult.url(), PokemonDetails.class);
    }
    public List<PokemonDetails> getPokemonDetailsWithLimit(int limit) {
        return pokeApiResultService.getResults(PokeApiUrlEnum.POKEMON, limit).parallelStream()
                .map(this::getPokemonDetails).collect(Collectors.toList());
    }

    public List<PokemonDetails> getAllPokemonDetails() {
        return pokeApiResultService.getResults(PokeApiUrlEnum.POKEMON).parallelStream()
                .map(this::getPokemonDetails).collect(Collectors.toList());

    }

    public List<PokemonDetails> getHeaviestPokemon(int numberOfPokemon) {
        return getAllPokemonDetails().parallelStream()
                .filter(pokemonDetails -> pokemonDetails.weight() < 10000)
                .sorted(Comparator.comparing(PokemonDetails::weight).reversed())
                // According to the PokeAPI, Gigantamax Pokémon weigh 1000 kg (weight is measured in hectograms)
                // by default. In the games, however, Gigantamax forms have a weight of "???", suggesting their weights
                // are immeasurable. In addition, moves affected by weight don't affect them.
                // Therefore, they have been excluded from this query.
                // Source: https://bulbapedia.bulbagarden.net/wiki/Gigantamax
                .limit(numberOfPokemon)
                .collect(Collectors.toList());
    }

    public List<PokemonDetails> getTallestPokemon(int numberOfPokemon) {
        return getAllPokemonDetails().parallelStream()
                .sorted(Comparator.comparing(PokemonDetails::height).reversed())
                .limit(numberOfPokemon)
                .collect(Collectors.toList());
    }

    public List<PokemonDetails> getPokemonWithTheHighestBaseExperience(int numberOfPokemon) {
        return getAllPokemonDetails().parallelStream()
                .sorted(Comparator.comparing(PokemonDetails::baseExperience).reversed())
                .limit(numberOfPokemon)
                .collect(Collectors.toList());
    }


}
