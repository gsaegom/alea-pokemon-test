package com.alea.aleapokemontest.services;

import com.alea.aleapokemontest.models.PokeApiUrl;
import com.alea.aleapokemontest.models.PokeApiUrlEnum;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PokeApiUrlService {

    public PokeApiUrl getPokeApiUrl(PokeApiUrlEnum url, int limitResults) {
        RestTemplate template = new RestTemplate();
        return template.getForObject(url.limitResults(limitResults), PokeApiUrl.class);
    }

    public PokeApiUrl getPokeApiUrl(PokeApiUrlEnum url) {
        RestTemplate template = new RestTemplate();
        return template.getForObject(url.limitResults(10000), PokeApiUrl.class);
    }
}
