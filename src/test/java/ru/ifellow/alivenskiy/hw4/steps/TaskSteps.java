package ru.ifellow.alivenskiy.hw4.steps;

import io.cucumber.java.ru.*;
import ru.ifellow.alivenskiy.hw3.pages.ProjectPage;
import ru.ifellow.alivenskiy.hw3.pages.TaskPage;

public class TaskSteps {

    private TaskPage taskPage = new TaskPage();
    private ProjectPage projectPage = new ProjectPage();

    @И("открываем задачу {string}")
    public void openTask(String taskName){
        projectPage.openTask(taskName);
    }

    @Тогда("проверяем статус {string} и версию {string}")
    public void verifyStatusAndVersion(String status, String version) {
        taskPage.verifyStatusAndVersion(status, version);
    }
}