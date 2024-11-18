package api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class AuthControllerTest {

    private final String BASE_URL = "http://localhost:8080/auth";

    @Test
    public void testUserRegistration() {
        String registrationBody = """
            {
                "username": "newUser",
                "email": "newUser@example.com",
                "password": "Password123"
            }
        """;

        RestAssured.given()
                .contentType("application/json")
                .body(registrationBody)
                .post(BASE_URL + "/register")
                .then()
                .statusCode(200)
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue());
    }

    @Test
    public void testUserLogin() {
        String loginBody = """
            {
                "username": "newUser",
                "password": "Password123"
            }
        """;

        RestAssured.given()
                .contentType("application/json")
                .body(loginBody)
                .post(BASE_URL + "/login")
                .then()
                .statusCode(200)
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue());
    }

    @Test
    public void testUserRegistrationWithExistingUsername() {
        String registrationBody = """
            {
                "username": "newUser",
                "email": "anotherEmail@example.com",
                "password": "Pass123"
            }
        """;

        RestAssured.given()
                .contentType("application/json")
                .body(registrationBody)
                .post(BASE_URL + "/register")
                .then()
                .statusCode(400); // Ожидаем ошибку при регистрации с уже существующим именем пользователя
    }

    @Test
    public void testUserLoginWithInvalidPassword() {
        String loginBody = """
            {
                "username": "newUser",
                "password": "wrongPassword"
            }
        """;

        RestAssured.given()
                .contentType("application/json")
                .body(loginBody)
                .post(BASE_URL + "/login")
                .then()
                .statusCode(401); // Ожидаем ошибку авторизации
    }
}

