package ru.ifellow.alivenskiy.hw3.pages;

import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ProjectPage {
    private final SelenideElement projectsCount = $x("//div[@class = 'showing']//parent::span").as("Общее количество задач");
    private final SelenideElement createTaskLink = $x("//a[@id = 'create_link']").as("Кнопка создания задачи");
    private final SelenideElement quickSearchInput = $x("//input[@id = 'quickSearchInput']").as("Поле ввода для поиска");

    public int getTotalTasksCount(){
        String text = projectsCount.getText();
        String parts = text.split(" ")[text.split(" ").length - 1];
        int countOfTasks = Integer.parseInt(parts);
        return  countOfTasks;
    }
    public void waitForTaskCount(int expectedCount){
        String expectedText = String.valueOf(expectedCount);
        projectsCount.shouldHave(text(expectedText), Duration.ofSeconds(10));

    }
    public CreateTaskPage clickCreateTask(){
        createTaskLink.shouldBe(visible).click();
        return  new CreateTaskPage();
    }
    public TaskPage openTask(String projectName){
        quickSearchInput.click();
        quickSearchInput.setValue(projectName).pressEnter();
        return new TaskPage();
    }


}