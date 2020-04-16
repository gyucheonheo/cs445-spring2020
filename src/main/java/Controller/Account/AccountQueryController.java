package Controller.Account;

import Boundary.Account.AccountInteractorQueryBoundary;
import Boundary.Rate.RateInteractorQueryBoundary;
import Boundary.RideRequest.RideRequestInteractorQueryBoundary;
import Boundary.Trip.TripInteractorQueryBoundary;
import Entity.Boundary.Account.User.RideInformation.Rate.Rate;
import Entity.Boundary.Account.User.User;
import Interactor.Account.AccountInteractorQuery;
import Interactor.Rate.RateInteractorQuery;
import Interactor.RideRequest.RideRequestInteractorQuery;
import Interactor.Trip.TripInteractorQuery;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.util.List;

@Path("accounts")
public class AccountQueryController {
    private Gson gson = new GsonBuilder().serializeNulls().create();
    private AccountInteractorQueryBoundary acc_query_i = AccountInteractorQuery.INSTANCE;
    private RateInteractorQueryBoundary rate_query_i = RateInteractorQuery.INSTANCE;
    private RideRequestInteractorQueryBoundary ride_query_i = RideRequestInteractorQuery.INSTANCE;
    private TripInteractorQueryBoundary trip_query_i = TripInteractorQuery.INSTANCE;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewAllAccount(@Context UriInfo uriInfo){
        MultivaluedMap<String, String> parameters = uriInfo.getQueryParameters();
        String key = parameters.getFirst("key");
        List<User> users = acc_query_i.getAllUsers(key);
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
        User u = acc_query_i.getUserById(aid);
        if(u.isNil()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        List<Rate> riderRates = rate_query_i.getRiderRatesByAccountId(aid);
        JsonArray detail = new JsonArray();
        for(Rate r : riderRates){
            JsonObject singleRate = new JsonObject();
            singleRate.addProperty("rid", r.getRid());
            singleRate.addProperty("sent_by_id", r.getSentBy());
            User commenter = acc_query_i.getUserById(r.getSentBy());
            singleRate.addProperty("first_name", commenter.getFirstName());
            singleRate.addProperty("date", r.getDate());
            singleRate.addProperty("rating", r.getRating());
            singleRate.addProperty("comment", r.getComment());
            detail.add(singleRate);
        }
        JsonObject result = new JsonObject();
        result.addProperty("aid", u.getAid());
        result.addProperty("first_name", u.getFirstName());
        result.addProperty("rides", ride_query_i.getTotalRidesByAid(aid));
        result.addProperty("ratings", rate_query_i.getRiderRatesByAccountId(aid).size());
        result.addProperty("average_rating", rate_query_i.getRiderAverageRatingByAccountId(aid));
        result.add("detail", detail);

        String s = gson.toJson(result);
        return Response.status(Response.Status.OK).entity(s).build();

    }
    @Path("{aid}/driver")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewDriverRating(@PathParam("aid") String aid){
        User u = acc_query_i.getUserById(aid);
        if(u.isNil()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        List<Rate> driverRates = rate_query_i.getDriverRatesByAccountId(aid);
        JsonArray detail = new JsonArray();
        for(Rate r : driverRates){
           JsonObject singleRate = new JsonObject();
           singleRate.addProperty("rid", r.getRid());
           singleRate.addProperty("sent_by_id", r.getSentBy());
           User commenter = acc_query_i.getUserById(r.getSentBy());
           singleRate.addProperty("first_name", commenter.getFirstName());
           singleRate.addProperty("date", r.getDate());
           singleRate.addProperty("rating", r.getRating());
           singleRate.addProperty("comment", r.getComment());
           detail.add(singleRate);
        }
        JsonObject result = new JsonObject();
        result.addProperty("aid", u.getAid());
        result.addProperty("first_name", u.getFirstName());
        result.addProperty("rides", trip_query_i.getTotalRidesByAid(aid));
        result.addProperty("ratings", rate_query_i.getDriverRatesByAccountId(aid).size());
        result.addProperty("average_rating", rate_query_i.getDriverAverageRatingByAccountId(aid));
        result.add("detail", detail);

        String s = gson.toJson(result);
        return Response.status(Response.Status.OK).entity(s).build();
    }

}
