package restActions;

import com.endava.models.CreatePersonRequest;
import com.endava.models.CreatePersonResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;


public class UserRestActions extends BaseRestActions {

    public void getListOfUsers() {
        getRequestSpecification()
                .param("page", "1")
                .get("users")
                .then().log().body()
                .statusCode(200).assertThat()
                .body("per_page", equalTo(6));
    }

    public void getUnexistingUser() {
        getRequestSpecification()
                .get("users/67")
                .then().log().body()
                .statusCode(404)
                .body("isEmpty()", Matchers.is(true));
    }
    public Response deleteUserRequest(String name, String job) {
        CreatePersonRequest personRequest = new CreatePersonRequest();
        personRequest.setName(name);
        personRequest.setJob(job);
        CreatePersonResponse createdPerson = createPersonRequest(personRequest);

        return given()
                .contentType(ContentType.JSON)
                .when()
                .delete("users/" + createdPerson.getId())
                .then().log().all()
                .statusCode(204)
                .extract().response();
    }

    public CreatePersonResponse createPersonRequest(Object person) {
          return given()
                .contentType(ContentType.JSON)
                .and()
                .body(person)
                .when()
                .post("users")
                .then()
                .statusCode(201)
                .extract().response().as(CreatePersonResponse.class);

    }

    public void firstObjectValidation() {
        getRequestSpecification()
                .param("page", "1")
                .get("users")
                .then().log().body()
                .assertThat()
                .body("data[0].id", equalTo(1))
                .body("data[0].email", equalTo("george.bluth@reqres.in"))
                .body("data[0].first_name", equalTo("George"))
                .body("data[0].last_name", equalTo("Bluth"))
                .body("data[0].avatar", equalTo("https://reqres.in/img/faces/1-image.jpg"));
    }
}