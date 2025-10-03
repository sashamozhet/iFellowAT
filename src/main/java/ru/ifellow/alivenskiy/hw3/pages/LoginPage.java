package ru.ifellow.alivenskiy.hw3.pages;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    private final SelenideElement loginField = $x("//input[@id = 'login-form-username']");
    private final SelenideElement passwordField = $x("//input[@id = 'login-form-password']");

    public DashboardPage logInAccount(String login, String password){
        loginField.setValue(login);
        passwordField.setValue(password).pressEnter();
        return new DashboardPage();
    }
}
