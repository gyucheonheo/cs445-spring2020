import Entity.Trip.DateTimeFormat.DateTimeFormat;
import junit.framework.Assert;
import org.junit.Test;

public class DateTimeFormatTest {
    @Test
    public void DateTimeFormatMustHave10DigitsDateUnderThatPattern(){
        DateTimeFormat dt = new DateTimeFormat("DD-MM-YYYY,HH:MM");
        Assert.assertEquals(10, dt.getDate().length());
    }
    @Test
    public void DateTimeFormatMustHave5DigitsTimeUnderThisPattern(){
        DateTimeFormat dt = new DateTimeFormat("DD-MM-YYYY , HH:MM");
        Assert.assertEquals(5, dt.getTime().length());
    }
}
