package Entity.Bounded.Trip.Rules;

public class NullBoundedRules extends BoundedRules {
    @Override
    public int getMaxPeople() {
        return 0;
    }
}
