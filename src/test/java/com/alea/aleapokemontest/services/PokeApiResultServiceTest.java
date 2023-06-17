package com.alea.aleapokemontest.services;

import com.alea.aleapokemontest.models.PokeApiResult;
import com.alea.aleapokemontest.models.PokeApiUrlEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class PokeApiResultServiceTest {

    private PokeApiResultService pokeApiResultService;

    @BeforeEach
    void setUp() {
        PokeApiUrlService pokeApiUrlService = new PokeApiUrlService();
        pokeApiResultService = new PokeApiResultService(pokeApiUrlService);
    }


    @Test
    void givenUrlEnum_whenLimitIsSpecified_thenReturnLimitedNumberOfResults() {
        PokeApiResult url1 = new PokeApiResult("cheri", "https://pokeapi.co/api/v2/berry/1/");
        PokeApiResult url2 = new PokeApiResult("chesto", "https://pokeapi.co/api/v2/berry/2/");
        PokeApiResult url3 = new PokeApiResult("pecha", "https://pokeapi.co/api/v2/berry/3/");

        List<PokeApiResult> expected = Arrays.asList(url1, url2, url3);

        List<PokeApiResult> actual = pokeApiResultService.getResults(PokeApiUrlEnum.BERRY, 3);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void givenUrlEnum_whenNoLimitIsSpecified_thenReturnAllResults() {
        PokeApiResult result1 = new PokeApiResult("generation-i", "https://pokeapi.co/api/v2/generation/1/");
        PokeApiResult result2 = new PokeApiResult("generation-ii", "https://pokeapi.co/api/v2/generation/2/");
        PokeApiResult result3 = new PokeApiResult("generation-iii", "https://pokeapi.co/api/v2/generation/3/");
        PokeApiResult result4 = new PokeApiResult("generation-iv", "https://pokeapi.co/api/v2/generation/4/");
        PokeApiResult result5 = new PokeApiResult("generation-v", "https://pokeapi.co/api/v2/generation/5/");
        PokeApiResult result6 = new PokeApiResult("generation-vi", "https://pokeapi.co/api/v2/generation/6/");
        PokeApiResult result7 = new PokeApiResult("generation-vii", "https://pokeapi.co/api/v2/generation/7/");
        PokeApiResult result8 = new PokeApiResult("generation-viii", "https://pokeapi.co/api/v2/generation/8/");
        PokeApiResult result9 = new PokeApiResult("generation-ix", "https://pokeapi.co/api/v2/generation/9/");


        List<PokeApiResult> expected = Arrays.asList(result1, result2, result3, result4, result5, result6, result7,
                result8, result9);

        List<PokeApiResult> actual = pokeApiResultService.getResults(PokeApiUrlEnum.GENERATION);

        Assertions.assertEquals(expected, actual);
    }
}