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

    public void getResource(String resource, int responseCode) {

        getRequestSpecification()
                .contentType(ContentType.JSON)
                .when()
                .get(resource)
                .then()
                .log().body()
                .statusCode(responseCode);

    }

    public Response postResource(Object requestBody, String resource, int responseCode) {
        return getRequestSpecification()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(resource)
                .then().log().body()
                .statusCode(responseCode)
                .extract().response();

    }
    public Response putResource(Object requestBody, String resource, int responseCode) {
        return getRequestSpecification()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put(resource)
                .then().log().body()
                .statusCode(responseCode)
                .extract().response();

    }
    public void deleteResource(String resource, int responseCode) {

        getRequestSpecification()
                .contentType(ContentType.JSON)
                .when()
                .delete(resource)
                .then()
                .log().body()
                .statusCode(responseCode);

    }

}
