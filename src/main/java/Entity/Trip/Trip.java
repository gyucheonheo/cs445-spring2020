package Entity.Trip;

import Entity.Trip.Car.Car;
import Entity.Trip.Condition.Condition;
import Entity.Trip.DateTimeFormat.DateTimeFormat;
import Entity.Trip.LocationInformation.LocationInformation;
import Lib.UniqueId;

import java.util.List;

public class Trip {
    private String tid;
    private LocationInformation locationInformation;
    private DateTimeFormat dateTimeInformation;
    private Car carInformation;
    private int maxPeople;
    private double amount_per_passenger = 0;
    private List<Condition> conditions;

    public Trip(){

    }
    public Trip(LocationInformation locationInformation, DateTimeFormat dateInformation, Car car, int maxPeople, double amount_per_passenger, List<Condition> conditions){
        this.tid = UniqueId.getUniqueID();

    }

    public boolean isNil(){
        return false;
    }
}
