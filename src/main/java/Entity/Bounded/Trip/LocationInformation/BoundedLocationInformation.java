package Entity.Bounded.Trip.LocationInformation;

import Entity.Boundary.Trip.LocationInformation.Location.Location;
import Entity.Boundary.Trip.LocationInformation.LocationInformation;

public class BoundedLocationInformation implements LocationInformation {
    private Location startingPoint;
    private Location endingPoint;

    public static LocationInformation Make(){
        return new NullBoundedLocationInformation();
    }

    public static LocationInformation Make(Location startingPoint, Location endingPoint){
        if(startingPoint.isNil() || endingPoint.isNil()){
            throw new PassingNullLocationNotAllowedException();
        }
        return new BoundedLocationInformation(startingPoint, endingPoint);
    }
        private BoundedLocationInformation(Location startingPoint, Location endingPoint){
            this.startingPoint = startingPoint;
            this.endingPoint = endingPoint;
        }

    public Location getStartingPoint(){
        return this.startingPoint;
    }
    public Location getEndingPoint(){
        return this.endingPoint;
    }
    public boolean isNil() { return false; }
}
