package ru.ifellow.alivenskiy.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.ifellow.alivenskiy.hw5.Specifications.Specifications;
import java.util.List;
import static io.restassured.RestAssured.*;

public class RickAndMortyTests {
    private static final String URL = "https://rickandmortyapi.com/api";

    @Test
    public void getMortySInfoFromLastEpisode(){
        Specifications.installSpecification(Specifications.requestSpecification(URL)
        ,Specifications.responseSpecOK200());

      List<String> episodes =
              given()
                .when()
                .get("/character/2")
                .then().log().all()
              .extract().jsonPath().getList("episode");
      String lastEpisode = episodes.get(episodes.size() - 1);

      List<String> charactersInEpisode = given()
              .when()
              .get(lastEpisode)
              .then().log().all()
              .extract().jsonPath().getList("characters");
      String lastCharacter = charactersInEpisode.get(charactersInEpisode.size() - 1);

        String lastCharacterSpecies = given()
                .when()
                .get(lastCharacter)
                .then().log().all()
                .extract().jsonPath().getString("species");

        String lastCharacterLocation = given()
                .when()
                .get(lastCharacter)
                .then().log().all()
                .extract().jsonPath().getString("location.name");

        String mortySpecies = given()
                .when()
                .get("/character/2")
                .then().log().all()
                .extract().jsonPath().getString("species");

        String mortyLocation = given()
                .when()
                .get("/character/2")
                .then().log().all()
                .extract().jsonPath().getString("location.name");

        boolean differentSpecies = !mortySpecies.equals(lastCharacterSpecies);
        boolean differentLocation = !mortyLocation.equals(lastCharacterLocation);
        Assertions.assertTrue(differentLocation || differentSpecies);
    }
}
