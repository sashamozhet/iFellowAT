package ru.ifellow.alivenskiy.hw3.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    private final SelenideElement loginField = $x("//input[@id = 'login-form-username']").as("Поле ввода логина");
    private final SelenideElement passwordField = $x("//input[@id = 'login-form-password']").as("Поле ввода пароля");

    @Step("Ввести логин: {login}")
    public LoginPage enterLogin(String login) {
        loginField.should(visible).setValue(login);
        return this;
    }

    @Step("Ввести пароль")
    public LoginPage enterPassword(String password) {
        passwordField.should(visible).setValue(password);
        return this;
    }

    @Step("Нажать кнопку входа")
    public DashboardPage pressEnter() {
        passwordField.pressEnter();
        return new DashboardPage();
    }

    @Step("Выполнить вход с логином {login}")
    public DashboardPage logInAccount(String login, String password){
        enterLogin(login);
        enterPassword(password);
        return pressEnter();
    }
}