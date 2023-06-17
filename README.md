# Alea Knowledge Test

For this assignment, an application has created to expose an API with the following scenarios:

1. The 5 heaviest Pokémons.
2. The 5 tallest Pokémons.
3. The 5 Pokémons with the highest base experience.

In order to do so, a Spring RESTful application has been created. This application consists of a model layer, with three classes: PokeApiUrl, containing a list of PokeApiResult, which contains the name and the url of a single result, and PokemonDetail, which contains the relevant details of a Pokémon for our application. It also contains an Enum, PokeApiUrlEnum, for the different types of results that can be fetched from the API (berries, Pokémon, generations...) This is to ensure a valid URL is used.

Similarly, the application has a service layer, with Service classes for all the entities. PokeApiUrlService contains two methods to retrieve the results from a PokeApiUrlEnum. Through method overloading, one can choose whether to retrieve all the results or a specific number.
