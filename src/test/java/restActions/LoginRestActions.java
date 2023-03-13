package restActions;

import io.restassured.response.Response;

public class LoginRestActions extends BaseRestActions {

    public Response sendLoginRequest(Object credentials) {

        return post(credentials, "login");

    }

    public Response sendUnsuccessfulLoginRequest(Object credentials) {
        return post(credentials, "login");

    }
}
