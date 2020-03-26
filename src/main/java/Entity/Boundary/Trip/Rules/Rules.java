package Entity.Boundary.Trip.Rules;

import java.util.List;

public interface Rules {
    int getMaxPeople();
    double getAmountPerPassenger();
    List<String> getConditions();
    boolean isNil();
}
