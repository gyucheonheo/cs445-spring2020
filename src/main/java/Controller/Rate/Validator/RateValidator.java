package Controller.Rate.Validator;

import Boundary.RideRequestInteractorBoundary;
import Boundary.TripInteractorBoundary;
import Entity.Boundary.RideRequest.RideRequest;
import Entity.Boundary.Trip.Trip;
import Interactor.RideRequest.RideRequestInteractor;
import Interactor.Trip.TripInteractor;
import com.google.gson.JsonObject;

public class RateValidator {
    private static String emsg="";

    public static boolean isValid(JsonObject json){
        String rid = json.get("rid").getAsString();
        if(rid == null){
            emsg = "rid is null";
            return false;
        }
        if(rid.isEmpty()){
            emsg = "rid is empty";
            return false;
        }

        String sent_by_id = json.get("sent_by_id").getAsString();

        TripInteractorBoundary ti = TripInteractor.INSTANCE;
        RideRequestInteractorBoundary ri = RideRequestInteractor.INSTANCE;
        Trip t = ti.getTripById(rid);
        RideRequest rr = ri.getRequestByTripId(rid);

        String tripOwnerId = t.getAid();
        String passengerId = rr.getAid();

        if(!tripOwnerId.equals(sent_by_id) && !(passengerId.equals(sent_by_id))){
            emsg = "This account ("+sent_by_id+") didn't create this ride ("+rid+") nor was it a passenger";
            return false;
        }

        int rate = json.get("rating").getAsInt();
        if( rate < 0 || rate > 5){
            emsg="Invalid rating value";
            return false;
        }

        return true;
    }

    public static String getErrorMessage(){
        return emsg;
    }
}
