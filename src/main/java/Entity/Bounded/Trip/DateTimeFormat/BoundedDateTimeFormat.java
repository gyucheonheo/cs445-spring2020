package Entity.Bounded.Trip.DateTimeFormat;

import Entity.Boundary.Trip.DateTimeFormat.DateTimeFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class BoundedDateTimeFormat implements DateTimeFormat {
    private String date;
    private String time;

    public static DateTimeFormat MakeNow(){
        return new BoundedDateTimeFormat();
    }
        private BoundedDateTimeFormat(){
            DateFormat currentFormat = new SimpleDateFormat("dd-MMM-yyyy, hh:mm:ss");
            String[] strLocalDateTime = currentFormat.format(new Date()).split(",");
            this.date = strLocalDateTime[0];
            this.time = strLocalDateTime[1];
        }
    public static DateTimeFormat MakeDateTime(String date_time) throws ParseException {
        return new BoundedDateTimeFormat(date_time);
    }
        private BoundedDateTimeFormat(String date_time) throws ParseException {
            DateFormat currentFormat = new SimpleDateFormat("dd-MMM-yyyy, hh:mm");
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            DateFormat timeFormat = new SimpleDateFormat("hh:mm");
            this.date = dateFormat.format(currentFormat.parse(date_time));
            this.time= timeFormat.format(currentFormat.parse(date_time));
        }

    public String getDate(){
        return this.date;
    }
    public String getTime(){
        return this.time;
    }
}
