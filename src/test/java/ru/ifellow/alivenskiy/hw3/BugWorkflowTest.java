package ru.ifellow.alivenskiy.hw3;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import ru.ifellow.alivenskiy.hw3.pages.*;
import ru.ifellow.alivenskiy.hw3.utils.ConfigReader;
import static com.codeborne.selenide.Selenide.*;

@Epic("Управление задачами JIRA")
@Feature("Жизненный цикл баг-репортов")
@Owner("Alivenskiy")
@DisplayName("Тест жизненного цикла бага")
public class BugWorkflowTest extends BaseTest {

    @Test
    @DisplayName("Workflow баг-репорта:  Готово")
    @Story("Ставим баг-репорту статус Готово")
    @Tag("Smoke")
    @Tag("Workflow")
    public void bugCanBeMovedToDoneStatusTest() {
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
        createdBugPage.completeTheTask();
        String actualStatus = createdBugPage.getCurrentStatusOfTask();
        Assertions.assertEquals(ConfigReader.getExpectedDoneStatus(), actualStatus);
    }
}