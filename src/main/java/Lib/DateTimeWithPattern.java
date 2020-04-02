package Lib;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeWithPattern {
    public static String getDate(String pattern ){
        Format formatter = new SimpleDateFormat(pattern);
        return formatter.format(new Date());
    }

}
