package Controller.Trip;

import Boundary.Account.AccountInteractorQueryBoundary;
import Boundary.Rate.RateInteractorQueryBoundary;
import Boundary.Trip.TripInteractorQueryBoundary;
import Entity.Boundary.Account.User.RideInformation.Rate.Rate;
import Entity.Boundary.Account.User.User;
import Entity.Boundary.Trip.Trip;
import Interactor.Account.AccountInteractorQuery;
import Interactor.Rate.RateInteractorQuery;
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

@Path("/rides")
public class TripQueryController {
    private Gson gson = new GsonBuilder().serializeNulls().create();
    private TripInteractorQueryBoundary trip_query_i = TripInteractorQuery.INSTANCE;
    private AccountInteractorQueryBoundary acc_query_i = AccountInteractorQuery.INSTANCE;
    private RateInteractorQueryBoundary rate_query_i = RateInteractorQuery.INSTANCE;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRides(@Context UriInfo uriInfo){
        MultivaluedMap<String, String> parameters = uriInfo.getQueryParameters();
        String from = parameters.getFirst("from");
        String to = parameters.getFirst("to");
        String date = parameters.getFirst("date");
        List<Trip> trips = trip_query_i.getAllTrips(from, to, date);
        JsonArray result = new JsonArray();
        for(Trip t : trips){
            JsonObject singleTrip = new JsonObject();
            JsonObject location_info = this.createLocationInfoJsonObject(t);
            JsonObject date_time = this.createDateTimeJsonObject(t);
            singleTrip.addProperty("rid", t.getTid());
            singleTrip.add("location_info", location_info);
            singleTrip.add("date_time", date_time);
            result.add(singleTrip);
        }
        return Response.status(Response.Status.OK).entity(gson.toJson(result)).build();
    }

    @Path("{rid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRideDetails(@PathParam("rid") String rid){

        Trip t = trip_query_i.getTripById(rid);
        if(t.isNil()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        String driverId = t.getAid();
        User u = acc_query_i.getUserById(driverId);
        JsonObject result = new JsonObject();

        JsonArray comments_about_driver = new JsonArray();
        List<Rate> rates = rate_query_i.getDriverRatesByAccountId(u.getAid());
        for(Rate r : rates){
            JsonObject singleRate = new JsonObject();
            singleRate.addProperty("rid", r.getRid());
            singleRate.addProperty("date", r.getDate());
            singleRate.addProperty("rating", r.getRating());
            singleRate.addProperty("comment", r.getComment());
            comments_about_driver.add(singleRate);
        }
        JsonObject location_info = this.createLocationInfoJsonObject(t);
        JsonObject date_time = this.createDateTimeJsonObject(t);
        JsonObject car_info = this.createCarInformationJsonObject(t);

        result.addProperty("rid", t.getTid());
        result.add("location_info", location_info);
        result.add("date_time", date_time);
        result.add("car_info", car_info);
        result.addProperty("max_passengers", t.getRules().getMaxPeople());
        result.addProperty("amount_per_passenger", t.getRules().getAmountPerPassenger());
        result.addProperty("conditions", t.getRules().getConditions());
        result.addProperty("driver", u.getFirstName());
        result.addProperty("driver_picture", u.getPicture());
        int rides = trip_query_i.getTotalRidesByAid(u.getAid());
        result.addProperty("rides", rides);
        result.addProperty("ratings", rates.size());

        Double avg = rate_query_i.getDriverAverageRatingByAccountId(u.getAid());

        result.addProperty("average_rating", avg);
        result.add("comments_about_driver", comments_about_driver);

        String s = gson.toJson(result);
        return Response.status(Response.Status.OK).entity(s).build();
    }

        private JsonObject createLocationInfoJsonObject(Trip t){
            JsonObject location_info = new JsonObject();
            location_info.addProperty("from_city", t.getLocationInformation().getStartingPoint().getCity());
            location_info.addProperty("from_zip", t.getLocationInformation().getStartingPoint().getZip());
            location_info.addProperty("to_city", t.getLocationInformation().getEndingPoint().getCity());
            location_info.addProperty("to_zip", t.getLocationInformation().getEndingPoint().getZip());
            return location_info;
        }
        private JsonObject createDateTimeJsonObject(Trip t){
            JsonObject date_time = new JsonObject();
            String date = t.getDateTimeFormat().getDate();
            date_time.addProperty("date", date);

            String time = t.getDateTimeFormat().getTime();
            date_time.addProperty("time", time);
            return date_time;
        }
        private JsonObject createCarInformationJsonObject(Trip t){
            JsonObject car_info = new JsonObject();
            car_info.addProperty("make", t.getCarInformation().getVehicleInformation().getMake());
            car_info.addProperty("model", t.getCarInformation().getVehicleInformation().getModel());
            car_info.addProperty("color", t.getCarInformation().getVehicleInformation().getColor());
            car_info.addProperty("plate_state", t.getCarInformation().getPlateState());
            car_info.addProperty("plate_serial", t.getCarInformation().getPlateSerial());
            return car_info;
        }
}
