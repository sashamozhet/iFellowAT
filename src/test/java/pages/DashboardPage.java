package pages;

import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private final String projectsMenu = "//a[@id = 'browse_link']";
    private final String testProjectLink = "//a[@id = 'admin_main_proj_link_lnk']";

    public ProjectPage openProjectPage(){
        $x(projectsMenu).click();
        $x(testProjectLink).click();
        return new ProjectPage();
    }
}
