package ru.ifellow.alivenskiy.hw3.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import java.time.Duration;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class CreatedBugPage {
    private final SelenideElement selectStatusLink = $x("//a[@id='opsbar-transitions_more']").as("Выпадающее меню Бизнес-процесс");
    private final SelenideElement doneLink = $x("//aui-item-link[@id='action_id_31']").as("Вариант ВЫПОЛНЕНО в списке Бизнец-процесс");
    private final SelenideElement statusOfTheCreatedTask = $x("//span[@id='status-val']").as("Статус созданной задачи");

    @Step("Получить статус задачи")
    public String getCurrentStatusOfTask(){
        statusOfTheCreatedTask.shouldHave(text("ГОТОВО"), Duration.ofSeconds(5));
        return statusOfTheCreatedTask.shouldBe(visible).getText().trim();
    }

    @Step("Поставить задаче статус Выполнено")
    public void completeTheTask(){
        selectStatusLink.should(visible, Duration.ofSeconds(5)).click();
        doneLink.shouldBe(visible, Duration.ofSeconds(5)).click();
    }

    @Step("Проверить, что страница загрузилась")
    public boolean isPageLoaded() {
        return statusOfTheCreatedTask.shouldBe(visible).isDisplayed();
    }
}