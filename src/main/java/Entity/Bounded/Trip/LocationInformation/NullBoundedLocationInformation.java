package Entity.Bounded.Trip.LocationInformation;

import Entity.Boundary.Trip.LocationInformation.Location.Location;
import Entity.Boundary.Trip.LocationInformation.LocationInformation;

public class NullBoundedLocationInformation implements LocationInformation {
    @Override
    public boolean isNil(){ return true;}

    @Override
    public Location getStartingPoint() {
        return null;
    }

    @Override
    public Location getEndingPoint() {
        return null;
    }
}
