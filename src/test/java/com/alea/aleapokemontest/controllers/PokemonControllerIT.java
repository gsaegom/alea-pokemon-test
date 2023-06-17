package com.alea.aleapokemontest.controllers;

import com.alea.aleapokemontest.AleaPokemonTestApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AleaPokemonTestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class PokemonControllerIT {

    @Autowired
    private MockMvc mvc;

    @Test
    public void givenPokemonDetails_whenGetPokemonDetails_thenStatus200() throws Exception {

        mvc.perform(get("/pokemon-details").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1281))))
                .andExpect(jsonPath("$[0].name", is("bulbasaur")))
                .andExpect(jsonPath("$[0].height", is(7)))
                .andExpect(jsonPath("$[0].weight", is(69)))
                .andExpect(jsonPath("$[0].baseExperience", is(64)))
                .andExpect(jsonPath("$[1].name", is("ivysaur")))
                .andExpect(jsonPath("$[1].height", is(10)))
                .andExpect(jsonPath("$[1].weight", is(130)))
                .andExpect(jsonPath("$[1].baseExperience", is(142)));

    }

    @Test
    public void givenPokemonDetails_whenPokemonDetailsWithLimit_thenStatus200() throws Exception {

        mvc.perform(get("/pokemon-details/2").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(equalTo(2))))
                .andExpect(jsonPath("$[0].name", is("bulbasaur")))
                .andExpect(jsonPath("$[0].height", is(7)))
                .andExpect(jsonPath("$[0].weight", is(69)))
                .andExpect(jsonPath("$[0].baseExperience", is(64)))
                .andExpect(jsonPath("$[1].name", is("ivysaur")))
                .andExpect(jsonPath("$[1].height", is(10)))
                .andExpect(jsonPath("$[1].weight", is(130)))
                .andExpect(jsonPath("$[1].baseExperience", is(142)));

    }

    @Test
    public void givenPokemonDetails_whenGetTwoHeaviestPokemon_thenStatus200() throws Exception {
// As new games are released, the list of Pokémon increases, hence the greaterThanOrEqualTo()
        mvc.perform(get("/pokemon/heaviest/2").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].name", is("cosmoem")))
                .andExpect(jsonPath("$[0].height", is(1)))
                .andExpect(jsonPath("$[0].weight", is(9999)))
                .andExpect(jsonPath("$[0].baseExperience", is(140)))
                .andExpect(jsonPath("$[1].name", is("celesteela")))
                .andExpect(jsonPath("$[1].height", is(92)))
                .andExpect(jsonPath("$[1].weight", is(9999)))
                .andExpect(jsonPath("$[1].baseExperience", is(285)));
    }

    @Test
    public void givenPokemonDetails_whenGetTwoTallestPokemon_thenStatus200() throws Exception {

        mvc.perform(get("/pokemon/tallest/2").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(equalTo(2))))
                .andExpect(jsonPath("$[0].name", is("eternatus-eternamax")))
                .andExpect(jsonPath("$[0].height", is(1000)))
                .andExpect(jsonPath("$[0].weight", is(0)))
                .andExpect(jsonPath("$[0].baseExperience", is(563)))
                .andExpect(jsonPath("$[1].name", is("centiskorch-gmax")))
                .andExpect(jsonPath("$[1].height", is(750)))
                .andExpect(jsonPath("$[1].weight", is(10000)))
                .andExpect(jsonPath("$[1].baseExperience", is(184)));
    }

    @Test
    public void givenPokemonDetails_whenGetTwoPokemonWithHighestBaseExperience_thenStatus200() throws Exception {

        mvc.perform(get("/pokemon/base-experience/2").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(equalTo(2))))
                .andExpect(jsonPath("$[0].name", is("blissey")))
                .andExpect(jsonPath("$[0].height", is(15)))
                .andExpect(jsonPath("$[0].weight", is(468)))
                .andExpect(jsonPath("$[0].baseExperience", is(635)))
                .andExpect(jsonPath("$[1].name", is("eternatus-eternamax")))
                .andExpect(jsonPath("$[1].height", is(1000)))
                .andExpect(jsonPath("$[1].weight", is(0)))
                .andExpect(jsonPath("$[1].baseExperience", is(563)));
    }
}