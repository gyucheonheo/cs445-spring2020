package Entity.Trip.DateTimeFormat;

import Lib.DateTimeWithPattern;

public class DateTimeFormat {
    private String date;
    private String time;

    public DateTimeFormat(String pattern, String delimeter){
        String[] dateTime = DateTimeWithPattern.getDate(pattern).split(delimeter, 2);
        this.date = dateTime[0].trim();
        this.time = dateTime[1].trim();
    }
    public DateTimeFormat(String pattern){
        String[] dateTime = DateTimeWithPattern.getDate(pattern).split(",", 2);
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
