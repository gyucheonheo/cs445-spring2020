package Interactor.RideRequest;

import Boundary.RideRequest.RideRequestInteractorCommandBoundary;
import Entity.Boundary.RideRequest.RideRequest;
import Entity.Bounded.RideRequest.BoundedRideRequest;

import java.util.List;

public enum RideRequestInteractorCommand implements RideRequestInteractorCommandBoundary {
    INSTANCE;
    private static List<RideRequest> requests = RideRequestInteractor.INSTANCE.getRequests();

    public void confirmRide(String driverId, String rideId, String jid) {
        wrappedConfirmRide(jid);
    }
        private void wrappedConfirmRide(String jid){
            RideRequest r = this.wrappedGetRequestByJid(jid);
            r.setIsRideConfirmed(true);
        }

    public void denyRide(String driverId, String rideId, String jid) {
        wrappedDenyRide(jid);
    }
        private void wrappedDenyRide(String jid){
            RideRequest r = this.wrappedGetRequestByJid(jid);
            r.setIsRideConfirmed(false);
        }
        private RideRequest wrappedGetRequestByJid(String jid){
            for(RideRequest r : requests){
                if(r.getJid().equals(jid)){
                    return r;
                }
            }
            return BoundedRideRequest.Make();
        }

}
