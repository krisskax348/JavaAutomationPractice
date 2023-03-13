package restActions;

import com.endava.models.CreatePersonRequest;
import io.restassured.response.Response;


public class UserRestActions extends BaseRestActions {

    public Response getUser(String id) {
        return get(id);
    }

    public Response getListOfUsers(String pageNumber) {
        return get("users?page=" + pageNumber);
    }


    public Response deleteExistingUser(int id) {
        return delete("users/" + id);
    }

    public Response createNewPerson(Object person) {
        return post(person, "users");
    }

    public Response updateCreatedPerson(int createdPersonId, CreatePersonRequest updatedPersonRequest){

        return put(updatedPersonRequest, "users/" + createdPersonId);
    }

}