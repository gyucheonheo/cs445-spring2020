package Entity.Trip.LocationInformation;

import Entity.Trip.LocationInformation.Location.Location;

public class LocationInformation {
    private Location startingPoint;
    private Location endingPoint;

    public LocationInformation(Location startingPoint, Location endingPoint){
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
    }
}
