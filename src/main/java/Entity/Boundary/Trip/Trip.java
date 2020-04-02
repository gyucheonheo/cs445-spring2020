package Entity.Boundary.Trip;

import Entity.Boundary.Trip.Car.Car;
import Entity.Boundary.Trip.DateTimeFormat.DateTimeFormat;
import Entity.Boundary.Trip.LocationInformation.LocationInformation;
import Entity.Boundary.Trip.Rules.Rules;

public interface Trip {
    String getTid();
    void setLocationInformation(LocationInformation l);
    void setCarInformation(Car c);
    void setRules(Rules p);
    void setDateTimeFormat(DateTimeFormat dt);

    LocationInformation getLocationInformation();
    Car getCarInformation();
    Rules getRules();
    DateTimeFormat getDateTimeFormat();
    boolean isNil();

    String getAid();
}
