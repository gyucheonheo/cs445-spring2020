package Entity.Bounded.Trip;

import Entity.Boundary.Trip.Car.Car;
import Entity.Boundary.Trip.DateTimeFormat.DateTimeFormat;
import Entity.Boundary.Trip.LocationInformation.LocationInformation;
import Entity.Boundary.Trip.Rules.Rules;
import Entity.Boundary.Trip.Trip;
import Lib.UniqueId;

public class BoundedTrip implements Trip {
    private String tid;
    private String aid;
    private LocationInformation locationInformation;
    private DateTimeFormat dateTimeInformation;
    private Car carInformation;
    private Rules rules;

    public static Trip Make(){
        return new NullBoundedTrip();
    }

    public static Trip Make(String aid, LocationInformation locationInformation, Car carInformation, DateTimeFormat dateTime, Rules rules){
        return new BoundedTrip(aid, locationInformation, carInformation, dateTime, rules);
    }
        private BoundedTrip(String aid, LocationInformation locationInformation, Car carInformation, DateTimeFormat dateTime, Rules rules){
            this.aid = aid;
            this.dateTimeInformation = dateTime;
            this.tid = UniqueId.getUniqueID();
            setLocationInformation(locationInformation);
            setCarInformation(carInformation);
            setRules(rules);
       }

    public String getTid(){
        return this.tid;
    }
    public String getAid() { return this.aid; }
    public DateTimeFormat getDateTimeFormat(){ return this.dateTimeInformation; }
    public void setLocationInformation(LocationInformation locationInformation){
        this.locationInformation = locationInformation;
    }
    public void setCarInformation(Car carInformation){
        this.carInformation = carInformation;
    }
    public void setRules(Rules rules){
        this.rules = rules;
    }
    public void setDateTimeFormat(DateTimeFormat dt){ this.dateTimeInformation = dt;}
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
