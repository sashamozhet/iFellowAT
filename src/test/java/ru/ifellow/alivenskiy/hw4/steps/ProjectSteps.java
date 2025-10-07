package ru.ifellow.alivenskiy.hw4.steps;

import io.cucumber.java.ru.*;
import ru.ifellow.alivenskiy.hw3.pages.DashboardPage;
import ru.ifellow.alivenskiy.hw3.pages.ProjectPage;
import ru.ifellow.alivenskiy.hw3.pages.CreateTaskPage;

public class ProjectSteps {

    private DashboardPage dashboardPage = new DashboardPage();
    private ProjectPage projectPage = new ProjectPage();
    private CreateTaskPage createTaskPage = new CreateTaskPage();

    @И("пользователь открывает страницу проекта Test")
    public void openProjectPage() {
        dashboardPage.openProjectPage();
    }

    @И("получает общее количество открытых задач")
    public int getTasksCount() {
        return projectPage.getTotalTasksCount();
    }

    @И("создаёт задачу с темой {string}")
    public void createTask(String theme) {
        projectPage.clickCreateTask().createTask(theme);
    }

    @Тогда("счетчик задач должен обновиться на {int}")
    public void verifyTaskCount(int expectedCount) {
        projectPage.waitForTaskCount(expectedCount);
    }
}