package ru.ifellow.alivenskiy.hw3;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import ru.ifellow.alivenskiy.hw3.utils.ConfigReader;
import static com.codeborne.selenide.Selenide.*;

public class BaseTest {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = ConfigReader.getBaseUrl();
        Configuration.timeout = ConfigReader.getTimeout();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(ConfigReader.isAllureScreenshotsEnabled())
                .savePageSource(ConfigReader.isAllurePageSourceEnabled())
                .includeSelenideSteps(ConfigReader.isAllureSelenideStepsEnabled()));
        open("/");
        webdriver().driver().getWebDriver().manage().window().maximize();
    }
    @AfterEach
    void tearDown() {
        closeWebDriver();
    }
}