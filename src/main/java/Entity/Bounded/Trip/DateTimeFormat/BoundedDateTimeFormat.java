package Entity.Bounded.Trip.DateTimeFormat;

import Entity.Boundary.Trip.DateTimeFormat.DateTimeFormat;
import Lib.DateTimeWithPattern;

public class BoundedDateTimeFormat implements DateTimeFormat {
    private String date;
    private String time;

    public static DateTimeFormat Make(String pattern, String delimiter){
        return new BoundedDateTimeFormat(pattern, delimiter);
    }
        private BoundedDateTimeFormat(String pattern, String delimiter){
            String[] dateTime = DateTimeWithPattern.getDate(pattern).split(delimiter, 2);
            this.date = dateTime[0].trim();
            this.time = dateTime[1].trim();
        }

    public String getDate(){
        return this.date;
    }
    public String getTime(){
        return this.time;
    }
}
