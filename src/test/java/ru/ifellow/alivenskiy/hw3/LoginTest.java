package ru.ifellow.alivenskiy.hw3;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import ru.ifellow.alivenskiy.hw3.pages.DashboardPage;
import ru.ifellow.alivenskiy.hw3.pages.LoginPage;

@Epic("Авторизация и безопасность")
@Feature("Вход в систему")
@Owner("Alivenskiy")
@DisplayName("Тест авторизации")
public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Успешная авторизация с корректными учетными данными")
    @Story("Пользователь может войти в систему с валидным логином и паролем")
    @Tag("Smoke")
    @Tag("Аутентификация")
    public void userCanLoginSuccessfullyTest() {
        LoginPage loginPage = new LoginPage();
        DashboardPage dashboardPage = loginPage.logInAccount();
        Assertions.assertTrue(dashboardPage.isProjectMenuDisplayed());
    }
}