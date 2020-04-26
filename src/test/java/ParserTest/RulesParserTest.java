package ParserTest;

import Controller.Trip.Parser.RulesParser;
import Entity.Boundary.Trip.Rules.Rules;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import junit.framework.Assert;
import org.junit.Test;

public class RulesParserTest {
    @Test
    public void rule_parse_and_return_rule_zero_case(){
        Rules r = RulesParser.parse(5, JsonNull.INSTANCE, "No Smoking");
        Assert.assertEquals(5, r.getMaxPeople());
        Assert.assertEquals(0.0,r.getAmountPerPassenger());
        Assert.assertEquals("No Smoking", r.getConditions());
    }

    @Test
    public void rule_parse_and_return_rule_object_not_null_case(){
        JsonObject j = new JsonObject();
        j.addProperty("amount_per_passenger", 5.0);
        Rules r = RulesParser.parse(5, j.get("amount_per_passenger"), "No Smoking");
        Assert.assertEquals(5, r.getMaxPeople());
        Assert.assertEquals(5.0, r.getAmountPerPassenger());
        Assert.assertEquals("No Smoking", r.getConditions());
    }
}
