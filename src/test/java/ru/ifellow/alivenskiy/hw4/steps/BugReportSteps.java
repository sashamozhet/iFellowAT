package ru.ifellow.alivenskiy.hw4.steps;

import io.cucumber.java.ru.*;
import ru.ifellow.alivenskiy.hw3.pages.BugReportPage;
import ru.ifellow.alivenskiy.hw3.pages.CreatedBugPage;
import ru.ifellow.alivenskiy.hw3.pages.TaskPage;

public class BugReportSteps {

    private BugReportPage bugReportPage = new BugReportPage();
    private CreatedBugPage createdBugPage = new CreatedBugPage();
    private TaskPage taskPage = new TaskPage();

    @И("открываем меню для создания баг-репорта")
    public void createBugReportButton(){
        taskPage.createBugReport();
    }
    @И("создаём баг-репорт с темой {string}, типом {string} и описанием {string}")
    public void createBugReport(String theme, String type, String description) {
        bugReportPage.createBugReport(theme, type, description);
    }

    @Тогда("баг репорт успешно создан")
    public void isBugReportCreated() {
        createdBugPage.isPageLoaded();
    }

}