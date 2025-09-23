package tests;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.*;

import static com.codeborne.selenide.Selenide.*;

public class ProjectDashboardTest {

    @BeforeAll
    static void setUp(){
        Configuration.baseUrl = "https://edujira.ifellow.ru";
    }

@Test
    public void taskCounterIncrementsAfterCreatingNewTaskTest(){
    open("/");
    webdriver().driver().getWebDriver().manage().window().maximize();
    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = loginPage.logInAccount("AT5", "Qwerty123");
    ProjectPage projectPage = dashboardPage.openProjectPage();
    int initialCount = projectPage.getTotalTasksCount();
    projectPage = projectPage.clickCreateTask().createTask("Тест_aliv");
    refresh();
    projectPage.waitForTaskCount(initialCount +1);
    int updCount = projectPage.getTotalTasksCount();
    Assertions.assertEquals(initialCount +1, updCount);

    TaskPage taskPage = projectPage.openTask("TestSeleniumATHomework");
    taskPage.verifyStatusAndVersion("Сделать", "Version 2.0");
    BugReportPage bugReportPage = taskPage.createBugReport();
    bugReportPage.createBugReport("Test123_aliv55", "Ошибка", "abs");
    }

}
