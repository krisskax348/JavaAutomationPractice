package tests.RestTests;

import com.endava.models.LoginCredentials;
import restActions.LoginRestActions;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTests {
    private LoginRestActions loginRestActions;


    @BeforeEach
    public void setup() {
        loginRestActions = new LoginRestActions();
    }

    @Test
    public void verifySuccessfulLogin() {
        LoginCredentials credentials = new LoginCredentials();
        credentials.setEmail("eve.holt@reqres.in");
        credentials.setPassword("cityslicka");
        Response response = loginRestActions.sendLoginRequest(credentials);

        Assertions.assertTrue(response.body().asString().contains("token"));
    }

    @Test
    public void verifyUnsuccessfulLogin() {
        LoginCredentials credentials = new LoginCredentials();
        credentials.setEmail("eve.holt@reqres.in");
        credentials.setPassword("");
        Response response = loginRestActions.sendUnsuccessfulLoginRequest(credentials);

        Assertions.assertEquals(400, response.statusCode());
        Assertions.assertEquals("Missing password", response.jsonPath().getString("error"));
    }
}
