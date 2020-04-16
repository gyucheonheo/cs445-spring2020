package Controller.Trip.Validator;

import com.google.gson.JsonObject;

public class CarInfoValidator {
    private static String emsg="";
    public static boolean isValid(JsonObject car_info) {
        if(car_info == null){
            emsg = "car_info is null.";
            return false;
        }
        if(car_info.get("make").getAsString() == null){
            emsg = "make is null";
            return false;
        }
        if(car_info.get("make").getAsString().isEmpty()){ emsg = "make is empty.";
            return false;
        }
        if(car_info.get("model").getAsString() == null){
            emsg = "model is null";
            return false;
        }
        if(car_info.get("model").getAsString().isEmpty()){
            emsg = "model is empty.";
            return false;
        }
        if(car_info.get("color").getAsString() == null){
            emsg = "color is null";
            return false;
        }
        if(car_info.get("color").getAsString().isEmpty()){
            emsg = "color is empty.";
            return false;
        }
        if(car_info.get("plate_state").getAsString() == null){
            emsg = "plate_state is null";
            return false;
        }
        if(car_info.get("plate_state").getAsString().isEmpty()){
            emsg = "plate_state is empty.";
            return false;
        }
        if(car_info.get("plate_state").getAsString().length() != 2){
            emsg = "Invalid plate_state.";
            return false;
        }
        if(car_info.get("plate_serial").getAsString() == null){
            emsg = "plate_serial is null";
            return false;
        }
        if(car_info.get("plate_serial").getAsString().isEmpty()){
            emsg = "plate_serial is empty.";
            return false;
        }
        return true;
    }
    public static String getErrorMessage(){
        return emsg;
    }
}
