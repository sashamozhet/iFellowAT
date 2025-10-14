package ru.ifellow.alivenskiy.hw3;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import ru.ifellow.alivenskiy.hw3.pages.DashboardPage;
import ru.ifellow.alivenskiy.hw3.pages.LoginPage;
import ru.ifellow.alivenskiy.hw3.pages.ProjectPage;
import ru.ifellow.alivenskiy.hw3.pages.TaskPage;
import static com.codeborne.selenide.Selenide.*;

public class TaskStatusVersionTest {
    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://edujira.ifellow.ru";
        Configuration.timeout = 10000;
        open("/");
        webdriver().driver().getWebDriver().manage().window().maximize();
    }
    @AfterEach
    void tearDown() {
        closeWebDriver();
    }
    @Test
    @DisplayName("Тест на проверку статуса и версии задачи без её изменений")
    public void taskStatusAndVersionAreCorrectTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.logInAccount("AT5", "Qwerty123");
        DashboardPage dashboardPage = new DashboardPage();
        ProjectPage projectPage = dashboardPage.openProjectPage();
        int initialCount = projectPage.getTotalTasksCount();
        projectPage.clickCreateTask().createTask("Тест_aliv");
        refresh();
        projectPage.waitForTaskCount(initialCount + 1);
        int updatedCount = projectPage.getTotalTasksCount();
        Assertions.assertEquals(initialCount + 1, updatedCount);
        TaskPage taskPage = projectPage.openTask("TestSeleniumATHomework");
        taskPage.verifyStatusAndVersion("Сделать", "Version 2.0");
    }
}
