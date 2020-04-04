package Controller.Command;

import Boundary.TripInteractorBoundary;
import Controller.Parsers.CarInfoParser;
import Controller.Parsers.DateTimeFormatParser;
import Controller.Parsers.LocationInfoParser;
import Controller.Parsers.RulesParser;
import Entity.Boundary.Trip.Car.Car;
import Entity.Boundary.Trip.DateTimeFormat.DateTimeFormat;
import Entity.Boundary.Trip.LocationInformation.LocationInformation;
import Entity.Boundary.Trip.Rules.Rules;
import Entity.Boundary.Trip.Trip;
import Interactor.TripInteractor;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;

@Path("/rides")
public class TripCommandController {

    private TripInteractorBoundary tripI = TripInteractor.INSTANCE;
    private Gson gson = new Gson();
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRide(String json) throws ParseException {
        JsonObject j = new JsonParser().parse(json).getAsJsonObject();
        if(!CreateRideValidator.isValid(json)){
            return Response.status(Response.Status.BAD_REQUEST).entity(CreateRideValidator.emsg).build();
        }
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        String aid = jsonObject.get("aid").getAsString();

        JsonObject location_info = jsonObject.get("location_info").getAsJsonObject();
        LocationInformation l = LocationInfoParser.parse(location_info);

        JsonObject date_time = jsonObject.get("date_time").getAsJsonObject();
        DateTimeFormat dt = DateTimeFormatParser.parse(date_time);

        JsonObject car_info = jsonObject.get("car_info").getAsJsonObject();
        Car car = CarInfoParser.parse(car_info);

        int max_passengers = jsonObject.get("max_passengers").getAsInt();
        double amount_per_passenger = jsonObject.get("amount_per_passenger").getAsDouble();
        String conditions = jsonObject.get("conditions").getAsString();
        Rules rules = RulesParser.parse(max_passengers, amount_per_passenger, conditions);

        Trip newTrip = tripI.createTrip(aid, l, car, dt, rules);
        tripI.registerTrip(newTrip);
        String tid = gson.toJson(newTrip.getTid());
        return Response.status(Response.Status.CREATED).entity(tid).build();
    }

    @Path("{rid}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRide(@PathParam("rid") String rid, String json) throws ParseException {
        Trip t = tripI.getTripById(rid);
        if(t.isNil()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        String aid = jsonObject.get("aid").getAsString();
        if(!UpdateRideValidator.isTripCreatedByAid(t, aid)){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        JsonObject location_info = jsonObject.get("location_info").getAsJsonObject();
        LocationInformation l = LocationInfoParser.parse(location_info);

        JsonObject date_time = jsonObject.get("date_time").getAsJsonObject();
        DateTimeFormat dt = DateTimeFormatParser.parse(date_time);

        JsonObject car_info = jsonObject.get("car_info").getAsJsonObject();
        Car car = CarInfoParser.parse(car_info);

        int max_passengers = jsonObject.get("max_passengers").getAsInt();
        double amount_per_passenger = jsonObject.get("amount_per_passenger").getAsDouble();
        String conditions = jsonObject.get("conditions").getAsString();
        Rules rules = RulesParser.parse(max_passengers, amount_per_passenger, conditions);

        tripI.updateTrip(rid, l, car, dt, rules);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Path("{rid}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRide(@PathParam("rid") String rid){
        Trip t = tripI.getTripById(rid);
        if(t.isNil()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        tripI.deleteTrip(rid);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
