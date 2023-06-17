package com.alea.aleapokemontest.controllers;

import com.alea.aleapokemontest.models.PokemonDetails;
import com.alea.aleapokemontest.services.PokemonDetailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class PokemonController {

    private final PokemonDetailService pokemonDetailService;

    public PokemonController(PokemonDetailService pokemonDetailService) {
        this.pokemonDetailService = pokemonDetailService;
    }

    @GetMapping("/pokemon-details")
    public List<PokemonDetails> getPokemonDetails() {
        return pokemonDetailService.getAllPokemonDetails();
    }

    @GetMapping("/pokemon-details/{limit}")
    public List<PokemonDetails> getPokemonDetailsWithLimit(@PathVariable int limit) {
        return pokemonDetailService.getPokemonDetailsWithLimit(limit);
    }


    @GetMapping("/pokemon/heaviest/{numberOfPokemon}")
    public List<PokemonDetails> getHeaviestPokemon(@PathVariable int numberOfPokemon) {
        return pokemonDetailService.getHeaviestPokemon(numberOfPokemon);
    }

    @GetMapping("/pokemon/tallest/{numberOfPokemon}")
    public List<PokemonDetails> getTallestPokemon(@PathVariable int numberOfPokemon) {
        return pokemonDetailService.getTallestPokemon(numberOfPokemon);
    }

    @GetMapping("/pokemon/base-experience/{numberOfPokemon}")
    public List<PokemonDetails> getPokemonWithTheHighestBaseExperience(@PathVariable int numberOfPokemon) {
        return pokemonDetailService.getPokemonWithTheHighestBaseExperience(numberOfPokemon);
    }


}
