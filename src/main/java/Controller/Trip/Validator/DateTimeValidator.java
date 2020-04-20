package Controller.Trip.Validator;

import com.google.gson.JsonObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTimeValidator {
    private static String emsg="";
    public static boolean isValid(JsonObject date_time) {
        return hasMemberField("date", date_time, "date is null.") &&
                hasMemberField("time", date_time, "time is null.") &&
                isMemberValidDateTimeFormat("date", date_time, "dd-MMM-yyyy", "Invalid date") &&
                isMemberValidDateTimeFormat("time", date_time, "hh:mm", "Invalid time");
    }
        private static boolean hasMemberField(String member, JsonObject json, String error){
            if(!json.has(member)){
                emsg=error;
                return false;
            }
            return true;
        }

        private static boolean isMemberValidDateTimeFormat(String member, JsonObject json, String format, String error){
            DateFormat dateFormatter = new SimpleDateFormat(format);
            dateFormatter.setLenient(false);
            String field = json.get(member).getAsString();
            try{
                dateFormatter.parse(field);
            } catch (ParseException e){
                emsg = error;
                return false;
            }
            return true;
        }
    public static String getErrorMessage(){
        return emsg;
    }
}
