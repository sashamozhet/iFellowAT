package ru.ifellow.alivenskiy.hw3.pages;

import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CreateTaskPage {
    private final SelenideElement themeValue = $x("//input[@id = 'summary']").as("Название задачи");
    private final SelenideElement createInput = $x("//input[@id = 'create-issue-submit']").as("Кнопка создать в меню создания задачи");
    private final SelenideElement successMessage = $x("//a[@class = 'issue-created-key issue-link']").as("Всплывающее окно при создании задачи");

    public ProjectPage createTask(String theme){
        themeValue.setValue(theme);
        createInput.click();
        successMessage.shouldBe(visible, Duration.ofSeconds(10));
        return  new ProjectPage();
    }


}