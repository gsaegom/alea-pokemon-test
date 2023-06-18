# Alea Knowledge Test

For this assignment, an application has created to expose an API with the following scenarios:

1. The 5 heaviest Pokémons.
2. The 5 tallest Pokémons.
3. The 5 Pokémons with the highest base experience.

In order to do so, a Spring RESTful application has been created.

## Layers

This application consists of a model layer, a service layer, and a controller layer.

### Model Layer

The model layer gas three classes: PokeApiUrl, containing a list of PokeApiResult, which contains the name and the url of a single result, and PokemonDetail, which contains the relevant details of a Pokémon for our application. It also contains an Enum, PokeApiUrlEnum, for the different types of results that can be fetched from the API (berries, Pokémon, generations...). This is to ensure a valid URL is used.

### Service Layer

Similarly, the application has a service layer, with Service classes for all the entities. PokeApiUrlService contains two methods to retrieve a PokeApiUrl from a PokeApiUrlEnum, using a RestTemplate. Thanks to method overloading, one can choose whether this PokeApiUrl will contain all the relevant results or a specific number of them. PokeApiResultService uses this PokeApiUrl to return the containing results, with or without a result limit. 

As for PokemonDetailService, it contains three methods to retrieve PokemonDetails. getPokemonDetails() takes a PokeApiResult. If it's a Pokémon API, it returns the details of said Pokémon. Otherwise, it throws an IllegalArgumentException. getPokemonDetailsWithLimit() and getAllPokemonDetails() return a list of PokemonDetails. They stream through a list of PokemonResult (containing either all results or a number of them), and turn them into PokemonDetails using getPokemonDetails(). In both cases, a parallel stream is used for performance reasons.

In addition, it also contains three methods to retrieve the heaviest and tallest Pokémon, as well as those with the highest base experience. All three methods work in a similar way. They take an int, which defines the number of Pokémon to be retrieved. Then, they stream through all the Pokémon, sort them according to the relevant feature (height, weight or baseExperience), limit the results to the desired number, and then return a list. For the heaviest Pokémon, though, a filter has been added in order to exclude G-Max Pokémon. Although they don't really have a measurable weight according to the games, they have been given a weight of 1000 kg by default, which makes them the heaviest Pokémon in the API. Without the filter, they would be pretty much the only Pokémon returned.

### Controller Layer

Regarding the controller layer, there is only one controller: PokemonController. It has five endpoints. The first two return either all the PokémonDetails or a specified number of them (entered as a PathVariable). As for the other three methods, the return the heaviest and tallest Pokémon, as well as those with the highest base experience. They all take a PathVariable to specify how many Pokémon are to be retrieved. 

## Tests

In order to ensure the quality of the application, as well as provide extra documentation. This application has both unit and integration tests. As of now, all methods have been tested.

### Unit Tests

Unit tests have been written for all services using JUnit 5. In the case of PokemonDetailService, Mockito has been used to mock PokeApiResultService in order to limit the number of results retrieved.

### Integration Tests

Integration tests have been written for all methods in the PokemonController. MockMvc was used to call the endpoints, inspect and assert their response.

## Potential Improvements

Of course, there is always room for improvement when it comes to software applications. Here are some ideas that could make this application even better.

### Efficiency

As it is right now, getAllPokemonDetails() is really slow. This is because it has to call all 1281 Pokémon URLs and collect their results. Although its speed was significantly improved by using a parallel stream instead of a sequential one, it should be further improved.

### Error Handling

Customised exceptions should be created when the application doesn't work as expected. Furthermore, errors should be linked to specific error codes. Also, more edge cases would need to be checked. For example, what happens if a negative integer is entered as a limit?

### Pokémon forms

As previously explained, G-Max Pokémon were excluded when finding the heaviest Pokémon. Similarly, it should be analysed if different Pokémon forms (G-Max, Eternamax, Primal, regional forms, etc.) should count as different Pokémon or not, and how to deal with them.

### Constants in Unit Tests

More constants should be used in unit tests, e. g. when using Pokémon names repeatedly.

### Way Forward

Right now, the application focuses on a few Pokémon features. The Pokémon API, however, can offer much more than that: berries, games, items, locations, etc. The PokeApiUrl and PokeApiResult classes were designed with flexibility in mind, so they can be reused for other components of this API.
