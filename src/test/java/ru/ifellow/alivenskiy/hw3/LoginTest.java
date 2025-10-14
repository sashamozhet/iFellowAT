package ru.ifellow.alivenskiy.hw3;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.ifellow.alivenskiy.hw3.pages.DashboardPage;
import ru.ifellow.alivenskiy.hw3.pages.LoginPage;
import ru.ifellow.alivenskiy.hw3.utils.ConfigReader;

import static com.codeborne.selenide.Selenide.*;

public class LoginTest {
    @BeforeAll
    static void setUp() {

        Configuration.baseUrl = ConfigReader.getBaseUrl();
        Configuration.timeout = ConfigReader.getTimeout();
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
        DashboardPage dashboardPage = loginPage.logInAccount(ConfigReader.getLogin(), ConfigReader.getPassword());
        Assertions.assertTrue(dashboardPage.isProjectMenuDisplayed());
    }
}