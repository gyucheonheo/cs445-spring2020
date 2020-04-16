package Controller.RideRequest.Parser;

import Entity.Boundary.RideRequest.RideRequest;
import Entity.Bounded.RideRequest.BoundedRideRequest;
import com.google.gson.JsonObject;

public class RideRequestParser {
    public static RideRequest parse(JsonObject request, String rid){
        String aid = request.get("aid").getAsString();
        int passengers = request.get("passengers").getAsInt();
        return BoundedRideRequest.Make(aid, rid, passengers);
    }
}
