package Interactor.RideRequest;

import Boundary.RideRequest.RideRequestInteractorQueryBoundary;
import Boundary.Trip.TripInteractorQueryBoundary;
import Entity.Boundary.RideRequest.RideRequest;
import Entity.Bounded.RideRequest.BoundedRideRequest;
import Interactor.Trip.TripInteractorQuery;

import java.util.List;

public enum RideRequestInteractorQuery implements RideRequestInteractorQueryBoundary {
    INSTANCE;
    private static List<RideRequest> requests = RideRequestInteractor.INSTANCE.getRequests();
    private TripInteractorQueryBoundary tb = TripInteractorQuery.INSTANCE;

    public String requestRide(RideRequest request){
        return this.wrappedRequestRide(request);
    }
    private String wrappedRequestRide(RideRequest request){
        tb.getTripById(request.getRideId());
        requests.add(request);
        return request.getJid();
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

}
