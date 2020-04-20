package Boundary.Trip;

import Entity.Boundary.Trip.Car.Car;
import Entity.Boundary.Trip.DateTimeFormat.DateTimeFormat;
import Entity.Boundary.Trip.LocationInformation.LocationInformation;
import Entity.Boundary.Trip.Rules.Rules;
import Entity.Boundary.Trip.Trip;

public interface TripInteractorCommandBoundary {
    void registerTrip(Trip t);
    void updateTrip(String tid, LocationInformation locationInformation, Car carInformation, DateTimeFormat dt , Rules rules);
    void deleteTrip(String tid);
}
