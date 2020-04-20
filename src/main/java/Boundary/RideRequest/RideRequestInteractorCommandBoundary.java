package Boundary.RideRequest;

public interface RideRequestInteractorCommandBoundary {
    void confirmRide(String driverAid, String rideId, String jid);
    void denyRide(String driverAid, String rideId, String jid);
}
