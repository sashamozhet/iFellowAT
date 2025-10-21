package ru.ifellow.alivenskiy.hw3;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import ru.ifellow.alivenskiy.hw3.pages.DashboardPage;
import ru.ifellow.alivenskiy.hw3.pages.LoginPage;
import ru.ifellow.alivenskiy.hw3.pages.ProjectPage;
import ru.ifellow.alivenskiy.hw3.pages.TaskPage;
import ru.ifellow.alivenskiy.hw3.utils.ConfigReader;
import static com.codeborne.selenide.Selenide.*;

@Epic("Управление задачами JIRA")
@Feature("Атрибуты задач")
@Owner("Alivenskiy")
@DisplayName("Тест статуса и версии задачи")
public class TaskStatusVersionTest extends BaseTest {

    @Test
    @DisplayName("Проверка корректности статуса и версии задачи")
    @Story("Необходимо проверить, что версия и статус задачи верные")
    @Tag("Regression")
    @Tag("Валидация")
    public void taskStatusAndVersionAreCorrectTest() {
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
    }
}
