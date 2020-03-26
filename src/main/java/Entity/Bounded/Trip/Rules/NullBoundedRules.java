package Entity.Bounded.Trip.Rules;

import Entity.Boundary.Trip.Rules.Rules;

import java.util.List;

public class NullBoundedRules implements Rules {
    @Override
    public int getMaxPeople() {
        return 0;
    }

    @Override
    public double getAmountPerPassenger() {
        return 0;
    }

    @Override
    public List<String> getConditions() {
        return null;
    }

    @Override
    public boolean isNil(){
        return true;
    }
}
