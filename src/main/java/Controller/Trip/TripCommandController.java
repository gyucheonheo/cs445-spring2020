package Controller.Trip;

import Boundary.Account.AccountInteractorQueryBoundary;
import Boundary.Trip.TripInteractorCommandBoundary;
import Boundary.Trip.TripInteractorQueryBoundary;
import Controller.ErrorHandler.ErrorMessage;
import Controller.Trip.Validator.CarInfoValidator;
import Controller.Trip.Validator.DateTimeValidator;
import Controller.Trip.Validator.LocationInfoValidator;
import Controller.Trip.Parser.CarInfoParser;
import Controller.Trip.Parser.DateTimeFormatParser;
import Controller.Trip.Parser.LocationInfoParser;
import Controller.Trip.Parser.RulesParser;
import Entity.Boundary.Trip.Car.Car;
import Entity.Boundary.Trip.DateTimeFormat.DateTimeFormat;
import Entity.Boundary.Trip.LocationInformation.LocationInformation;
import Entity.Boundary.Trip.Rules.Rules;
import Entity.Boundary.Trip.Trip;
import Interactor.Account.AccountInteractorQuery;
import Interactor.Trip.TripInteractorCommand;
import Interactor.Trip.TripInteractorQuery;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;

@Path("/rides")
public class TripCommandController {

    private TripInteractorQueryBoundary trip_query_i = TripInteractorQuery.INSTANCE;
    private AccountInteractorQueryBoundary acc_query_i = AccountInteractorQuery.INSTANCE;
    private TripInteractorCommandBoundary trip_command_i = TripInteractorCommand.INSTANCE;
    private Gson gson = new Gson();
    private ErrorMessage error = new ErrorMessage();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRide(String json) throws ParseException {

        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        String aid = jsonObject.get("aid").getAsString();
        if(!acc_query_i.getUserById(aid).getIsActive()){
            JsonObject err = error.createError("This account ("+aid+") is not active, may not create a ride.", 400, "/rides");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(err)).build();
        }
        JsonObject location_info = jsonObject.get("location_info").getAsJsonObject();
        if(!LocationInfoValidator.isValid(location_info)){
            JsonObject err = error.createError(LocationInfoValidator.getErrorMessage(), 400, "/rides");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(err)).build();
        }
        LocationInformation l = LocationInfoParser.parse(location_info);

        JsonObject date_time = jsonObject.get("date_time").getAsJsonObject();
        if(!DateTimeValidator.isValid(date_time)){
            JsonObject err = error.createError(DateTimeValidator.getErrorMessage(), 400, "/rides");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(err)).build();
        }
        DateTimeFormat dt = DateTimeFormatParser.parse(date_time);

        JsonObject car_info = jsonObject.get("car_info").getAsJsonObject();
        if(!CarInfoValidator.isValid(car_info)){
            JsonObject err = error.createError(CarInfoValidator.getErrorMessage(), 400, "/rides");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(err)).build();
        }
        Car car = CarInfoParser.parse(car_info);

        int max_passengers = jsonObject.get("max_passengers").getAsInt();

        JsonElement amount_per_passenger = jsonObject.get("amount_per_passenger");
        String conditions = jsonObject.get("conditions").getAsString();
        Rules rules = RulesParser.parse(max_passengers, amount_per_passenger, conditions);

        Trip newTrip = trip_query_i.createTrip(aid, l, car, dt, rules);
        trip_command_i.registerTrip(newTrip);
        JsonObject result = new JsonObject();
        result.addProperty("rid", newTrip.getTid());
        return Response.status(Response.Status.CREATED).entity(gson.toJson(result)).build();
    }

    @Path("{rid}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRide(@PathParam("rid") String rid, String json) throws ParseException {
        Trip t = trip_query_i.getTripById(rid);

        if(t.isNil()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        String aid = jsonObject.get("aid").getAsString();
        if(!aid.equals(t.getAid())){
            JsonObject err = error.createError("Only the creator of the ride may change it", 400, "/rides/"+rid);
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(err)).build();
        }
        JsonObject location_info = jsonObject.get("location_info").getAsJsonObject();
        if(!LocationInfoValidator.isValid(location_info)){
            JsonObject err = error.createError(LocationInfoValidator.getErrorMessage(), 400, "/rides/"+rid);
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(err)).build();
        }
        LocationInformation l = LocationInfoParser.parse(location_info);

        JsonObject date_time = jsonObject.get("date_time").getAsJsonObject();
        if(!DateTimeValidator.isValid(date_time)){
            JsonObject err = error.createError(DateTimeValidator.getErrorMessage(), 400, "/rides/"+rid);
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(err)).build();
        }
        DateTimeFormat dt = DateTimeFormatParser.parse(date_time);

        JsonObject car_info = jsonObject.get("car_info").getAsJsonObject();
        if(!CarInfoValidator.isValid(date_time)){
            JsonObject err = error.createError(CarInfoValidator.getErrorMessage(), 400, "/rides/"+rid);
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(err)).build();
        }
        Car car = CarInfoParser.parse(car_info);

        int max_passengers = jsonObject.get("max_passengers").getAsInt();
        JsonElement amount_per_passenger = jsonObject.get("amount_per_passenger");
        String conditions = jsonObject.get("conditions").getAsString();
        Rules rules = RulesParser.parse(max_passengers, amount_per_passenger, conditions);

        trip_command_i.updateTrip(rid, l, car, dt, rules);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Path("{rid}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRide(@PathParam("rid") String rid){
        Trip t = trip_query_i.getTripById(rid);
        if(t.isNil()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        trip_command_i.deleteTrip(rid);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
