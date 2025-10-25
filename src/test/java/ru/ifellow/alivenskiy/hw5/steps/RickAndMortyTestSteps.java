package ru.ifellow.alivenskiy.hw5.steps;

import io.cucumber.java.ru.Тогда;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.И;
import ru.ifellow.alivenskiy.hw5.Specifications.Specifications;
import ru.ifellow.alivenskiy.hw5.testUtils.RickAndMortyUtils;
import ru.ifellow.alivenskiy.hw5.utils.TestConfig;
import java.util.List;

// ⬇️⬇️⬇️ ДОБАВИЛ ЭТОТ ИМПОРТ ⬇️⬇️⬇️
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

    // ⬇️⬇️⬇️ ДОБАВИЛ АННОТАЦИЮ @Step ⬇️⬇️⬇️
    @Step("Получаем информацию о персонаже Морти")
    @Когда("получаем информацию о Морти")
    public void getMortyInfo() {
        Specifications.installSpecification(
                Specifications.requestSpecification(TestConfig.getRickAndMortyUrl()),
                Specifications.responseSpecOK200()
        );

        mortySpecies = RickAndMortyUtils.getCharacterSpecies("/character/2");
        mortyLocation = RickAndMortyUtils.getCharacterLocation("/character/2");
    }

    // ⬇️⬇️⬇️ ДОБАВИЛ АННОТАЦИЮ @Step ⬇️⬇️⬇️
    @Step("Получаем последний эпизод Морти")
    @И("получаем последний эпизод Морти")
    public void getLastEpisode() {
        mortyEpisodes = RickAndMortyUtils.getCharacterEpisodes(2);
        lastEpisodeUrl = mortyEpisodes.get(mortyEpisodes.size() - 1);
    }

    // ⬇️⬇️⬇️ ДОБАВИЛ АННОТАЦИЮ @Step ⬇️⬇️⬇️
    @Step("Получаем последнего персонажа из эпизода")
    @И("получаем последнего персонажа из этого эпизода")
    public void getLastCharacterFromEpisode() {
        episodeCharacters = RickAndMortyUtils.getEpisodeCharacters(lastEpisodeUrl);
        lastCharacterUrl = episodeCharacters.get(episodeCharacters.size() - 1);
    }

    // ⬇️⬇️⬇️ ДОБАВИЛ АННОТАЦИЮ @Step ⬇️⬇️⬇️
    @Step("Получаем информацию о последнем персонаже")
    @И("получаем информацию о последнем персонаже")
    public void getLastCharacterInfo() {
        lastCharSpecies = RickAndMortyUtils.getCharacterSpecies(lastCharacterUrl);
        lastCharLocation = RickAndMortyUtils.getCharacterLocation(lastCharacterUrl);
    }

    // ⬇️⬇️⬇️ ДОБАВИЛ АННОТАЦИЮ @Step ⬇️⬇️⬇️
    @Step("Сравниваем виды и местоположения Морти и последнего персонажа")
    @Тогда("сравниваем виды и местоположения Морти и последнего персонажа")
    public void compareSpeciesAndLocations() {
        boolean speciesDifferent = !mortySpecies.equals(lastCharSpecies);
        boolean locationDifferent = !mortyLocation.equals(lastCharLocation);
        assertTrue(speciesDifferent || locationDifferent);
    }
}