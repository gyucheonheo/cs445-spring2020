package Controller.Trip.Validator;

import com.google.gson.JsonObject;

public class LocationInfoValidator {
    private static String emsg="";
    public static boolean isValid(JsonObject loc_info){
        if(loc_info == null){
            emsg = "location_info is null.";
            return false;
        }
        if(loc_info.get("from_city").getAsString() == null){
            emsg = "from_city is null";
            return false;
        }
        if(loc_info.get("from_city").getAsString().isEmpty()){
            emsg = "from_city is empty";
            return false;
        }
        if(loc_info.get("to_city").getAsString() == null){
            emsg = "to_city is null";
            return false;
        }
        if(loc_info.get("to_city").getAsString().isEmpty()){
            emsg = "to_city is empty";
            return false;
        }
        return true;
    }
    public static String getErrorMessage(){
        return emsg;
    }
}
