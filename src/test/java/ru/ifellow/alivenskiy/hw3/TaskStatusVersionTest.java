package ru.ifellow.alivenskiy.hw3;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import ru.ifellow.alivenskiy.hw3.pages.DashboardPage;
import ru.ifellow.alivenskiy.hw3.pages.LoginPage;
import ru.ifellow.alivenskiy.hw3.pages.ProjectPage;
import ru.ifellow.alivenskiy.hw3.pages.TaskPage;
import ru.ifellow.alivenskiy.hw3.utils.ConfigReader;

import static com.codeborne.selenide.Selenide.*;

public class TaskStatusVersionTest {
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
    @DisplayName("Тест на проверку статуса и версии задачи без её изменений")
    public void taskStatusAndVersionAreCorrectTest() {
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
    }
}
