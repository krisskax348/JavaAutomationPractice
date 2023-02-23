package actions;

import com.endava.models.CreatePersonRequest;
import com.endava.models.CreatePersonResponse;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class BaseRestActions {
    public static void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    public CreatePersonResponse createPerson(String name, String job) {
        CreatePersonRequest personRequest = new CreatePersonRequest();
        personRequest.setName(name);
        personRequest.setJob(job);


        CreatePersonResponse response = given()
                .header("Content-type", "application/json")
                .and()
                .body(personRequest)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract().response().as(CreatePersonResponse.class);

        return response;
    }
}
