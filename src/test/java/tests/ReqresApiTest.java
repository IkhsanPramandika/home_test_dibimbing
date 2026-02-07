package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ConfigReader;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ReqresApiTest {
    private String userId;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigReader.getProperty("apiBaseUrl");
        RestAssured.requestSpecification = given()
                .header("x-api-key", ConfigReader.getProperty("apiKey"))
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) Chrome/120.0.0.0");
    }

    @Test(priority = 1)
    public void testCreateUser() {
        String requestBody = "{\"name\": \"Ikhsan\", \"job\": \"QA Automation\"}";

        Response response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .log().all()
                .statusCode(201)
                .extract().response();

//        userId = response.path("id");
        userId = "2";
        System.out.println("ID User yang baru dibuat adalah: " + userId);
    }

    @Test(priority = 2, dependsOnMethods = "testCreateUser")
    public void testGetSingleUser() {
        given()
                .log().all()
                .when()
                .get("/users/" + userId)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test(priority = 3, dependsOnMethods = "testCreateUser")
    public void testUpdateUser() {
        String requestBody = "{\"name\": \"Ikhsan\", \"job\": \"Senior Automation\"}";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/users/" + userId)
                .then()
                .log().all()
                .statusCode(200)
                .body("job", equalTo("Senior Automation"));
    }

    @Test(priority = 4, dependsOnMethods = "testCreateUser")
    public void testDeleteUser() {
        given()
                .log().all()
                .when()
                .delete("/users/" + userId)
                .then()
                .log().all()
                .statusCode(204);
    }
}