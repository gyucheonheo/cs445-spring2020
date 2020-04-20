package Interactor.Trip;


import Entity.Boundary.Trip.Trip;

import java.util.ArrayList;
import java.util.List;

public enum TripInteractor {
    INSTANCE;
    private static List<Trip> trips = new ArrayList<>();

    public List<Trip> getTrips() {
        return trips;
    }
}
