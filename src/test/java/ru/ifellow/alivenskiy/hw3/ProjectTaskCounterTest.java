package ru.ifellow.alivenskiy.hw3;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.ifellow.alivenskiy.hw3.pages.DashboardPage;
import ru.ifellow.alivenskiy.hw3.pages.LoginPage;
import ru.ifellow.alivenskiy.hw3.pages.ProjectPage;
import static com.codeborne.selenide.Selenide.*;

public class ProjectTaskCounterTest {
    @BeforeAll
     static void setUp() {
        Configuration.baseUrl = "https://edujira.ifellow.ru";
        open("/");
        webdriver().driver().getWebDriver().manage().window().maximize();
    }
    @Test
    @DisplayName("Тест на увеличение количества задач на 1 после создания новой")
    public void taskCounterIncrementsAfterCreatingNewTaskTest() {
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
    }
}