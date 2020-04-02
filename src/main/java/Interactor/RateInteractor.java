package Interactor;

import Boundary.RateInteractorBoundary;
import Boundary.RideRequestInteractorBoundary;
import Boundary.TripInteractorBoundary;
import Entity.Boundary.Account.User.RideInformation.Rate.Rate;
import Entity.Boundary.RideRequest.RideRequest;
import Entity.Boundary.Trip.Trip;
import Entity.Bounded.Account.User.RideInformation.Rate.BoundedRate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum RateInteractor implements RateInteractorBoundary {
    INSTANCE;
    private Map<String, List<Rate>> rateToDriver = new HashMap<>();
    private Map<String, List<Rate>> rateToRider = new HashMap<>();

    public void rateDriver(String tripId, Rate r){
        TripInteractorBoundary ti = TripInteractor.INSTANCE;
        Trip t = ti.getTripById(tripId);

        String driverAid = t.getAid();

        if(!rateToDriver.containsKey(driverAid)){
            List<Rate> initialRate = new ArrayList<>();
            initialRate.add(r);
            rateToDriver.put(t.getAid(), initialRate);
        } else {
            List<Rate> existingRate = rateToDriver.get(driverAid);
            existingRate.add(r);
            rateToDriver.put(driverAid, existingRate);
        }
    }

    public void rateRider(String tid, Rate r) {
        TripInteractorBoundary ti = TripInteractor.INSTANCE;
        Trip t = ti.getTripById(tid);

        RideRequestInteractorBoundary rri = RideRequestInteractor.INSTANCE;
        RideRequest request = rri.getRequestByTripId(tid);

        String riderAid = request.getAid();

        if(!rateToRider.containsKey(riderAid)){
            List<Rate> initialRate = new ArrayList<>();
            initialRate.add(r);
            rateToRider.put(riderAid, initialRate);
        } else {
            List<Rate> existingRate = rateToRider.get(riderAid);
            existingRate.add(r);
            rateToRider.put(riderAid, existingRate);
        }
    }

    public Rate getDriverRateByRateId(String driverId, String rateId) {
        return wrappedGetDriverRateByRateId(driverId, rateId);
    }

        private Rate wrappedGetDriverRateByRateId(String driverId, String rateId) {
            List<Rate> rates = rateToDriver.get(driverId);
            for(Rate r : rates){
                if(r.getRid().equals(rateId)){
                    return r;
                }
            }
            return BoundedRate.Make();
        }
    public Rate getRiderRateByRateId(String riderId, String rateId) {
        return wrappedGetRiderRateByRateId(riderId, rateId);
    }
        private Rate wrappedGetRiderRateByRateId(String riderId, String rateId) {
            List<Rate> rates = rateToRider.get(riderId);
            for(Rate r : rates){
                if(r.getRid().equals(rateId)){
                    return r;
                }
            }
            return BoundedRate.Make();
        }
}
