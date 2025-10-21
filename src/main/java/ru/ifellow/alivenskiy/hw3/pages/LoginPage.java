package ru.ifellow.alivenskiy.hw3.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.ifellow.alivenskiy.hw3.utils.ConfigReader;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    private final SelenideElement loginField = $x("//input[@id = 'login-form-username']").as("Поле ввода логина");
    private final SelenideElement passwordField = $x("//input[@id = 'login-form-password']").as("Поле ввода пароля");

    @Step("Ввести логин")
    public void enterLogin() {
        String login = ConfigReader.getLogin();
        loginField.should(visible).setValue(login);
    }

    @Step("Ввести пароль")
    public void enterPassword() {
        String password = ConfigReader.getPassword();
        passwordField.should(visible).setValue(password);
    }

    @Step("Нажать кнопку входа")
    public DashboardPage pressEnter() {
        passwordField.pressEnter();
        return new DashboardPage();
    }

    @Step("Выполнить вход в систему")
    public DashboardPage logInAccount(){
        enterLogin();
        enterPassword();
        return pressEnter();
    }
}