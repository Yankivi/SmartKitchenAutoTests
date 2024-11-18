package api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class EquipmentControllerTest {

    private final String BASE_URL = "http://localhost:8080/api/equipments";
    private final String BEARER_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxIiwidXNlcm5hbWUiOiJ2aWthIiwiYXV0aG9yaXRpZXMiOlsidXNlciJdLCJlbWFpbCI6bnVsbCwiaWF0IjoxNzMxODg3MzU2LCJleHAiOjE3MzE4ODc2NTZ9.abm49yjY-V8VyBFLBv5_5twH9k4Tth57lNQ_iMH_7lM\",\n" +
            "  \"refreshToken\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxIiwidXNlcm5hbWUiOiJ2aWthIiwiYXV0aG9yaXRpZXMiOlsidXNlciJdLCJlbWFpbCI6bnVsbCwiaWF0IjoxNzMxODg3MzU2LCJleHAiOjE3MzE5ODczNTZ9.m__5scPz_FhVAoPX_46HqqGvyeiGueyxJugF0iRfF7Y";

    @Test
    public void testCreateEquipment() {
        String newEquipment = """
        {
            "id": 1,
            "name": "Fork",
            "categoryId": 1
        }
    """;

        RestAssured.given()
                .header("Authorization", "Bearer " + BEARER_TOKEN)
                .contentType("application/json")
                .body(newEquipment)
                .post(BASE_URL)
                .then()
                .statusCode(200)
                .body("name", equalTo("Fork"))
                .body("categoryId", equalTo(1));
    }

    @Test
    public void testGetAllEquipments() {
        RestAssured.given()
                .header("Authorization", "Bearer " + BEARER_TOKEN)
                .get(BASE_URL + "/all")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void testGetEquipmentById() {
        long equipmentId = 1;

        RestAssured.given()
                .header("Authorization", "Bearer " + BEARER_TOKEN)
                .get(BASE_URL + "/" + equipmentId)
                .then()
                .statusCode(200)
                .body("id", equalTo((int) equipmentId))
                .body("name", equalTo("Fork"))
                .body("categoryId", equalTo(1));
    }

    @Test
    public void testUpdateEquipment() {
        long equipmentId = 1; // ID существующего объекта
        String updatedEquipment = """
        {
            "id": 1,
            "name": "Fork",
            "categoryId": 2
        }
    """;

        RestAssured.given()
                .header("Authorization", "Bearer " + BEARER_TOKEN)
                .contentType("application/json")
                .body(updatedEquipment)
                .put(BASE_URL + "/" + equipmentId)
                .then()
                .statusCode(200)
                .body("name", equalTo("Fork"))
                .body("categoryId", equalTo(2));
    }

    @Test
    public void testDeleteEquipment() {
        long equipmentId = 1;

        RestAssured.given()
                .header("Authorization", "Bearer " + BEARER_TOKEN)
                .delete(BASE_URL + "/" + equipmentId)
                .then()
                .statusCode(204); // 204 No Content
    }

    @Test
    public void testGetDeletedEquipment() {
        long equipmentId = 1;

        RestAssured.given()
                .header("Authorization", "Bearer " + BEARER_TOKEN)
                .get(BASE_URL + "/" + equipmentId)
                .then()
                .statusCode(404); // 404 Not Found
    }


}



