package ru.ifellow.alivenskiy.hw3.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    private final SelenideElement loginField = $x("//input[@id = 'login-form-username']").as("Поле ввода логина");
    private final SelenideElement passwordField = $x("//input[@id = 'login-form-password']").as("Поле ввода пароля");


    public DashboardPage logInAccount(String login, String password){
        loginField.should(visible).setValue(login);
        passwordField.should(visible).setValue(password).pressEnter();
        return new DashboardPage();
    }


}
