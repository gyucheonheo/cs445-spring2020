package Entity.Trip;

import Entity.Trip.Car.Car;
import Entity.Trip.DateTimeFormat.DateTimeFormat;
import Entity.Trip.LocationInformation.LocationInformation;
import Entity.Trip.PassengerInformation.PassengerInformation;
import Lib.UniqueId;

public class Trip {
    private String tid;
    private LocationInformation locationInformation;
    private DateTimeFormat dateTimeInformation;
    private Car carInformation;
    private PassengerInformation passengerInformation;

    public Trip(){

    }
    public Trip(LocationInformation locationInformation, Car carInformation, PassengerInformation passengerInformation){
        dateTimeInformation = new DateTimeFormat("DD-MM-YYYY,HH:MM");
        this.tid = UniqueId.getUniqueID();
        this.locationInformation = locationInformation;
        this.carInformation = carInformation;
        this.passengerInformation = passengerInformation;
    }

    public boolean isNil(){
        return false;
    }
}
