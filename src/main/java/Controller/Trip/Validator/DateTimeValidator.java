package Controller.Trip.Validator;

import com.google.gson.JsonObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeValidator {
    private static String emsg="";
    public static boolean isValid(JsonObject date_time) {
        String date = date_time.get("date").getAsString();
        String time = date_time.get("time").getAsString();
        if(date == null){
            emsg = "date is null.";
            return false;
        }
        if(time == null){
            emsg = "time is null.";
        }
        DateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
        dateFormatter.setLenient(false);
        try{
            Date dateDate = dateFormatter.parse(date);
        } catch (ParseException e){
            emsg = "Invalid date";
            return false;
        }
        DateFormat timeFormatter = new SimpleDateFormat("hh:mm");
        timeFormatter.setLenient(false);
        try{
            Date timeTime = timeFormatter.parse(time);
        } catch (ParseException e){
            emsg = "Invalid time";
            return false;
        }
        return true;
    }
    public static String getErrorMessage(){
        return emsg;
    }
}
