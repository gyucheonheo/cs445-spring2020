package Controller.Account;

import Boundary.Account.AccountInteractorCommandBoundary;
import Boundary.Account.AccountInteractorQueryBoundary;
import Boundary.AccountInteractorBoundary;
import Controller.ErrorHandler.ErrorMessage;
import Controller.Account.Validator.AccountValidator;
import Entity.Boundary.Account.User.User;
import Entity.Bounded.Account.CellPhoneFormat.BoundedCellPhoneFormat;
import Interactor.Account.AccountInteractor;
import Interactor.Account.AccountInteractorCommand;
import Interactor.Account.AccountInteractorQuery;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Path("/accounts")
public class AccountCommandController {
    private AccountInteractorQueryBoundary acc_query_i = AccountInteractorQuery.INSTANCE;
    private AccountInteractorCommandBoundary acc_command_i = AccountInteractorCommand.INSTANCE;
    private ErrorMessage error = new ErrorMessage();
    private Gson gson = new Gson();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(String json) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        if(!AccountValidator.isValid(jsonObject)){
            JsonObject err = error.createError(AccountValidator.getErrorMessage(), 400, "/accounts");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(err)).build();
        }

        String firstName = jsonObject.get("first_name").getAsString();
        String lastName = jsonObject.get("last_name").getAsString();
        String phoneNumber = jsonObject.get("phone").getAsString();
        String picture = jsonObject.get("picture").getAsString();

        List<String> numbers = Arrays.asList(phoneNumber.split("-"));
        User u = acc_query_i.createUser(firstName, lastName, BoundedCellPhoneFormat.Make(numbers.get(0), numbers.get(1), numbers.get(2)), picture);
        acc_command_i.registerUser(u);
        JsonObject result = new JsonObject();
        result.addProperty("aid", u.getAid());

        return Response.status(Response.Status.CREATED).entity(gson.toJson(result)).build();
    }

    @Path("{aid}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAccount(@PathParam("aid") String aid, String json) {
        User u = acc_query_i.getUserById(aid);
        if (u.isNil()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        if(!AccountValidator.isValid(jsonObject)){
            JsonObject err = error.createError(AccountValidator.getErrorMessage(), 400, "/accounts/"+aid);
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(err)).build();
        }
        String firstName = jsonObject.get("first_name").getAsString();
        String lastName = jsonObject.get("last_name").getAsString();
        String phoneNumber = jsonObject.get("phone").getAsString();
        List<String> numbers = Arrays.asList(phoneNumber.split("-"));
        String picture = jsonObject.get("picture").getAsString();

        acc_command_i.updateUser(aid, firstName, lastName, BoundedCellPhoneFormat.Make(numbers.get(0), numbers.get(1), numbers.get(2)), picture);

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Path("{aid}/status")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response activateAccount(@PathParam("aid") String aid, String json) {
        User u = acc_query_i.getUserById(aid);
        if (u.isNil()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        if(!AccountValidator.isActivateInfoValid(jsonObject)){
            JsonObject err = error.createError(AccountValidator.getErrorMessage(), 400, "/accounts/"+aid+"/status");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(err)).build();
        }

        acc_command_i.activateUser(aid);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Path("{aid}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAccount(@PathParam("aid") String aid) {
        User u = acc_query_i.getUserById(aid);
        if (u.isNil()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        acc_command_i.deleteUser(aid);
        return Response.status(Response.Status.NO_CONTENT).build();
    }


}
