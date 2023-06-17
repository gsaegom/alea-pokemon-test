package com.alea.aleapokemontest.services;

import com.alea.aleapokemontest.models.PokeApiUrlEnum;
import com.alea.aleapokemontest.models.PokeApiResult;
import com.alea.aleapokemontest.models.PokeApiUrl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokeApiResultService {
    private final PokeApiUrlService pokeApiUrlService;

    public PokeApiResultService(PokeApiUrlService pokeApiUrlService) {
        this.pokeApiUrlService = pokeApiUrlService;
    }

    public List<PokeApiResult> getResults(PokeApiUrlEnum resultUrl, int limit) {
        PokeApiUrl pokeApiUrl = pokeApiUrlService.getPokeApiUrl(resultUrl, limit);
        return pokeApiUrl.results();
    }

    public List<PokeApiResult> getResults(PokeApiUrlEnum resultUrl) {
        PokeApiUrl pokeApiUrl = pokeApiUrlService.getPokeApiUrl(resultUrl, 10000);
        return pokeApiUrl.results();
    }

}
