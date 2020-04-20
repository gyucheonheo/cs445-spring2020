package Controller.Rate.Validator;

import Boundary.RideRequest.RideRequestInteractorQueryBoundary;
import Boundary.Trip.TripInteractorQueryBoundary;
import Entity.Boundary.RideRequest.RideRequest;
import Entity.Boundary.Trip.Trip;
import Interactor.RideRequest.RideRequestInteractorQuery;
import Interactor.Trip.TripInteractorQuery;
import com.google.gson.JsonObject;

public class RateValidator {
    private static String emsg="";

    public static boolean isValid(JsonObject json){
        return hasRide(json) && isRidEmpty(json) && isOwnerOrPassenger(json) && isRatingNegative(json) && isRatingAboveFive(json);
    }

    private static boolean isRatingNegative(JsonObject json) {
        int rate = json.get("rating").getAsInt();
        if( rate < 1  ){
            emsg="Invalid rating value";
            return false;
        }
        return true;
    }
    private static boolean isRatingAboveFive(JsonObject json) {
        int rate = json.get("rating").getAsInt();
        if( rate > 5){
            emsg="Invalid rating value";
            return false;
        }
        return true;
    }

    private static boolean isOwnerOrPassenger(JsonObject json) {
        String rid = json.get("rid").getAsString();
        TripInteractorQueryBoundary ti = TripInteractorQuery.INSTANCE;
        RideRequestInteractorQueryBoundary ri = RideRequestInteractorQuery.INSTANCE;
        Trip t = ti.getTripById(rid);
        RideRequest rr = ri.getRequestByTripId(rid);

        String tripOwnerId = t.getAid();
        String passengerId = rr.getAid();
        String sent_by_id = json.get("sent_by_id").getAsString();
        if(!tripOwnerId.equals(sent_by_id) && !(passengerId.equals(sent_by_id))){
            emsg = "This account ("+sent_by_id+") didn't create this ride ("+rid+") nor was it a passenger";
            return false;
        }
        return true;
    }

    private static boolean isRidEmpty(JsonObject json) {
        String rid = json.get("rid").getAsString();
        if(rid.isEmpty()){
            emsg = "rid is empty";
            return false;
        }
        return true;
    }

    private static boolean hasRide(JsonObject json) {
        if(!json.has("rid")){
            emsg = "rid is null";
            return false;
        }
        return true;
    }

    public static String getErrorMessage(){
        return emsg;
    }
}
