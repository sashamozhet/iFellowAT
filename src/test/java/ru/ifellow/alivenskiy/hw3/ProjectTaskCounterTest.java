package ru.ifellow.alivenskiy.hw3;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import ru.ifellow.alivenskiy.hw3.pages.DashboardPage;
import ru.ifellow.alivenskiy.hw3.pages.LoginPage;
import ru.ifellow.alivenskiy.hw3.pages.ProjectPage;
import ru.ifellow.alivenskiy.hw3.utils.ConfigReader;
import static com.codeborne.selenide.Selenide.*;

@Epic("Управление задачами JIRA")
@Feature("Статистика проекта")
@Owner("Alivenskiy")
@DisplayName("Тесты счётчика задач проектов")
public class ProjectTaskCounterTest extends BaseTest {

    @Test
    @DisplayName("Счетчик задач увеличивается при создании новой задачи")
    @Story("Система корректно отслеживает количество задач в проекте")
    @Tag("Regression")
    @Tag("Статистика")
    public void taskCounterIncrementsAfterCreatingNewTaskTest() {
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
    }
}