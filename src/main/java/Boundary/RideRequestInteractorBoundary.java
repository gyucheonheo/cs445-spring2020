package Boundary;

import Entity.Boundary.RideRequest.RideRequest;

public interface RideRequestInteractorBoundary {
    String requestRide(RideRequest request);
    void confirmRide(String driverAid, String rideId, String jid);
    void denyRide(String driverAid, String rideId, String jid);
    RideRequest getRequestByTripId(String tripId);

    int getTotalRidesByAid(String aid);

    RideRequest getRequestByJid(String jid);
}
