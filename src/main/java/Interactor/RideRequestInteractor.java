package Interactor;

import Boundary.RideRequestInteractorBoundary;
import Boundary.TripInteractorBoundary;
import Entity.Boundary.RideRequest.RideRequest;
import Entity.Bounded.RideRequest.BoundedRideRequest;

import java.util.ArrayList;
import java.util.List;

public enum RideRequestInteractor implements RideRequestInteractorBoundary {
    INSTANCE;
    private static List<RideRequest> requests = new ArrayList<>();
    private TripInteractorBoundary tb = TripInteractor.INSTANCE;

    public void requestRide(RideRequest request){
        this.wrappedRequestRide(request);
    }
        private void wrappedRequestRide(RideRequest request){
            tb.getTripById(request.getRideId());
            requests.add(request);
        }
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
    public RideRequest getRequestByTripId(String tripId) {
        return wrappedGetRequestByTripId(tripId);
    }
        private RideRequest wrappedGetRequestByJid(String jid){
            for(RideRequest r : requests){
                if(r.getJid().equals(jid)){
                    return r;
                }
            }
            return BoundedRideRequest.Make();
        }

        private RideRequest wrappedGetRequestByTripId(String tripId){
            for(RideRequest r : requests){
                if(r.getRideId().equals(tripId)){
                    return r;
                }
            }
            return BoundedRideRequest.Make();
        }
}