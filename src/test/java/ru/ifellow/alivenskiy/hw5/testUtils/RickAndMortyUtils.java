package ru.ifellow.alivenskiy.hw5.testUtils;

import io.restassured.response.Response;
import ru.ifellow.alivenskiy.hw5.utils.TestConfig;
import java.util.List;
import static io.restassured.RestAssured.given;

public class RickAndMortyUtils {
    private static final String BASE_URL = TestConfig.getRickAndMortyUrl();

    public static Response getCharacter(int characterId) {
        return given()
                .baseUri(BASE_URL)
                .when()
                .get("/character/" + characterId)
                .then()
                .extract().response();
    }

    public static Response getEpisode(String episodeUrl) {
        return given()
                .when()
                .get(episodeUrl)
                .then()
                .extract().response();
    }

    public static List<String> getCharacterEpisodes(int characterId) {
        return getCharacter(characterId)
                .jsonPath()
                .getList("episode");
    }

    public static List<String> getEpisodeCharacters(String episodeUrl) {
        return getEpisode(episodeUrl)
                .jsonPath()
                .getList("characters");
    }

    public static String getCharacterSpecies(String characterUrl) {
        return given()
                .when()
                .get(characterUrl)
                .then()
                .extract().jsonPath().getString("species");
    }

    public static String getCharacterLocation(String characterUrl) {
        return given()
                .when()
                .get(characterUrl)
                .then()
                .extract().jsonPath().getString("location.name");
    }
}