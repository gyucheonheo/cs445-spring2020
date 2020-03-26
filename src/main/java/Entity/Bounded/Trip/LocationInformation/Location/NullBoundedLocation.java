package Entity.Bounded.Trip.LocationInformation.Location;

import Entity.Boundary.Trip.LocationInformation.Location.Location;

public class NullBoundedLocation implements Location {
    @Override
    public String getCity() {
        return null;
    }

    @Override
    public String getZip() {
        return null;
    }

    @Override
    public boolean isNil(){ return true; }
}
