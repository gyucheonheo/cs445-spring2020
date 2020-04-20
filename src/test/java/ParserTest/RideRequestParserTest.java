package ParserTest;

import Controller.RideRequest.Parser.RideRequestParser;
import Entity.Boundary.RideRequest.RideRequest;
import com.google.gson.JsonObject;
import junit.framework.Assert;
import org.junit.Test;

public class RideRequestParserTest {
    @Test
    public void riderequest_parse_should_parse_and_return_riderequest_object(){
        JsonObject j = new JsonObject();
        j.addProperty("aid", "1234");
        j.addProperty("passengers", 5);
        RideRequest r = RideRequestParser.parse(j, "rid");
        Assert.assertEquals("1234", r.getAid());
        Assert.assertEquals(5, r.getPassengers());
    }
}
