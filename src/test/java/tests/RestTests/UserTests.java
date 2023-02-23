package tests.RestTests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import actions.BaseRestActions;
import com.endava.models.CreatePersonRequest;
import com.endava.models.CreatePersonResponse;
import com.fasterxml.jackson.databind.ser.Serializers;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UserTests {


    private static BaseRestActions baseRestActions;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
        baseRestActions = new BaseRestActions();
    }

    @Test
    public void getSingleUser() {
        Response response = given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .get("/users/1")
                .then().log().all()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());

    }

    @Test
    public void getUnexistingUser() {
        given()
                .get("https://reqres.in/api/users/67")
                .then().log().body()
                .statusCode(404)
                .body("isEmpty()", Matchers.is(true));
    }

    @Test
    public void getListOfUsers() {
        given()
                .get("https://reqres.in/api/users?page=1")
                .then().log().body()
                .statusCode(200).assertThat()
                .body("per_page", equalTo(6));
    }

    @Test
    public void verifyFirstObjectMatchesExpected() {
        given().
                get("https://reqres.in/api/users?page=1")
                .then().log().body()
                .assertThat()
                .body("data[0].id", equalTo(1))
                .body("data[0].email", equalTo("george.bluth@reqres.in"))
                .body("data[0].first_name", equalTo("George"))
                .body("data[0].last_name", equalTo("Bluth"))
                .body("data[0].avatar", equalTo("https://reqres.in/img/faces/1-image.jpg"));
    }

    @Test
    public void createPersonRequest() {
        CreatePersonRequest personRequest = new CreatePersonRequest();
        personRequest.setName("Ema");
        personRequest.setJob("dentist");


        CreatePersonResponse response = given()
                .header("Content-type", "application/json")
                .and()
                .body(personRequest)
                .when()
                .post("/users")
                .then().log().body()
                .statusCode(201)
                .extract().response().as(CreatePersonResponse.class);

        Assertions.assertEquals("Ema", response.getName());
        Assertions.assertEquals("dentist", response.getJob());


    }

    @Test
    public void deletePerson() {
        CreatePersonResponse createdPerson = baseRestActions.createPerson("Anna", "dancer");
        given()
                .header("Content-type", "application/json")
                .when()
                .delete("/users/" + createdPerson.getId())
                .then().log().body()
                .statusCode(204);

    }
}
