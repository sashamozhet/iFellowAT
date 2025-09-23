package pages;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ProjectPage {
    private final String projectsCount = "//div[@class = 'showing']//parent::span";
    private final String createTaskLink = "//a[@id = 'create_link']";
    private final String quickSearchInput = "//input[@id = 'quickSearchInput']";

    public int getTotalTasksCount(){
        String text = $x(projectsCount).getText();
        String parts = text.split(" ")[text.split(" ").length - 1];
        int countOfTasks = Integer.parseInt(parts);
        return  countOfTasks;
    }
    public void waitForTaskCount(int expectedCount){
        String expectedText = String.valueOf(expectedCount);
        $x(projectsCount).shouldHave(text(expectedText), Duration.ofSeconds(10));

    }
    public CreateTaskPage clickCreateTask(){
        $x(createTaskLink).shouldBe(visible).click();
        return  new CreateTaskPage();
    }
    public TaskPage openTask(String projectName){
        $x(quickSearchInput).click();
        $x(quickSearchInput).setValue(projectName).pressEnter();
        return new TaskPage();
    }


}
