package Controller.Parsers;

import Entity.Boundary.Trip.DateTimeFormat.DateTimeFormat;
import Entity.Bounded.Trip.DateTimeFormat.BoundedDateTimeFormat;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateTimeFormatParser {
    public static DateTimeFormat parse(JsonObject date_time) throws ParseException {
        String date = date_time.get("date").getAsString();
        List<String> dates = Arrays.asList(date.split("-"));
        int year = Integer.parseInt(dates.get(2));
        int month = convertCharMonthToNumber(dates.get(1));
        int day = Integer.parseInt(dates.get(0));
        String time = date_time.get("time").getAsString();
        List<String> times = Arrays.asList(time.split(":"));
        int hour = Integer.parseInt(times.get(0));
        int minute = Integer.parseInt(times.get(1));

        return BoundedDateTimeFormat.MakeDateTime(year, month, day, hour, minute);
    }
        private static int convertCharMonthToNumber(String month) throws ParseException {
             Date date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(month);
             Calendar cal = Calendar.getInstance();
             cal.setTime(date);
             return cal.get(Calendar.MONTH);
        }
}
