package Controller.Trip.Validator;

import com.google.gson.JsonObject;

public class LocationInfoValidator {
    private static String emsg="";
    public static boolean isValid(JsonObject loc_info){
        return isJsonObjectNull(loc_info) &&
                isFromCityValid(loc_info) &&
                isToCityValid(loc_info);
    }
        private static boolean isJsonObjectNull(JsonObject json){
            if(json == null){
                emsg = "location_info is null.";
                return false;
            }
            return true;
        }
        private static boolean isFromCityValid(JsonObject json){
            return hasMemberField("from_city", json, "from_city is null") &&
                    isMemberEmpty("from_city", json, "from_city is empty");
        }

        private static boolean isToCityValid(JsonObject json){
            return hasMemberField("to_city", json, "to_city is null") &&
                    isMemberEmpty("to_city", json, "to_city is empty");
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
