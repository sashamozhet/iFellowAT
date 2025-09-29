package ru.ifellow.alivenskiy.hw3.pages;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    private final String loginField = "//input[@id = 'login-form-username']";
    private final String passwordField = "//input[@id = 'login-form-password']";

    public DashboardPage logInAccount(String login, String password){
        $x(loginField).setValue(login);
        $x(passwordField).setValue(password).pressEnter();
        return new DashboardPage();
    }
}
