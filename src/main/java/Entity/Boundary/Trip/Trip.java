package Entity.Boundary.Trip;

import Entity.Boundary.Trip.Car.Car;
import Entity.Boundary.Trip.LocationInformation.LocationInformation;
import Entity.Boundary.Trip.Rules.Rules;

public interface Trip {
    String getTid();
    void setLocationInformation(LocationInformation l);
    void setCarInformation(Car c);
    void setRules(Rules p);

    LocationInformation getLocationInformation();
    Car getCarInformation();
    Rules getRules();

    boolean isNil();
}
