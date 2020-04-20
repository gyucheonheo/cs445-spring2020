package Controller.RideRequest.Validator;

import Boundary.Account.AccountInteractorQueryBoundary;
import Entity.Boundary.Account.User.User;
import Interactor.Account.AccountInteractorQuery;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class RequestValidator {
    private static String emsg="";

    public static boolean isValid(JsonObject jsonObject){
        AccountInteractorQueryBoundary aib = AccountInteractorQuery.INSTANCE;
        String aid = jsonObject.get("aid").getAsString();
        JsonElement rideConfirmed = jsonObject.get("ride_confirmed");
        JsonElement pickupConfirmed = jsonObject.get("pickup_confirmed");
        User u = aib.getUserById(aid);
        boolean isValid = isUserNull(u) && isUserActive(u) && isRideConfirmedJsonNull(rideConfirmed) && isPickUpConfirmedJsonNull(pickupConfirmed);
        return isValid;
    }
    private static boolean isUserNull(User u){
        if(u.isNil()){
            emsg="User does not exist";
            return false;
        }
        return true;
    }
    private static boolean isUserActive(User u){
        if(!u.getIsActive()){
            emsg="This account ("+u.getAid()+") is not active, may not create a join ride request.";
            return false;
        }
        return true;
    }
    private static boolean isRideConfirmedJsonNull(JsonElement rideConfirmed){
        if(!rideConfirmed.isJsonNull()){
            emsg="Invalid value for ride_confirmed";
            return false;
        }
        return true;
    }
    private static boolean isPickUpConfirmedJsonNull(JsonElement pickupConfirmed){
        if(!pickupConfirmed.isJsonNull()){
            emsg="Invalid value for pickup_confirmed";
            return false;
        }
       return true;
    }
    public static String getErrorMessage(){
        return emsg;
    }
}
