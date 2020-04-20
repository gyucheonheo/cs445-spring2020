package Controller.Trip.Parser;

import Entity.Boundary.Trip.Rules.Rules;
import Entity.Bounded.Trip.Rules.BoundedRules;
import com.google.gson.JsonElement;

import java.util.Arrays;
import java.util.List;

public class RulesParser {
    public static Rules parse(int max_passengers, JsonElement amount_per_passenger, String conditions) {
        if(amount_per_passenger.isJsonNull()){
            return BoundedRules.Make(max_passengers, 0.0, conditions );
        } else{
            return BoundedRules.Make(max_passengers, amount_per_passenger.getAsDouble(), conditions);
        }
    }
}
