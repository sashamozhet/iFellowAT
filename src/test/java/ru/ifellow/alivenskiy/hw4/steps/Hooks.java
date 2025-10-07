package ru.ifellow.alivenskiy.hw4.steps;

import io.cucumber.java.Before;
import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Selenide.*;

public class Hooks {

    @Before
    public static void setUp() {
        Configuration.baseUrl = "https://edujira.ifellow.ru";
        Configuration.timeout = 10000;
        open("/");
        webdriver().driver().getWebDriver().manage().window().maximize();
    }
}