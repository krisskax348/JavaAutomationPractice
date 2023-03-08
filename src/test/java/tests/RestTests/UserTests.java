package tests.RestTests;


import com.endava.models.CreatePersonRequest;
import io.restassured.response.Response;
import restActions.BaseRestActions;
import restActions.UserRestActions;
import com.endava.models.CreatePersonResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTests {

    private  UserRestActions userRestActions;


    @BeforeEach
    public void setup() {
        userRestActions = new UserRestActions();
    }

    @Test
    public void getSingleUser() {
     userRestActions.getResource("1", 200);
    }

    @Test
    public void verifyUnexistingUserRequest() {
        userRestActions.getUnexistingUser();
    }

    @Test
    public void verifyListOfUsers() {
        userRestActions.getListOfUsers();
    }

    @Test
    public void verifyFirstObjectMatchesExpected() {
        userRestActions.firstObjectValidation();
    }

    @Test
    public void createPersonRequest() {
        CreatePersonRequest personRequest = new CreatePersonRequest();
        personRequest.setName("Ema");
        personRequest.setJob("job");

        CreatePersonResponse createdPerson = userRestActions.createPersonRequest(personRequest);
        Assertions.assertEquals("Ema", createdPerson.getName());
        Assertions.assertEquals("dentist", createdPerson.getJob());

    }

    @Test
    public void deletePerson() {

        Response response = userRestActions.deleteUserRequest("Emma", "dentist");
        Assertions.assertTrue(response.body().asString().isBlank());

    }
}
