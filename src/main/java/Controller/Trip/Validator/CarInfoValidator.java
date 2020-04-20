package Controller.Trip.Validator;

import com.google.gson.JsonObject;

public class CarInfoValidator {
    private static String emsg="";
    public static boolean isValid(JsonObject car_info) {
        return isJsonObjectNull(car_info) &&
                isVehicleInfoValid(car_info) &&
                isPlateValid(car_info);
    }
        private static boolean isJsonObjectNull(JsonObject json){
            if(json == null){
                emsg="car_info is null.";
                return false;
            }
            return true;
        }
        private static boolean isVehicleInfoValid(JsonObject json){
            return hasMemberField("make", json, "make is null") &&
                    isMemberEmpty("make", json, "make is empty.") &&
                    hasMemberField("model", json, "model is null") &&
                    isMemberEmpty("model", json, "model is empty.") &&
                    hasMemberField("color", json, "color is null") &&
                    isMemberEmpty("color", json, "color is empty.");
        }
        private static boolean isPlateValid(JsonObject json){
            return hasMemberField("plate_state", json, "plate_state is null") &&
                    isMemberEmpty("plate_state", json, "plate_state is empty.") &&
                    hasMemberField("plate_serial", json, "plate_serial is null") &&
                    isMemberEmpty("plate_serial", json, "plate_serial is empty.");
        }
            private static boolean hasMemberField(String member, JsonObject json, String error){
                if(!json.has(member)){
                    emsg=error;
                    return false;
                }
                return true;
            }
            private static boolean isMemberEmpty(String member, JsonObject json, String error){
                if(json.get(member).getAsString().isEmpty()){
                    emsg=error;
                    return false;
                }
                return true;
            }
    public static String getErrorMessage(){
        return emsg;
    }
}
