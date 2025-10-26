package ru.ifellow.alivenskiy.hw5.steps;

import io.cucumber.java.ru.*;
import io.restassured.response.Response;
import ru.ifellow.alivenskiy.hw5.testUtils.RickAndMortyUtils;
import java.util.List;
import io.qameta.allure.Step;
import static org.junit.jupiter.api.Assertions.*;

public class RickAndMortyTestSteps {

    private List<String> mortyEpisodes;
    private String lastEpisodeUrl;
    private List<String> episodeCharacters;
    private String lastCharacterUrl;
    private String mortySpecies;
    private String mortyLocation;
    private String lastCharSpecies;
    private String lastCharLocation;


    @Step("Получаем информацию о персонаже Морти")
    @Когда("получаем информацию о Морти")
    public void getMortyInfo() {
        Response mortyResponse = RickAndMortyUtils.getCharacter(2);
        mortySpecies = mortyResponse.jsonPath().getString("species");
        mortyLocation = mortyResponse.jsonPath().getString("location.name");
    }


    @Step("Получаем последний эпизод Морти")
    @И("получаем последний эпизод Морти")
    public void getLastEpisode() {
        mortyEpisodes = RickAndMortyUtils.getCharacterEpisodes(2);
        lastEpisodeUrl = mortyEpisodes.get(mortyEpisodes.size() - 1);
    }


    @Step("Получаем последнего персонажа из эпизода")
    @И("получаем последнего персонажа из этого эпизода")
    public void getLastCharacterFromEpisode() {
        episodeCharacters = RickAndMortyUtils.getEpisodeCharacters(lastEpisodeUrl);
        lastCharacterUrl = episodeCharacters.get(episodeCharacters.size() - 1);
    }


    @Step("Получаем информацию о последнем персонаже")
    @И("получаем информацию о последнем персонаже")
    public void getLastCharacterInfo() {
        lastCharSpecies = RickAndMortyUtils.getCharacterSpecies(lastCharacterUrl);
        lastCharLocation = RickAndMortyUtils.getCharacterLocation(lastCharacterUrl);
    }


    @Step("Сравниваем виды и местоположения Морти и последнего персонажа")
    @Тогда("сравниваем виды и местоположения Морти и последнего персонажа")
    public void compareSpeciesAndLocations() {
        boolean speciesDifferent = !mortySpecies.equals(lastCharSpecies);
        boolean locationDifferent = !mortyLocation.equals(lastCharLocation);
        assertTrue(speciesDifferent || locationDifferent);
    }
}