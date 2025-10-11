package ru.ifellow.alivenskiy.hw4.steps;


import io.cucumber.java.ru.*;
import org.junit.jupiter.api.Assertions;
import ru.ifellow.alivenskiy.hw3.pages.CreatedBugPage;

public class TaskWorkflowSteps {

    CreatedBugPage createdBugPage = new CreatedBugPage();
    @И("завершаем задачу")
    public void completeTask() {
        createdBugPage.completeTheTask();
    }

    @Тогда("статус задачи должен измениться на {string}")
    public void verifyTaskStatus(String expectedStatus) {
        String actualStatus = createdBugPage.getCurrentStatusOfTask();
        Assertions.assertEquals(expectedStatus, actualStatus);
    }
}
