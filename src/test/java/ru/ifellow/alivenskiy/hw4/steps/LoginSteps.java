package ru.ifellow.alivenskiy.hw4.steps;

import io.cucumber.java.ru.*;
import ru.ifellow.alivenskiy.hw3.pages.DashboardPage;
import ru.ifellow.alivenskiy.hw3.pages.LoginPage;
import static com.codeborne.selenide.Selenide.*;


public class LoginSteps {

    private LoginPage loginPage = new LoginPage();
    private DashboardPage dashboardPage = new DashboardPage();

    @Когда("пользователь открывает страницу логина")
    public void openLoginPage() {
        open("/");
    }

    @И("вводит логин {string} и пароль {string}")
    public void enterCredentials(String login, String password) {
        loginPage.logInAccount(login, password);
    }

    @Тогда("отображается меню проектов")
    public void checkProjectMenu() {
        dashboardPage.isProjectMenuDisplayed();
    }
}