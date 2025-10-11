package ru.ifellow.alivenskiy.hw3.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private final SelenideElement projectsMenu = $x("//a[@id = 'browse_link']").as("меню проекты на дашборде");
    private final SelenideElement testProjectLink = $x("//a[@id = 'admin_main_proj_link_lnk']").as("проект TEST в выпадающем списке Проекты");
    private final SelenideElement projectMenu = $x("//header[@id='header']").as("Header при входе в личный кабинет");

    public ProjectPage openProjectPage(){
        projectsMenu.shouldBe(visible).click();
        testProjectLink.click();
        return new ProjectPage();
    }
    public boolean isProjectMenuDisplayed(){
        return projectMenu.shouldBe(visible).isDisplayed();
    }
}