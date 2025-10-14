package ru.ifellow.alivenskiy.hw3;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.ifellow.alivenskiy.hw3.pages.DashboardPage;
import ru.ifellow.alivenskiy.hw3.pages.LoginPage;

import static com.codeborne.selenide.Selenide.*;

public class LoginTest {
    @BeforeAll
    static void setUp() {

        Configuration.baseUrl = "https://edujira.ifellow.ru";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
    }
    @AfterEach
    void tearDown() {
        closeWebDriver();
    }
    @Test
    @DisplayName("Тест на успешную авторизацию")
    public void userCanLoginSuccessfullyTest() {
        open("/");
        webdriver().driver().getWebDriver().manage().window().maximize();
        LoginPage loginPage = new LoginPage();
        DashboardPage dashboardPage = loginPage.logInAccount("AT5", "Qwerty123");
        Assertions.assertTrue(dashboardPage.isProjectMenuDisplayed());
    }
}