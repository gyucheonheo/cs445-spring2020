package ParserTest;

import Controller.Trip.Parser.DateTimeFormatParser;
import Entity.Boundary.Trip.DateTimeFormat.DateTimeFormat;
import com.google.gson.JsonObject;
import junit.framework.Assert;
import org.junit.Test;

import java.text.ParseException;

public class DateTimeFormatParserTest {
    @Test
    public void dateTimeFormatParser_should_parse_and_returnDateTimeFormat() throws ParseException {
        JsonObject j = new JsonObject();
        j.addProperty("date", "14-Apr-2020");
        j.addProperty("time", "09:00");
        DateTimeFormat dt = DateTimeFormatParser.parse(j);

        Assert.assertEquals("14-Apr-2020", dt.getDate());
        Assert.assertEquals("09:00", dt.getTime());
    }
}
