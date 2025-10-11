package ru.ifellow.alivenskiy.hw4.hooks;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import com.codeborne.selenide.Configuration;
import io.cucumber.java.Before;

import static com.codeborne.selenide.Selenide.*;

public class Hooks {

    @Before
    public static void setUp() {
        Configuration.baseUrl = "https://edujira.ifellow.ru";
        Configuration.timeout = 10000;
        open("/");
        webdriver().driver().getWebDriver().manage().window().maximize();
    }
    @After
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}