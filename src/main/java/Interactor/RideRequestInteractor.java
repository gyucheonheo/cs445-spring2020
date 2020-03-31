package Interactor;

import Boundary.RideRequestInteractorBoundary;
import Boundary.Trip.TripInteractorBoundary;
import Entity.Boundary.RideRequest.RideRequest;

import java.util.HashMap;
import java.util.Map;

public enum RideRequestInteractor implements RideRequestInteractorBoundary {
    INSTANCE;
    private Map<String, RideRequest> requests = new HashMap<>();
    private TripInteractorBoundary tb = TripInteractor.INSTANCE;

    public void requestRide(String rideId , RideRequest request) throws RuntimeException{
        tb.getTripById(rideId);
        requests.put(request.getJid(), request);
    }

    public void confirmRide(String driverId, String rideId, String jid){
        if(!this.requests.containsKey(jid)){
           throw new JoinRequestNotFoundByJidException();
        }
        RideRequest request = requests.get(jid);
        if(!driverId.equals(tb.getTripById(rideId).getAid())){
            throw new UserDoNotHavePermissionToConfirmException();
        }
        request.setIsRideConfirmed(true);
    }

    public void denyRide(String driverId, String rideId, String jid) {
        if(!this.requests.containsKey(jid)){
            throw new JoinRequestNotFoundByJidException();
        }
        RideRequest request = requests.get(jid);
        if(!driverId.equals(tb.getTripById(rideId).getAid())){
            throw new UserDoNotHavePermissionToDenyException();
        }
        request.setIsRideConfirmed(false);
    }

}
