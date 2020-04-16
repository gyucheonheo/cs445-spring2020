package Interactor.RideRequest;

import Boundary.RideRequestInteractorBoundary;
import Boundary.TripInteractorBoundary;
import Entity.Boundary.RideRequest.RideRequest;
import Entity.Bounded.RideRequest.BoundedRideRequest;
import Interactor.Trip.TripInteractor;

import java.util.ArrayList;
import java.util.List;

public enum RideRequestInteractor implements RideRequestInteractorBoundary {
    INSTANCE;
    private static List<RideRequest> requests = new ArrayList<>();
    private TripInteractorBoundary tb = TripInteractor.INSTANCE;

    public String requestRide(RideRequest request){
        return this.wrappedRequestRide(request);
    }
        private String wrappedRequestRide(RideRequest request){
            tb.getTripById(request.getRideId());
            requests.add(request);
            return request.getJid();
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

        private RideRequest wrappedGetRequestByTripId(String tripId){
            for(RideRequest r : requests){
                if(r.getRideId().equals(tripId)){
                    return r;
                }
            }
            return BoundedRideRequest.Make();
        }

    public RideRequest getRequestByJid(String jid) {
        return wrappedGetRequestByJid(jid);
    }
        private RideRequest wrappedGetRequestByJid(String jid){
                for(RideRequest r : requests){
                    if(r.getJid().equals(jid)){
                        return r;
                    }
                }
                return BoundedRideRequest.Make();
            }


    public int getTotalRidesByAid(String aid) {
        return wrappedGetTotalRidesByAid(aid);
    }

        private int wrappedGetTotalRidesByAid(String aid){
                int total = 0;
                for(RideRequest r : requests){
                    if(r.getAid().equals(aid) && (r.getIsPickUpConfirmed() != null && r.getIsPickUpConfirmed())) {
                        total += 1;
                    }
                }
                return total;
            }

    public List<RideRequest> getRequests() {
        return requests;
    }
}