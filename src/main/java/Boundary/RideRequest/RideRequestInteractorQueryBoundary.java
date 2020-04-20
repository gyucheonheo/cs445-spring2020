package Boundary.RideRequest;

import Entity.Boundary.RideRequest.RideRequest;

public interface RideRequestInteractorQueryBoundary {
    String requestRide(RideRequest request);
    RideRequest getRequestByTripId(String tripId);
    RideRequest getRequestByJid(String jid);
    int getTotalRidesByAid(String aid);
}
