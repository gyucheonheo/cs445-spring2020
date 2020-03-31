package Entity.Bounded.Trip.DateTimeFormat;

import Entity.Boundary.Trip.DateTimeFormat.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class BoundedDateTimeFormat implements DateTimeFormat {
    private String date;
    private String time;

    public static DateTimeFormat MakeNow(){
        return new BoundedDateTimeFormat();
    }
        private BoundedDateTimeFormat(){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy, hh:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.now();
            String[] strLocalDateTime = formatter.format(localDateTime).split(",");
            this.date = strLocalDateTime[0];
            this.time = strLocalDateTime[1];
        }
    public static DateTimeFormat MakeDate(int year, int month, int days){
        return new BoundedDateTimeFormat(year, month, days);
    }
        private BoundedDateTimeFormat(int year, int month, int days){
            this.date = DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.of(year, month, days));
        }
    public static DateTimeFormat MakeTime(int hour, int minute){
        return new BoundedDateTimeFormat(hour, minute);
    }
        private BoundedDateTimeFormat(int hour, int minute){
            this.time = DateTimeFormatter.ISO_LOCAL_TIME.format(LocalTime.of(hour, minute));
        }
    public static DateTimeFormat MakeDateTime(int year, int month, int day, int hour, int minute){
        return new BoundedDateTimeFormat(year, month, day, hour, minute);
    }
        private BoundedDateTimeFormat(int year, int month, int days, int hour, int minute){
            this.date = DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.of(year, month, days));
            this.time = DateTimeFormatter.ISO_LOCAL_TIME.format(LocalTime.of(hour, minute));
        }

    public String getDate(){
        return this.date;
    }
    public String getTime(){
        return this.time;
    }
}
