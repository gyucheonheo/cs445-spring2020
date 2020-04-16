package Entity.Boundary.Trip.Rules;

public interface Rules {
    int getMaxPeople();
    Double getAmountPerPassenger();
    String getConditions();
    boolean isNil();
}
