package Interactor.RideRequest;

import Entity.Boundary.RideRequest.RideRequest;

import java.util.ArrayList;
import java.util.List;

public enum RideRequestInteractor {
    INSTANCE;
    private static List<RideRequest> requests = new ArrayList<>();

    public List<RideRequest> getRequests() {
        return requests;
    }
}