package Controller.Trip.Parser;

import Entity.Boundary.Trip.DateTimeFormat.DateTimeFormat;
import Entity.Bounded.Trip.DateTimeFormat.BoundedDateTimeFormat;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateTimeFormatParser {
    public static DateTimeFormat parse(JsonObject date_time) throws ParseException {
        String date = date_time.get("date").getAsString();
        String time = date_time.get("time").getAsString();
        String dateTime = date + ", " + time;

        return BoundedDateTimeFormat.MakeDateTime(dateTime);
    }
}
