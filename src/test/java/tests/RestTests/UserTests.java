package tests.RestTests;

import com.endava.models.CreatePersonRequest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restActions.UserRestActions;

public class UserTests {

    private UserRestActions userRestActions;


    @BeforeEach
    public void setup() {
        userRestActions = new UserRestActions();
    }

    @Test
    public void verifyExistingUserRequest() {
        Response response = userRestActions.getUser("1");
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    public void verifyNonExistingUserRequest() {
        Response response = userRestActions.getUser("67");
        Assertions.assertEquals(404, response.statusCode());
        Assertions.assertTrue(response.body().asString().contains("{}"));
    }

    @Test
    public void verifyListOfUsersRequest() {
        Response response = userRestActions.getListOfUsers("1");
        Assertions.assertEquals(200, response.statusCode());

        JsonPath jsonPath = response.jsonPath();

        Assertions.assertEquals(6, jsonPath.getInt("per_page"));
    }

    @Test
    public void verifyLastUserCreatedMatchesExpected() {
        Response response = userRestActions.getListOfUsers("1");
        Assertions.assertEquals(200, response.statusCode());

        Assertions.assertEquals(1, response.jsonPath().getInt("data[0].id"));
        Assertions.assertEquals("george.bluth@reqres.in", response.jsonPath().getString("data[0].email"));
        Assertions.assertEquals("George", response.jsonPath().getString("data[0].first_name"));
        Assertions.assertEquals("Bluth", response.jsonPath().getString("data[0].last_name"));
    }

    @Test
    public void verifySuccessfulCreatePersonRequest() {
        CreatePersonRequest personRequest = new CreatePersonRequest();
        personRequest.setName("Ema");
        personRequest.setJob("dentist");

        Response createdPerson = userRestActions.createNewPerson(personRequest);
        JsonPath jsonPath = createdPerson.jsonPath();

        Assertions.assertEquals("Ema", jsonPath.getString("name"));
        Assertions.assertEquals("dentist", jsonPath.getString("job"));
    }

    @Test
    public void verifySuccessfulUpdatePersonRequest() {
        CreatePersonRequest newPersonRequest = new CreatePersonRequest();
        newPersonRequest.setName("Ema");
        newPersonRequest.setJob("singer");
        Response createdPersonResponse = userRestActions.createNewPerson(newPersonRequest);
        int createdPersonId = createdPersonResponse.jsonPath().getInt("id");

        CreatePersonRequest updatedPersonRequest = new CreatePersonRequest();
        updatedPersonRequest.setName("Diana");
        updatedPersonRequest.setJob("singer");

        Response updatedPersonResponse = userRestActions.updateCreatedPerson(createdPersonId, updatedPersonRequest);
        Assertions.assertEquals(200, updatedPersonResponse.statusCode());
        Assertions.assertEquals("Diana", updatedPersonResponse.jsonPath().getString("name"));
        Assertions.assertEquals("singer", updatedPersonResponse.jsonPath().getString("job"));
    }

    @Test
    public void verifySuccessfulDeletePersonRequest() {
        CreatePersonRequest newPersonRequest = new CreatePersonRequest();
        newPersonRequest.setName("Emily");
        newPersonRequest.setJob("singer");
        Response createdPersonResponse = userRestActions.createNewPerson(newPersonRequest);
        int createdPersonId = createdPersonResponse.jsonPath().getInt("id");

        Response response = userRestActions.deleteExistingUser(createdPersonId);
        Assertions.assertEquals(204, response.statusCode());
        Assertions.assertTrue(response.body().asString().isBlank());
    }
}
