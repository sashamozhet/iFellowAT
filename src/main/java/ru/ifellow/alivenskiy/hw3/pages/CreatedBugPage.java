package ru.ifellow.alivenskiy.hw3.pages;

import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class CreatedBugPage {
    private final SelenideElement selectStatusLink = $x("//a[@id='opsbar-transitions_more']");
    private final SelenideElement doneLink = $x("//aui-item-link[@id='action_id_31']");
    private final SelenideElement statusOfTheCreatedTask = $x("//span[@id='status-val']");


public String getCurrentStatusOfTask(){
    statusOfTheCreatedTask.shouldHave(text("ГОТОВО"), Duration.ofSeconds(5));
    return statusOfTheCreatedTask.shouldBe(visible).getText().trim();
}
    public void completeTheTask(){
        selectStatusLink.should(visible, Duration.ofSeconds(5)).click();
        doneLink.shouldBe(visible, Duration.ofSeconds(5)).click();
    }
    public boolean isPageLoaded() {
        return statusOfTheCreatedTask.isDisplayed();
    }
}
