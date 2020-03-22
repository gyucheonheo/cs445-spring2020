package Interactor;

import Boundary.TripBoundary;

public class TripInteractor implements TripBoundary {
    private final static TripInteractor singleton = new TripInteractor();

    private TripInteractor(){

    }
    public static TripInteractor getInstance(){
        return singleton;
    }
}
