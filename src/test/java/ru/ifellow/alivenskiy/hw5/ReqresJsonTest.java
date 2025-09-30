package ru.ifellow.alivenskiy.hw5;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.ifellow.alivenskiy.hw5.Specifications.Specifications;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static io.restassured.RestAssured.given;

public class ReqresJsonTest {
    private static final String URL = "https://reqres.in/";
    @Test
    public void createUserFromJsonFile() throws IOException {
        Specifications.installSpecification(Specifications.requestSpecification(URL),
                Specifications.responseSpecUnique(201));
        File file = new File("src/test/java/resources/user-data.json");
        String fileContent = Files.readString(file.toPath());
        ObjectMapper mapper = new ObjectMapper();
        User userFromFile = mapper.readValue(fileContent, User.class);
        userFromFile.setName("Tomato");
        userFromFile.setJob("Eat maket");
        Response response = given()
                .contentType(ContentType.JSON)
                .body(userFromFile)
                .header("x-api-key","reqres-free-v1")
                .when()
                .post("api/users")
                .then().log().all()
                .extract().response();
        System.out.println(response);
       Assertions.assertEquals("Tomato", response.jsonPath().getString("name"));
       Assertions.assertEquals("Eat maket", response.jsonPath().getString("job"));



    }
}
