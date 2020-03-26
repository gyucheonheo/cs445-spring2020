package Entity.Bounded.Trip;

import Entity.Boundary.Trip.Car.Car;
import Entity.Boundary.Trip.LocationInformation.LocationInformation;
import Entity.Boundary.Trip.Rules.Rules;
import Entity.Boundary.Trip.Trip;

public class NullBoundedTrip implements Trip {
    @Override
    public String getTid() {
        return null;
    }

    @Override
    public void setLocationInformation(LocationInformation l) {

    }

    @Override
    public void setCarInformation(Car c) {

    }

    @Override
    public void setRules(Rules p) {

    }

    @Override
    public LocationInformation getLocationInformation() {
        return null;
    }

    @Override
    public Car getCarInformation() {
        return null;
    }

    @Override
    public Rules getRules() {
        return null;
    }

    @Override
    public boolean isNil(){
        return true;
    }
}
