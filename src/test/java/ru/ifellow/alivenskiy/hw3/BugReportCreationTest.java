package ru.ifellow.alivenskiy.hw3;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import ru.ifellow.alivenskiy.hw3.pages.*;
import ru.ifellow.alivenskiy.hw3.utils.ConfigReader;
import static com.codeborne.selenide.Selenide.*;

@Epic("Управление задачами JIRA")
@Feature("Создание баг-репортов")
@Owner("Alivenskiy")
@DisplayName("Тест создания баг-репорта")
public class BugReportCreationTest extends BaseTest {

    @Test
    @DisplayName("Создание баг-репорта с заполнением всех обязательных полей")
    @Story("Пользователь может создать баг-репорт с описанием ошибки")
    @Tag("Regression")
    @Tag("баг-репорт")
    public void canCreateBugReportFromTaskTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.logInAccount();
        DashboardPage dashboardPage = new DashboardPage();
        ProjectPage projectPage = dashboardPage.openProjectPage();
        int initialCount = projectPage.getTotalTasksCount();
        projectPage.clickCreateTask().createTask(ConfigReader.getTestTheme());
        refresh();
        projectPage.waitForTaskCount(initialCount + 1);
        int updatedCount = projectPage.getTotalTasksCount();
        Assertions.assertEquals(initialCount + 1, updatedCount);
        TaskPage taskPage = projectPage.openTask(ConfigReader.getTestTaskName());
        taskPage.verifyStatusAndVersion(ConfigReader.getExpectedStatus(), ConfigReader.getExpectedVersion());
        BugReportPage bugReportPage = taskPage.createBugReport();
        CreatedBugPage createdBugPage = bugReportPage.createBugReport(ConfigReader.getTestBugTheme(), ConfigReader.getTestBugType(), ConfigReader.getTestBugDescription());
        Assertions.assertTrue(createdBugPage.isPageLoaded());
    }
}