package Interactor;

import Boundary.TripInteractorBoundary;
import Entity.Boundary.Trip.Car.Car;
import Entity.Boundary.Trip.DateTimeFormat.DateTimeFormat;
import Entity.Boundary.Trip.LocationInformation.LocationInformation;
import Entity.Boundary.Trip.Rules.Rules;
import Entity.Bounded.Trip.BoundedTrip;
import Entity.Boundary.Trip.Trip;

import java.util.ArrayList;
import java.util.List;

public enum TripInteractor implements TripInteractorBoundary {
    INSTANCE;
    private static List<Trip> trips = new ArrayList<>();

    public Trip createTrip(String aid, LocationInformation locationInformation, Car carInformation, DateTimeFormat dt, Rules rules) {
        return BoundedTrip.Make(aid, locationInformation, carInformation, dt, rules);
    }

    public void registerTrip(Trip t){
        wrappedRegisterTrip(t);
    }
        private void wrappedRegisterTrip(Trip t){
            trips.add(t);
        }

    public void updateTrip(String aid, String tid, LocationInformation locationInformation, Car carInformation, DateTimeFormat dt, Rules rules) {
        wrappedUpdateTrip(aid, tid, locationInformation, carInformation, dt, rules);
    }
       private void wrappedUpdateTrip(String aid, String tid, LocationInformation locationInformation, Car carInformation, DateTimeFormat dt, Rules rules){
           Trip currentTrip = getTripById(tid);
           currentTrip.setCarInformation(carInformation);
           currentTrip.setLocationInformation(locationInformation);
           currentTrip.setRules(rules);
           currentTrip.setDateTimeFormat(dt);
       }
    public void deleteTrip(String tid) {
        wrappedDeleteTrip(tid);
    }
        private void wrappedDeleteTrip(String tid){
            Trip t = getTripById(tid);
            trips.remove(t);
        }
    public List<Trip> getAllTrips() {
        return trips;
    }

    public Trip getTripById(String tid) {
        return this.wrappedGetTripById(tid);
    }
        private Trip wrappedGetTripById(String tid){
            for(Trip t : trips){
                if(t.getTid().equals(tid)){
                    return t;
                }
            }
            return BoundedTrip.Make();
        }
    public void searchTrip() {

    }

}
