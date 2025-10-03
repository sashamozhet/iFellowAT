package ru.ifellow.alivenskiy.hw3;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.ifellow.alivenskiy.hw3.pages.*;

import static com.codeborne.selenide.Selenide.*;

public class TaskStatusVersionTest {
    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://edujira.ifellow.ru";
        Configuration.timeout = 10000;
        open("/");
        webdriver().driver().getWebDriver().manage().window().maximize();
    }
    @Test
    public void canCreateBugReportFromTaskTest() {
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
        BugReportPage bugReportPage = taskPage.createBugReport();
        bugReportPage.createBugReport("Test123_aliv60", "Ошибка", "abs");

    }
}