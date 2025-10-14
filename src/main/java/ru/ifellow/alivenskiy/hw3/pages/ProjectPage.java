package ru.ifellow.alivenskiy.hw3.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ProjectPage {
    private final SelenideElement projectsCount = $x("//div[@class = 'showing']//parent::span").as("Общее количество задач");
    private final SelenideElement createTaskLink = $x("//a[@id = 'create_link']").as("Кнопка создания задачи");
    private final SelenideElement quickSearchInput = $x("//input[@id = 'quickSearchInput']").as("Поле ввода для поиска");

    @Step("Получить общее количество задач")
    public int getTotalTasksCount(){
        String text = projectsCount.getText();
        String parts = text.split(" ")[text.split(" ").length - 1];
        int countOfTasks = Integer.parseInt(parts);
        return countOfTasks;
    }

    @Step("Дождаться появления количества задач: {expectedCount}")
    public void waitForTaskCount(int expectedCount){
        String expectedText = String.valueOf(expectedCount);
        projectsCount.shouldHave(text(expectedText), Duration.ofSeconds(10));
    }

    @Step("Нажать кнопку создания задачи")
    public CreateTaskPage clickCreateTask(){
        createTaskLink.shouldBe(visible).click();
        return new CreateTaskPage();
    }

    @Step("Открыть задачу: {projectName}")
    public TaskPage openTask(String projectName){
        quickSearchInput.click();
        quickSearchInput.setValue(projectName).pressEnter();
        return new TaskPage();
    }
}