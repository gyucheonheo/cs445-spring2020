package Boundary;

import Entity.Boundary.RideRequest.RideRequest;

public interface RideRequestInteractorBoundary {
    void requestRide(String rideId, RideRequest request);
    void confirmRide(String driverAid, String rideId, String jid);
    void denyRide(String driverAid, String rideId, String jid);

    class JoinRequestNotFoundByJidException extends RuntimeException{
    }

    class UserDoNotHavePermissionToConfirmException extends RuntimeException {
    }

    class UserDoNotHavePermissionToDenyException extends RuntimeException {
    }
}
