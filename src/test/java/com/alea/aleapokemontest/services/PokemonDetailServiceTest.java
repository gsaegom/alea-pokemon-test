package com.alea.aleapokemontest.services;

import com.alea.aleapokemontest.models.PokeApiResult;
import com.alea.aleapokemontest.models.PokemonDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class PokemonDetailServiceTest {

    @Mock
    private PokeApiResultService pokeApiResultService;
    private PokemonDetailService pokemonDetailService;

    @BeforeEach
    void setUp() {
        pokemonDetailService = new PokemonDetailService(pokeApiResultService);
    }
    
    @Test
    void givenPokeApiResult_whenResultIsPokemon_thenReturnDetails() {
        PokeApiResult bulbasaur = new PokeApiResult("bulbasaur", "https://pokeapi.co/api/v2/pokemon/1");
        PokemonDetails expected = new PokemonDetails("bulbasaur", 7, 69, 64);

        PokemonDetails actual = pokemonDetailService.getPokemonDetails(bulbasaur);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void givenPokeApiResult_whenResultIsNotPokemon_thenThrowIllegalArgumentException() {
        PokeApiResult invalidResult = new PokeApiResult("invalid", "result");

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> pokemonDetailService.getPokemonDetails(invalidResult));
    }

    @Test
    void givenNoArguments_whenGetAllPokemonDetailsIsCalled_thenReturnDetailsOfAllPokemon() {


        Mockito.when(pokeApiResultService.getResults(any())).thenReturn(basePokeApiResults());

        List<PokemonDetails> expected = basePokemonDetails();

        List<PokemonDetails> actual = pokemonDetailService.getAllPokemonDetails();

        Assertions.assertEquals(expected, actual);

        Mockito.verify(pokeApiResultService).getResults(any());
    }

    @Test
    void givenAResultLimit_whenGetPokemonWithLimitIsCalled_thenReturnDetailsOfPokemonWithinLimit() {


        Mockito.when(pokeApiResultService.getResults(any(), anyInt())).thenReturn(basePokeApiResults().subList(0, 3));

        List<PokemonDetails> expected = basePokemonDetails().subList(0, 3);

        List<PokemonDetails> actual = pokemonDetailService.getPokemonDetailsWithLimit(3);

        Assertions.assertEquals(expected, actual);

        Mockito.verify(pokeApiResultService).getResults(any(), anyInt());
    }

    @Test
    void givenNumberOfPokemon_whenGetHeaviestPokemon_thenReturnTheHeaviestNPokemon() {


        Mockito.when(pokeApiResultService.getResults(any())).thenReturn(basePokeApiResults());

        PokemonDetails tyranitarDetails = new PokemonDetails("tyranitar", 20, 2020, 300);
        PokemonDetails pupitarDetails = new PokemonDetails("pupitar", 12, 1520, 144);
        PokemonDetails venusaurDetails = new PokemonDetails("venusaur", 20, 1000, 263);

        List<PokemonDetails> expected = Arrays.asList(tyranitarDetails, pupitarDetails, venusaurDetails);

        List<PokemonDetails> actual = pokemonDetailService.getHeaviestPokemon(3);
        Assertions.assertEquals(expected, actual);

        Mockito.verify(pokeApiResultService).getResults(any());

    }

    @Test
    void givenNumberOfPokemon_whenGetTallestPokemon_thenReturnTheTallestNPokemon() {


        Mockito.when(pokeApiResultService.getResults(any())).thenReturn(basePokeApiResults());

        PokemonDetails tyranitarDetails = new PokemonDetails("tyranitar", 20, 2020, 300);
        PokemonDetails pupitarDetails = new PokemonDetails("pupitar", 12, 1520, 144);
        PokemonDetails venusaurDetails = new PokemonDetails("venusaur", 20, 1000, 263);

        List<PokemonDetails> expected = Arrays.asList(venusaurDetails, tyranitarDetails, pupitarDetails);

        List<PokemonDetails> actual = pokemonDetailService.getTallestPokemon(3);

        Assertions.assertEquals(expected, actual);

        Mockito.verify(pokeApiResultService).getResults(any());

    }

    @Test
    void givenNumberOfPokemon_whenGetPokemonWithHighestBaseExperience_thenReturnNPokemonWithHighestBaseExperience() {


        Mockito.when(pokeApiResultService.getResults(any())).thenReturn(basePokeApiResults());

        PokemonDetails tyranitarDetails = new PokemonDetails("tyranitar", 20, 2020, 300);
        PokemonDetails venusaurDetails = new PokemonDetails("venusaur", 20, 1000, 263);
        PokemonDetails butterfreeDetails = new PokemonDetails("butterfree", 11, 320, 198);

        List<PokemonDetails> expected = Arrays.asList(tyranitarDetails, venusaurDetails, butterfreeDetails);

        List<PokemonDetails> actual = pokemonDetailService.getPokemonWithTheHighestBaseExperience(3);

        Assertions.assertEquals(expected, actual);

        Mockito.verify(pokeApiResultService).getResults(any());

    }


    public List<PokemonDetails> basePokemonDetails() {

        PokemonDetails bulbasaurDetails = new PokemonDetails("bulbasaur", 7, 69, 64);
        PokemonDetails ivysaurDetails = new PokemonDetails("ivysaur", 10, 130, 142);
        PokemonDetails venusaurDetails = new PokemonDetails("venusaur", 20, 1000, 263);
        PokemonDetails caterpieDetails = new PokemonDetails("caterpie", 3, 29, 39);
        PokemonDetails metapodDetails = new PokemonDetails("metapod", 7, 99, 72);
        PokemonDetails butterfreeDetails = new PokemonDetails("butterfree", 11, 320, 198);
        PokemonDetails larvitarDetails = new PokemonDetails("larvitar", 6, 720, 60);
        PokemonDetails pupitarDetails = new PokemonDetails("pupitar", 12, 1520, 144);
        PokemonDetails tyranitarDetails = new PokemonDetails("tyranitar", 20, 2020, 300);

        return Arrays.asList(bulbasaurDetails, ivysaurDetails, venusaurDetails, caterpieDetails, metapodDetails
                , butterfreeDetails, larvitarDetails, pupitarDetails, tyranitarDetails);

    }

    public List<PokeApiResult> basePokeApiResults() {
        PokeApiResult bulbasaur = new PokeApiResult("bulbasaur", "https://pokeapi.co/api/v2/pokemon/1");
        PokeApiResult ivysaur = new PokeApiResult("ivysaur", "https://pokeapi.co/api/v2/pokemon/2");
        PokeApiResult venusaur = new PokeApiResult("venusaur", "https://pokeapi.co/api/v2/pokemon/3");
        PokeApiResult caterpie = new PokeApiResult("caterpie", "https://pokeapi.co/api/v2/pokemon/10");
        PokeApiResult metapod = new PokeApiResult("metapod", "https://pokeapi.co/api/v2/pokemon/11");
        PokeApiResult butterfree = new PokeApiResult("butterfree", "https://pokeapi.co/api/v2/pokemon/12");
        PokeApiResult larvitar = new PokeApiResult("larvitar", "https://pokeapi.co/api/v2/pokemon/246");
        PokeApiResult pupitar = new PokeApiResult("pupitar", "https://pokeapi.co/api/v2/pokemon/247");
        PokeApiResult tyranitar = new PokeApiResult("tyranitar", "https://pokeapi.co/api/v2/pokemon/248");

        return Arrays.asList(bulbasaur, ivysaur, venusaur, caterpie, metapod, butterfree, larvitar
                , pupitar, tyranitar);

    }
}