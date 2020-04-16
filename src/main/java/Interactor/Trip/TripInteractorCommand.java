package Interactor.Trip;

import Boundary.Trip.TripInteractorCommandBoundary;
import Entity.Boundary.Trip.Car.Car;
import Entity.Boundary.Trip.DateTimeFormat.DateTimeFormat;
import Entity.Boundary.Trip.LocationInformation.LocationInformation;
import Entity.Boundary.Trip.Rules.Rules;
import Entity.Boundary.Trip.Trip;
import Entity.Bounded.Trip.BoundedTrip;

import java.util.List;

public enum TripInteractorCommand implements TripInteractorCommandBoundary {
    INSTANCE;
    private static List<Trip> trips = TripInteractor.INSTANCE.getTrips();

    public void registerTrip(Trip t) {
        wrappedRegisterTrip(t);
    }

    private void wrappedRegisterTrip(Trip t) {
        trips.add(t);
    }

    public void updateTrip(String tid, LocationInformation locationInformation, Car carInformation, DateTimeFormat dt, Rules rules) {
        wrappedUpdateTrip(tid, locationInformation, carInformation, dt, rules);
    }

    private void wrappedUpdateTrip(String tid, LocationInformation locationInformation, Car carInformation, DateTimeFormat dt, Rules rules) {
        Trip currentTrip = getTripById(tid);
        currentTrip.setCarInformation(carInformation);
        currentTrip.setLocationInformation(locationInformation);
        currentTrip.setRules(rules);
        currentTrip.setDateTimeFormat(dt);
    }

    public void deleteTrip(String tid) {
        wrappedDeleteTrip(tid);
    }

        private void wrappedDeleteTrip(String tid) {
            Trip t = getTripById(tid);
            trips.remove(t);
        }
        private Trip getTripById(String tid) {
            return this.wrappedGetTripById(tid);
        }

        private Trip wrappedGetTripById(String tid) {
            for (Trip t : trips) {
                if (t.getTid().equals(tid)) {
                    return t;
                }
            }
            return BoundedTrip.Make();
        }

}
