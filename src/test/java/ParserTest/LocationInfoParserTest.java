package ParserTest;

import Controller.Trip.Parser.LocationInfoParser;
import Entity.Boundary.Trip.LocationInformation.LocationInformation;
import com.google.gson.JsonObject;
import junit.framework.Assert;
import org.junit.Test;

public class LocationInfoParserTest {
    @Test
    public void location_parse_should_parse_and_return_LocationInformation(){
        JsonObject j = new JsonObject();
        j.addProperty("from_city", "from");
        j.addProperty("from_zip", "");
        j.addProperty("to_city", "to");
        j.addProperty("to_zip", "");
        LocationInformation l = LocationInfoParser.parse(j);

        Assert.assertEquals("from", l.getStartingPoint().getCity());
        Assert.assertEquals("", l.getStartingPoint().getZip());
        Assert.assertEquals("to", l.getEndingPoint().getCity());
        Assert.assertEquals("", l.getEndingPoint().getZip());

    }
}
