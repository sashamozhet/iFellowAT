package ru.ifellow.alivenskiy.hw5.hooks;

import io.cucumber.java.Before;
import ru.ifellow.alivenskiy.hw5.Specifications.Specifications;
import ru.ifellow.alivenskiy.hw5.utils.TestConfig;

public class ApiHooks {

    @Before("@rickandmorty")
    public void setUpRickAndMorty() {
        Specifications.installSpecification(
                Specifications.requestSpecification(TestConfig.getRickAndMortyUrl()),
                Specifications.responseSpecOK200()
        );
    }
}