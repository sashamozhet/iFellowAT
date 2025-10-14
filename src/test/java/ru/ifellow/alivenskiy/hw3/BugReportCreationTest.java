package ru.ifellow.alivenskiy.hw3;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import ru.ifellow.alivenskiy.hw3.pages.*;
import ru.ifellow.alivenskiy.hw3.utils.ConfigReader;

import static com.codeborne.selenide.Selenide.*;

public class BugReportCreationTest {
    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = ConfigReader.getBaseUrl();
        Configuration.timeout = ConfigReader.getTimeout();
        open("/");
        webdriver().driver().getWebDriver().manage().window().maximize();
    }
    @AfterEach
    void tearDown() {
        closeWebDriver();
    }
    @Test
    @DisplayName("Тест на создание баг-репорта с заполнением полей")
    public void canCreateBugReportFromTaskTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.logInAccount(ConfigReader.getLogin(), ConfigReader.getPassword());
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