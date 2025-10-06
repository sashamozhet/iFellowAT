package ru.ifellow.alivenskiy.hw5;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.ifellow.alivenskiy.hw5.Specifications.Specifications;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class AuthTest {
    File body = new File("src/test/java/resources/userInfo.json");
    private static final String URL = "http://localhost:8080/";
    private static String authToken;

    @Test
    public void fullAuthenticationTest() throws IOException {
        //Step 1.0
        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("api/register")
                .then().log().all()
                .statusCode(200)
                .extract().response();
        System.out.println(1);

        //Step 2.a
        ObjectMapper mapper = new ObjectMapper();
        NewUser userFromFile = mapper.readValue(body, NewUser.class);
        userFromFile.setUsername("Tommy");
        String actualUserName = given()
                .contentType(ContentType.JSON)
                .body(userFromFile)
                .when()
                .post("api/login")
                .then().log().all()
                .statusCode(401)
                .extract().response().getBody().asString();
        Assertions.assertEquals("not found", actualUserName);

        //Step 2.b
        userFromFile.setUsername("Alex");
        userFromFile.setPassword("123asd");
        String actualPassword = given()
                .contentType(ContentType.JSON)
                .body(userFromFile)
                .when()
                .post("api/login")
                .then().log().all()
                .statusCode(401)
                .extract().response().getBody().asString();
        Assertions.assertEquals("not right pass", actualPassword);

        //Step 2.c
      authToken=   given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("api/login")
                .then().log().all()
                .statusCode(200)
                .extract().response().getBody().asString().replace("token : ", "");

        //Step 3.a
        String unSuccesToken = given()
                .header("Authorization", "12345678-1234-1234-1234-123456789abc")
                .when()
                .get("api/logout")
                .then().log().all()
                .statusCode(401)
                .extract().response().getBody().asString();
        Assertions.assertEquals("not found", unSuccesToken);

        //Step 3.b
        String succesToken = given()
                .header("Authorization", authToken)
                .when()
                .get("api/logout")
                .then().log().all()
                .statusCode(200)
                .extract().response().getBody().asString();
        Assertions.assertEquals("success logout", succesToken);




    }

}
