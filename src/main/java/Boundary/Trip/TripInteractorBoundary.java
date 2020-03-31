package Boundary.Trip;

import Entity.Boundary.Trip.Car.Car;
import Entity.Boundary.Trip.DateTimeFormat.DateTimeFormat;
import Entity.Boundary.Trip.LocationInformation.LocationInformation;
import Entity.Boundary.Trip.Rules.Rules;
import Entity.Boundary.Trip.Trip;

import java.util.Map;

public interface TripInteractorBoundary {
    Trip createTrip(String aid, LocationInformation locationInformation, Car carInformation, DateTimeFormat dt, Rules rules);
    void registerTrip(Trip t);
    void updateTrip(String aid, String tid, LocationInformation locationInformation, Car carInformation, DateTimeFormat dt ,Rules rules);
    void deleteTrip(String tid);
    Map<String, Trip> getAllTrips();
    Trip getTripById(String tid);
    void searchTrip();

    class NotFoundByTripIdException extends RuntimeException{}

    class UserDoNotHavePermissionToUpdateTrip extends RuntimeException{}
}
