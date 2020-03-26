package EntityTest.TripTest.DateTimeFormatTest;

import Entity.Boundary.Trip.DateTimeFormat.DateTimeFormat;
import Entity.Bounded.Trip.DateTimeFormat.BoundedDateTimeFormat;
import junit.framework.Assert;
import org.junit.Test;

public class DateTimeFormatTest {
    @Test
    public void DateTimeFormatMustHave10DigitsDateUnderThatPattern(){
        DateTimeFormat dt = BoundedDateTimeFormat.Make("DD-MM-YYYY,HH:MM", ",");
        Assert.assertEquals(10, dt.getDate().length());
    }
    @Test
    public void DateTimeFormatMustHave5DigitsTimeUnderThisPattern(){
        DateTimeFormat dt =  BoundedDateTimeFormat.Make("DD-MM-YYYY , HH:MM",",");
        Assert.assertEquals(5, dt.getTime().length());
    }
}
