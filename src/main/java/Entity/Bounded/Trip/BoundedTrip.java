package Entity.Bounded.Trip;

import Entity.Boundary.Trip.Car.Car;
import Entity.Boundary.Trip.DateTimeFormat.DateTimeFormat;
import Entity.Boundary.Trip.LocationInformation.LocationInformation;
import Entity.Boundary.Trip.Rules.Rules;
import Entity.Boundary.Trip.Trip;
import Entity.Bounded.Trip.DateTimeFormat.BoundedDateTimeFormat;
import Lib.UniqueId;

public class BoundedTrip implements Trip {
    private String tid;
    private LocationInformation locationInformation;
    private DateTimeFormat dateTimeInformation;
    private Car carInformation;
    private Rules rules;

    public static Trip Make(){
        return new NullBoundedTrip();
    }

    public static Trip Make(LocationInformation locationInformation, Car carInformation, Rules rules){
        return new BoundedTrip(locationInformation, carInformation, rules);
    }
        private BoundedTrip(LocationInformation locationInformation, Car carInformation, Rules rules){
            dateTimeInformation = BoundedDateTimeFormat.Make("DD-MM-YYYY,HH:MM", ",");
            this.tid = UniqueId.getUniqueID();
            setLocationInformation(locationInformation);
            setCarInformation(carInformation);
            setRules(rules);
       }

    public String getTid(){
        return this.tid;
    }

    public void setLocationInformation(LocationInformation locationInformation){
        this.locationInformation = locationInformation;
    }
    public void setCarInformation(Car carInformation){
        this.carInformation = carInformation;
    }
    public void setRules(Rules rules){
        this.rules = rules;
    }
    public LocationInformation getLocationInformation(){
        return this.locationInformation;
    }
    public Car getCarInformation(){
        return this.carInformation;
    }
    public Rules getRules(){
        return this.rules;
    }
    public boolean isNil(){
        return false;
    }
}
