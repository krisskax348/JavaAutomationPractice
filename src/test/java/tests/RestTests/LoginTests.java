package tests.RestTests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class LoginTests {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    public void verifySuccessfulLogin() {
        JSONObject jsonObj = new JSONObject()
                .put("email", "eve.holt@reqres.in")
                .put("password", "cityslicka");

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(jsonObj.toString())
                .when()
                .post("/login")
                .then().log().body()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        JsonPath jsonPathEvaluator = response.jsonPath();
        String token = jsonPathEvaluator.get("token");
    }

    @Test
    public void verifyUnsuccessfulLogin() {
        JSONObject jsonObj = new JSONObject()
                .put("email", "eve.holt@reqRes.in")
                .put("password", "");

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(jsonObj.toString())
                .when()
                .post("/login")
                .then().log().body()
                .extract().response();

        Assertions.assertEquals(400, response.statusCode());
        Assertions.assertEquals("Missing password", response.jsonPath().getString("error"));
    }
}
