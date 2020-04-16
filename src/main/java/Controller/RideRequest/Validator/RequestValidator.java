package Controller.RideRequest.Validator;

import Boundary.AccountInteractorBoundary;
import Boundary.TripInteractorBoundary;
import Entity.Boundary.Account.User.User;
import Entity.Boundary.Trip.Trip;
import Interactor.Account.AccountInteractor;
import Interactor.Trip.TripInteractor;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class RequestValidator {
    private static String emsg="";

    public static boolean isValid(JsonObject jsonObject){
        AccountInteractorBoundary aib = AccountInteractor.INSTANCE;
        String aid = jsonObject.get("aid").getAsString();
        JsonElement rideConfirmed = jsonObject.get("ride_confirmed");
        JsonElement pickupConfirmed = jsonObject.get("pickup_confirmed");
        User u = aib.getUserById(aid);
        if(u.isNil()){
            emsg="User does not exist";
            return false;
        }
        if(!u.getIsActive()){
            emsg="This account ("+u.getAid()+") is not active, may not create a join ride request.";
            return false;
        }

        if(!rideConfirmed.isJsonNull()){
            emsg="Invalid value for ride_confirmed";
            return false;
        }

        if(!pickupConfirmed.isJsonNull()){
            emsg="Invalid value for pickup_confirmed";
            return false;
        }
        return true;
    }
    public static boolean isJoinRequestPatchValid(JsonObject jsonObject, String rid){

        AccountInteractorBoundary aib = AccountInteractor.INSTANCE;
        TripInteractorBoundary ti = TripInteractor.INSTANCE;
        String aid = jsonObject.get("aid").getAsString();
        JsonElement rideConfirmed = jsonObject.get("ride_confirmed");
        JsonElement pickupConfirmed = jsonObject.get("pickup_confirmed");
        User u = aib.getUserById(aid);
        Trip t = ti.getTripById(rid);

        if(u.isNil()){
            emsg="User does not exist";
            return false;
        }

        if(!u.getIsActive()){
            emsg="This account ("+u.getAid()+") is not active, may not create a join ride request.";
            return false;
        }

        if(!t.getAid().equals(aid)){
            emsg="This account ("+aid+") didn't create the ride ("+rid+")";
            return false;
        }

        if(rideConfirmed.isJsonNull()){
            emsg="Invalid value for ride_confirmed";
            return false;
        }
        if(pickupConfirmed.isJsonNull()){
            emsg="Invalid value for pickup_confirmed";
            return false;
        }
        if(!pickupConfirmed.getAsBoolean()){
            emsg="Invalid value for pickup_confirmed";
            return false;
        }
        return true;
    }
    public static String getErrorMessage(){
        return emsg;
    }
}
