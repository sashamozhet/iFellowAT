package pages;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CreateTaskPage {
    private final String themeValue = "//input[@id = 'summary']";
    private final String createInput = "//input[@id = 'create-issue-submit']";
    private final String successMessage = "//a[@class = 'issue-created-key issue-link']";

    public ProjectPage createTask(String theme){
        $x(themeValue).setValue(theme);
        $x(createInput).click();
        $x(successMessage).shouldBe(visible, Duration.ofSeconds(10));
        return  new ProjectPage();
    }


}
