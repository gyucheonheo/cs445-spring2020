package Controller.Rate;

import Boundary.Rate.RateInteractorCommandBoundary;
import Boundary.Rate.RateInteractorQueryBoundary;
import Boundary.RateInteractorBoundary;
import Controller.ErrorHandler.ErrorMessage;
import Controller.Rate.Validator.RateValidator;
import Entity.Boundary.Account.User.RideInformation.Rate.Rate;
import Interactor.Rate.RateInteractor;
import Interactor.Rate.RateInteractorCommand;
import Interactor.Rate.RateInteractorQuery;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/accounts")
public class RateCommandController {
    private RateInteractorQueryBoundary rate_query_i = RateInteractorQuery.INSTANCE;
    private RateInteractorCommandBoundary rate_command_i = RateInteractorCommand.INSTANCE;
    private Gson gson = new Gson();
    private ErrorMessage error = new ErrorMessage();

    @Path("{aid}/ratings")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response rateAccount(@PathParam("aid") String aid, String json){
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

        if(!RateValidator.isValid(jsonObject)){
            JsonObject err = error.createError(RateValidator.getErrorMessage(), 400, "/accounts/"+aid+"/ratings");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(err)).build();
        }

        String rid = jsonObject.get("rid").getAsString();
        String sent_by_id = jsonObject.get("sent_by_id").getAsString();
        int rating = jsonObject.get("rating").getAsInt();
        String comment = jsonObject.get("comment").getAsString();

        Rate r = rate_query_i.createRate(rid, sent_by_id, rating,comment);
        rate_command_i.rateAccount(rid, r);
        JsonObject result = new JsonObject();
        result.addProperty("sid", r.getRid());
        return Response.status(Response.Status.CREATED).entity(gson.toJson(result)).header("Location", "Location").build();
    }

}
