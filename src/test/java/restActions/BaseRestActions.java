package restActions;

import com.endava.utils.PropertiesManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public abstract class BaseRestActions {
    private static final String API_BASE_URL = PropertiesManager.getProperties("apiBaseUri");

    protected RequestSpecification getRequestSpecification() {

        return RestAssured.given()
                .baseUri(API_BASE_URL)
                .log().all();

    }

    public Response get(String resource) {

        return getRequestSpecification()
                .when()
                .get(resource)
                .then()
                .log().body()
                .extract().response();

    }

    public Response post(Object requestBody, String resource) {
        return getRequestSpecification()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(resource)
                .then().log().body()
                .extract().response();

    }

    public Response put(Object requestBody, String resource) {
        return getRequestSpecification()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put(resource)
                .then().log().body()
                .extract().response();

    }

    public Response delete(String resource) {

        return getRequestSpecification()
                .when()
                .delete(resource)
                .then()
                .log().body()
                .extract().response();

    }
}
