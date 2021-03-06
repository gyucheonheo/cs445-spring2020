package Interactor.Rate;

import Boundary.Rate.RateInteractorCommandBoundary;
import Boundary.RideRequest.RideRequestInteractorQueryBoundary;
import Boundary.Trip.TripInteractorQueryBoundary;
import Entity.Boundary.Account.User.RideInformation.Rate.Rate;
import Entity.Boundary.RideRequest.RideRequest;
import Entity.Boundary.Trip.Trip;
import Interactor.RideRequest.RideRequestInteractorQuery;
import Interactor.Trip.TripInteractorQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public enum RateInteractorCommand implements RateInteractorCommandBoundary {
    INSTANCE;
    private Map<String, List<Rate>> rateToDriver = RateInteractor.INSTANCE.getDriverRate();
    private Map<String, List<Rate>> rateToRider = RateInteractor.INSTANCE.getRiderRate();

    public void rateAccount(String tripId, Rate r){
        if( this.isAidRiderToTripId(tripId, r.getSentBy()) ){
            rateRider(tripId, r);
        } else {
            rateDriver(tripId, r);
        }
    }

        private boolean isAidRiderToTripId(String tripId, String aid){
            RideRequestInteractorQueryBoundary rri = RideRequestInteractorQuery.INSTANCE;
            RideRequest request = rri.getRequestByTripId(tripId);
            try {
                return !request.getAid().equals(aid);
            } catch(Exception e){
                return false;
            }
        }
        private void rateDriver(String tripId, Rate r){
            TripInteractorQueryBoundary ti = TripInteractorQuery.INSTANCE;
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

        private void rateRider(String tid, Rate r) {

            RideRequestInteractorQueryBoundary rri = RideRequestInteractorQuery.INSTANCE;
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
}
