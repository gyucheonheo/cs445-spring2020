package Controller.Parsers;

import Entity.Boundary.Trip.Rules.Rules;
import Entity.Bounded.Trip.Rules.BoundedRules;

import java.util.Arrays;
import java.util.List;

public class RulesParser {
    public static Rules parse(int max_passengers, double amount_per_passenger, String conditions) {

        List<String> conditionList = Arrays.asList(conditions.split("."));
        return BoundedRules.Make(max_passengers, amount_per_passenger,conditionList);
    }
}
