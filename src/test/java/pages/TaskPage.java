package pages;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TaskPage {
    private final String status = "//span[@id = 'status-val']";
    private final String version = "//span[@id = 'versions-field']";
    private final String createTaskLink = "//a[@id = 'create_link']";

    public void verifyStatusAndVersion(String expectedStatus, String expectedVersion){
        $x(status).shouldHave(text(expectedStatus), Duration.ofSeconds(10));
        $x(version).shouldHave(text(expectedVersion), Duration.ofSeconds(10));
    }
    public BugReportPage createBugReport(){
        $x(createTaskLink).click();
        return new BugReportPage();
    }

}
