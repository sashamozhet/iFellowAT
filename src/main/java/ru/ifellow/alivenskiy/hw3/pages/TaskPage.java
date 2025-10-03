package ru.ifellow.alivenskiy.hw3.pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TaskPage {
    private final SelenideElement status = $x("//span[@id = 'status-val']");
    private final SelenideElement version = $x("//span[@id = 'versions-field']");
    private final SelenideElement createTaskLink = $x("//a[@id = 'create_link']");

    public void verifyStatusAndVersion(String expectedStatus, String expectedVersion){
        status.shouldHave(text(expectedStatus), Duration.ofSeconds(10));
        version.shouldHave(text(expectedVersion), Duration.ofSeconds(10));
    }
    public BugReportPage createBugReport(){
        createTaskLink.click();
        return new BugReportPage();
    }

}
