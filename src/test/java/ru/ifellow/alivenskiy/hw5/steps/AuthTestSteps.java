package ru.ifellow.alivenskiy.hw5.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.ru.*;
import io.restassured.response.Response;
import ru.ifellow.alivenskiy.hw5.NewUser;
import ru.ifellow.alivenskiy.hw5.testUtils.ApiUtils;
import java.io.File;
import java.io.IOException;

// ⬇️⬇️⬇️ ДОБАВИЛ ЭТОТ ИМПОРТ ⬇️⬇️⬇️
import io.qameta.allure.Step;

import static org.junit.jupiter.api.Assertions.*;

public class AuthTestSteps {

    private NewUser userFromFile;
    private String authToken;
    private Response response;
    private ObjectMapper mapper = new ObjectMapper();
    private File body = new File("src/test/resources/userInfo.json");

    // ⬇️⬇️⬇️ ДОБАВИЛ АННОТАЦИЮ @Step ⬇️⬇️⬇️
    @Step("Загружаем данные пользователя из файла")
    @Дано("пользователь имеет данные для аутентификации")
    public void loadUserCredentials() throws IOException {
        userFromFile = mapper.readValue(body, NewUser.class);
    }

    // ⬇️⬇️⬇️ ДОБАВИЛ АННОТАЦИЮ @Step ⬇️⬇️⬇️
    @Step("Регистрируем пользователя с данными из файла")
    @Когда("пользователь регистрируется с данными из файла")
    public void registerUser() {
        response = ApiUtils.registerUser(userFromFile);
    }

    // ⬇️⬇️⬇️ ДОБАВИЛ АННОТАЦИЮ @Step ⬇️⬇️⬇️
    @Step("Проверяем успешность регистрации")
    @Тогда("регистрация должна быть успешной")
    public void verifyRegistration() {
        assertEquals(200, response.statusCode());
    }

    // ⬇️⬇️⬇️ ДОБАВИЛ АННОТАЦИЮ @Step ⬇️⬇️⬇️
    @Step("Пытаемся войти с неверным именем: {username}")
    @Когда("пользователь пытается войти с неверным именем {string}")
    public void loginWithInvalidUsername(String username) {
        NewUser invalidUser = new NewUser(username, userFromFile.getPassword());
        response = ApiUtils.loginUser(invalidUser);
    }

    // ⬇️⬇️⬇️ ДОБАВИЛ АННОТАЦИЮ @Step ⬇️⬇️⬇️
    @Step("Проверяем ошибку входа: {expectedError}")
    @Тогда("вход должен завершиться ошибкой {string}")
    public void verifyLoginError(String expectedError) {
        assertEquals(401, response.statusCode());
        assertEquals(expectedError, response.getBody().asString());
    }

    // ⬇️⬇️⬇️ ДОБАВИЛ АННОТАЦИЮ @Step ⬇️⬇️⬇️
    @Step("Пытаемся войти с неверным паролем")
    @Когда("пользователь пытается войти с неверным паролем {string}")
    public void loginWithInvalidPassword(String password) {
        NewUser invalidUser = new NewUser(userFromFile.getUsername(), password);
        response = ApiUtils.loginUser(invalidUser);
    }

    // ⬇️⬇️⬇️ ДОБАВИЛ АННОТАЦИЮ @Step ⬇️⬇️⬇️
    @Step("Входим с верными данными")
    @Когда("пользователь входит с верными данными")
    public void loginWithValidCredentials() {
        response = ApiUtils.loginUser(userFromFile);
    }

    // ⬇️⬇️⬇️ ДОБАВИЛ АННОТАЦИЮ @Step ⬇️⬇️⬇️
    @Step("Проверяем успешный вход и получение токена")
    @Тогда("вход должен быть успешным и возвращен токен")
    public void verifySuccessfulLogin() {
        assertEquals(200, response.statusCode());
        authToken = response.getBody().asString().replace("token : ", "");
        assertNotNull(authToken, "Auth token should not be null");
    }

    // ⬇️⬇️⬇️ ДОБАВИЛ АННОТАЦИЮ @Step ⬇️⬇️⬇️
    @Step("Пытаемся выйти с неверным токеном")
    @Когда("пользователь пытается выйти с неверным токеном")
    public void logoutWithInvalidToken() {
        response = ApiUtils.logoutUser("12345678-1234-1234-1234-123456789abc");
    }

    // ⬇️⬇️⬇️ ДОБАВИЛ АННОТАЦИЮ @Step ⬇️⬇️⬇️
    @Step("Проверяем ошибку выхода: {expectedError}")
    @Тогда("выход должен завершиться ошибкой {string}")
    public void verifyLogoutError(String expectedError) {
        assertEquals(401, response.statusCode());
        assertEquals(expectedError, response.getBody().asString());
    }

    // ⬇️⬇️⬇️ ДОБАВИЛ АННОТАЦИЮ @Step ⬇️⬇️⬇️
    @Step("Выходим с верным токеном")
    @Когда("пользователь выходит с верным токеном")
    public void logoutWithValidToken() {
        response = ApiUtils.logoutUser(authToken);
    }

    // ⬇️⬇️⬇️ ДОБАВИЛ АННОТАЦИЮ @Step ⬇️⬇️⬇️
    @Step("Проверяем успешный выход")
    @Тогда("выход должен быть успешным")
    public void verifySuccessfulLogout() {
        assertEquals(200, response.statusCode());
        assertEquals("success logout", response.getBody().asString());
    }
}