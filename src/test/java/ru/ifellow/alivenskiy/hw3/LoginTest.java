package ru.ifellow.alivenskiy.hw3;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.ifellow.alivenskiy.hw3.pages.DashboardPage;
import ru.ifellow.alivenskiy.hw3.pages.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;

public class LoginTest {
    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://edujira.ifellow.ru";
        open("/");
        webdriver().driver().getWebDriver().manage().window().maximize();
        LoginPage loginPage = new LoginPage();
        loginPage.logInAccount("AT5", "Qwerty123");
    }
    @Test
    public void userCanLoginSuccessfullyTest() {
        LoginPage loginPage = new LoginPage();
        DashboardPage dashboardPage = loginPage.logInAccount("AT5", "Qwerty123");
       // Assertions.assertTrue(dashboardPage.isDashboardDisplayed());
    }
}