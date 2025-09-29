package pages;

import org.openqa.selenium.Keys;
import java.time.Duration;
import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class BugReportPage {

    private final String bugInput = "//input[@id = 'issuetype-field']";
    private final String themeValue = "//input[@id = 'summary']";
    private final String visualButtonsOn = "//button[text()='Визуальный']";
  //  private final String version = "//select[@id='fixVersions']/optgroup/option[1]";
    private final String descriptionFrame  = "//iframe[starts-with(@id,'mce_')]";
    private final String bodyFrame = "//body[@id='tinymce']";

   private void fillDescription(String text) {
       switchTo().frame($x(descriptionFrame).shouldBe(visible));
       $x(bodyFrame).shouldBe(visible);
       $x(bodyFrame).should(appear, Duration.ofSeconds(20)).setValue(text);
       switchTo().defaultContent();
   }
   private void waitVisualModeButtons(){
       $$x(visualButtonsOn).shouldHave(size(2), Duration.ofSeconds(10));
       if (!$$x(visualButtonsOn).get(0).has(attribute("aria-pressed", "true"))) {
           $$x(visualButtonsOn).get(0).click();
       }
       if (!$$x(visualButtonsOn).get(1).has(attribute("aria-pressed", "true"))) {
           $$x(visualButtonsOn).get(1).click();
       }

   }

    public void createBugReport(String theme, String tasksType, String descriptionValue){
        $x(bugInput).doubleClick();
        $x(bugInput).sendKeys(Keys.BACK_SPACE);
        $x(bugInput).setValue(tasksType);
        $x(bugInput).pressEnter();
        waitVisualModeButtons();
        for (int i = 0; i < visualButtonsOn.length(); i++) {
            $x(visualButtonsOn).click();
        }
        fillDescription(descriptionValue);
        $x(themeValue).setValue(theme).pressEnter();
    }


}
