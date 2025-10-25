package ru.ifellow.alivenskiy.hw5.testUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ru.ifellow.alivenskiy.hw5.NewUser;
import ru.ifellow.alivenskiy.hw5.utils.TestConfig;
import static io.restassured.RestAssured.*;

public class ApiUtils {
    private static final String BASE_URL = TestConfig.getBaseUrl();

    public static Response registerUser(NewUser user) {
        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .baseUri(BASE_URL)
                .when()
                .post("api/register")
                .then()
                .extract().response();
    }

    public static Response loginUser(NewUser user) {
        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .baseUri(BASE_URL)
                .when()
                .post("api/login")
                .then()
                .extract().response();
    }

    public static Response logoutUser(String token) {
        return given()
                .header("Authorization", token)
                .baseUri(BASE_URL)
                .when()
                .get("api/logout")
                .then()
                .extract().response();
    }

    public static String loginAndGetToken(NewUser user) {
        Response response = loginUser(user);
        if (response.statusCode() == 200) {
            return response.getBody().asString().replace("token : ", "");
        }
        return null;
    }
}