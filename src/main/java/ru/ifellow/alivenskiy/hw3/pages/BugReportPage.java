package ru.ifellow.alivenskiy.hw3.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import java.time.Duration;
import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class BugReportPage {

    private final SelenideElement bugInput = $x("//input[@id = 'issuetype-field']");
    private final SelenideElement themeValue = $x("//input[@id = 'summary']");
    private final ElementsCollection visualButtonsOn = $$x("//button[text()='Визуальный']") ;
    private final String editFrames  = "//iframe[starts-with(@id,'mce_')]";
    private final String bodyFrame = "//body[@id='tinymce']";

    private void fillBothDescriptions(String text) {
        ElementsCollection editorFrames = $$x(editFrames).shouldHave(sizeGreaterThan(0), Duration.ofSeconds(10));
        switchTo().frame(editorFrames.get(0).shouldBe(visible));
        $x(bodyFrame).shouldBe(visible, Duration.ofSeconds(10)).setValue(text);
        switchTo().defaultContent();
        switchTo().frame(editorFrames.get(1).shouldBe(visible));
        $x(bodyFrame).shouldBe(visible, Duration.ofSeconds(5)).shouldBe(editable).setValue(text);
        switchTo().defaultContent();
    }
    private void waitVisualModeButtons(){
        visualButtonsOn.shouldHave(size(2), Duration.ofSeconds(10));
        if (!visualButtonsOn.get(0).has(attribute("aria-pressed", "true"))) {
            visualButtonsOn.get(0).click();
        }
        if (!visualButtonsOn.get(1).has(attribute("aria-pressed", "true"))) {
            visualButtonsOn.get(1).click();
        }

    }

    public void createBugReport(String theme, String tasksType, String descriptionValue){
        bugInput.doubleClick();
        bugInput.sendKeys(Keys.BACK_SPACE);
        bugInput.setValue(tasksType);
        bugInput.pressEnter();
        waitVisualModeButtons();
        fillBothDescriptions(descriptionValue);
        themeValue.setValue(theme).pressEnter();
    }


}
