package Entity.Boundary.Trip.LocationInformation;

import Entity.Boundary.Trip.LocationInformation.Location.Location;

public interface LocationInformation {
    boolean isNil();
    Location getStartingPoint();
    Location getEndingPoint();

    class PassingNullLocationNotAllowedException extends RuntimeException{
    }
}
