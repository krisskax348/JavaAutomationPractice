package restActions;

import io.restassured.response.Response;

public class LoginRestActions extends BaseRestActions {

    public Response sendLoginRequest(Object credentials) {

        return postResource(credentials, "login", 200);

    }

    public Response sendUnsuccessfulLoginRequest(Object credentials) {
        return postResource(credentials, "login", 400);

    }
}
