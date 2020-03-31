package Interactor;

import Boundary.Account.User.AccountInteractorBoundary;
import Boundary.Trip.TripInteractorBoundary;
import Entity.Boundary.Trip.Car.Car;
import Entity.Boundary.Trip.DateTimeFormat.DateTimeFormat;
import Entity.Boundary.Trip.LocationInformation.LocationInformation;
import Entity.Boundary.Trip.Rules.Rules;
import Entity.Bounded.Trip.BoundedTrip;
import Entity.Boundary.Trip.Trip;

import java.util.HashMap;
import java.util.Map;

public enum TripInteractor implements TripInteractorBoundary {
    INSTANCE;
    private static Map<String, Trip> trips = new HashMap<>();

    public Trip createTrip(String aid, LocationInformation locationInformation, Car carInformation, DateTimeFormat dt, Rules rules) {
        return BoundedTrip.Make(aid, locationInformation, carInformation, dt, rules);
    }

    public void registerTrip(Trip t){
        trips.put(t.getTid(), t);
    }

    public void updateTrip(String aid, String tid, LocationInformation locationInformation, Car carInformation, DateTimeFormat dt, Rules rules) {
        AccountInteractorBoundary ab = AccountInteractor.INSTANCE;
        if(!trips.containsKey(tid)){
           throw new NotFoundByTripIdException();
        }
        if(!ab.getUserById(aid).getAid().equals(trips.get(tid).getAid())){
            throw new UserDoNotHavePermissionToUpdateTrip();
        }
        Trip currentTrip = trips.get(tid);
        currentTrip.setCarInformation(carInformation);
        currentTrip.setLocationInformation(locationInformation);
        currentTrip.setRules(rules);
        currentTrip.setDateTimeFormat(dt);
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
