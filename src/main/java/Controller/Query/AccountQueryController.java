package Controller.Query;

import Boundary.AccountInteractorBoundary;
import Boundary.RateInteractorBoundary;
import Boundary.RideRequestInteractorBoundary;
import Entity.Boundary.Account.User.RideInformation.Rate.Rate;
import Entity.Boundary.Account.User.User;
import Interactor.AccountInteractor;
import Interactor.RateInteractor;
import Interactor.RideRequestInteractor;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.util.List;

@Path("accounts")
public class AccountQueryController {
    private Gson gson = new Gson();
    private AccountInteractorBoundary ai = AccountInteractor.INSTANCE;
    private RateInteractorBoundary ri = RateInteractor.INSTANCE;
    private RideRequestInteractorBoundary rideI = RideRequestInteractor.INSTANCE;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewAllAccount(@Context UriInfo uriInfo){
        MultivaluedMap<String, String> parameters = uriInfo.getQueryParameters();
        String key = parameters.getFirst("key");
        List<User> users = ai.getAllUsers(key);
        JsonArray result = new JsonArray();
        for(User u : users){
            JsonObject singleUser = new JsonObject();
            singleUser.addProperty("aid", u.getAid());
            singleUser.addProperty("name", u.getFirstName() +" " + u.getLastName());
            singleUser.addProperty("date_created", u.getWhenCreated());
            singleUser.addProperty("is_active", u.getIsActive());
            result.add(singleUser);
        }
        String s = gson.toJson(result);
        return Response.status(Response.Status.OK).entity(s).build();
    }

    @Path("{aid}/rider")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewRiderRating(@PathParam("aid") String aid){
        User u = ai.getUserById(aid);
        if(u.isNil()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        List<Rate> riderRates = ri.getRiderRatesByAccountId(aid);
        JsonArray detail = new JsonArray();
        for(Rate r : riderRates){
            JsonObject singleRate = new JsonObject();
            singleRate.addProperty("rid", r.getRid());
            singleRate.addProperty("sent_by_id", r.getSentBy());
            singleRate.addProperty("date", r.getDate());
            singleRate.addProperty("rating", r.getRating());
            singleRate.addProperty("comment", r.getComment());
        }
        JsonObject result = new JsonObject();
        result.addProperty("aid", u.getAid());
        result.addProperty("first_name", u.getFirstName());
        result.addProperty("rides", rideI.getTotalRidesByAid(aid));
        result.addProperty("ratings", ri.getRiderRatesByAccountId(aid).size());
        /* TODO */
        double avg_rating =  ri.getRiderAverageRatingByAccountId(aid);
        if( avg_rating == 0.0){
            result.add("average_rating", JsonNull.INSTANCE);
        } else {
            result.addProperty("average_rating", avg_rating);
        }
        result.add("detail", detail);

        String s = gson.toJson(result); return Response.status(Response.Status.OK).entity(s).build();
    }
    @Path("{aid}/driver")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewDriverRating(@PathParam("aid") String aid){
        User u = ai.getUserById(aid);
        if(u.isNil()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        List<Rate> driverRates = ri.getDriverRatesByAccountId(aid);
        JsonArray detail = new JsonArray();
        for(Rate r : driverRates){
           JsonObject singleRate = new JsonObject();
           singleRate.addProperty("rid", r.getRid());
           singleRate.addProperty("sent_by_id", r.getSentBy());
           singleRate.addProperty("date", r.getDate());
           singleRate.addProperty("rating", r.getRating());
           singleRate.addProperty("comment", r.getComment());
        }
        JsonObject result = new JsonObject();
        result.addProperty("aid", u.getAid());
        result.addProperty("first_name", u.getFirstName());
        result.addProperty("rides", rideI.getTotalRidesByAid(aid));
        result.addProperty("ratings", ri.getDriverRatesByAccountId(aid).size());

        /* TODO */
        double avg_rating =  ri.getDriverAverageRatingByAccountId(aid);
        if( avg_rating == 0.0){
            result.add("average_rating", JsonNull.INSTANCE);
        } else {
            result.addProperty("average_rating", avg_rating);
        }
        result.add("detail", detail);

        String s = gson.toJson(result);
        return Response.status(Response.Status.OK).entity(s).build();
    }

}
