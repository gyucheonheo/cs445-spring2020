package Interactor;

import Boundary.Trip.TripInteractorBoundary;
import Entity.Boundary.Trip.Car.Car;
import Entity.Boundary.Trip.LocationInformation.LocationInformation;
import Entity.Boundary.Trip.Rules.Rules;
import Entity.Bounded.Trip.BoundedTrip;
import Entity.Boundary.Trip.Trip;

import java.util.HashMap;
import java.util.Map;

public enum TripInteractor implements TripInteractorBoundary {
    INSTANCE;
    private static Map<String, Trip> trips = new HashMap<>();

    public Trip createTrip(LocationInformation locationInformation, Car carInformation, Rules rules) {
        return BoundedTrip.Make(locationInformation, carInformation, rules);
    }

    public void registerTrip(Trip t){
        trips.put(t.getTid(), t);
    }

    public void updateTrip(String tid, LocationInformation locationInformation, Car carInformation, Rules rules) {
       if(!trips.containsKey(tid)){
           throw new NotFoundByTripIdException();
       }
       Trip currentTrip = trips.get(tid);
       currentTrip.setCarInformation(carInformation);
       currentTrip.setLocationInformation(locationInformation);
       currentTrip.setRules(rules);
    }

    public void deleteTrip(String tid) {
        if(!trips.containsKey(tid)){
            throw new NotFoundByTripIdException();
        }
        trips.remove(tid);
    }

    public Map<String, Trip> getAllTrips() {
        return trips;
    }

    public Trip getTripById(String tid) {
        if(!trips.containsKey(tid)){
            throw new NotFoundByTripIdException();
        }
        return trips.get(tid);
    }

    public void searchTrip() {

    }

}
