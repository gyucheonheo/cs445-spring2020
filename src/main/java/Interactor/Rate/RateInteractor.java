package Interactor.Rate;

import Boundary.RateInteractorBoundary;
import Boundary.RideRequestInteractorBoundary;
import Boundary.TripInteractorBoundary;
import Entity.Boundary.Account.User.RideInformation.Rate.Rate;
import Entity.Boundary.RideRequest.RideRequest;
import Entity.Boundary.Trip.Trip;
import Entity.Bounded.Account.User.RideInformation.Rate.BoundedRate;
import Interactor.RideRequest.RideRequestInteractor;
import Interactor.Trip.TripInteractor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum RateInteractor implements RateInteractorBoundary {
    INSTANCE;
    private Map<String, List<Rate>> rateToDriver = new HashMap<>();
    private Map<String, List<Rate>> rateToRider = new HashMap<>();


    public Rate createRate(String rid, String sentBy, int rating, String comment) {
        return BoundedRate.Make(rid, sentBy, rating, comment);
    }
    public void rateAccount(String tripId, Rate r){
        if( this.isAidRiderToTripId(tripId, r.getSentBy()) ){
            rateRider(tripId, r);
        } else {
            rateDriver(tripId, r);
        }
    }
        private boolean isAidRiderToTripId(String tripId, String aid){
           RideRequestInteractorBoundary rri = RideRequestInteractor.INSTANCE;
           RideRequest request = rri.getRequestByTripId(tripId);
            if(request.getAid().equals(aid)){
                return false;
            } else {
                return true;
            }
        }
        private void rateDriver(String tripId, Rate r){
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

        private void rateRider(String tid, Rate r) {

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
    public List<Rate> getDriverRatesByAccountId(String aid){
        return wrappedGetDriverRatesByAccountId(aid);
    }
        private List<Rate> wrappedGetDriverRatesByAccountId(String aid){
            return rateToDriver.getOrDefault(aid, new ArrayList<>());
        }

    public List<Rate> getRiderRatesByAccountId(String aid){
        return wrappedGetRiderRatesByAccountId(aid);
    }
        private List<Rate> wrappedGetRiderRatesByAccountId(String aid){
            return rateToRider.getOrDefault(aid, new ArrayList<>());
        }

    public Double getDriverAverageRatingByAccountId(String aid){
        List<Rate> itsRates = getDriverRatesByAccountId(aid);
        int size = itsRates.size();
        if(size == 0){
            return null;
        }
        double averageRating = 0.0;
        for(Rate r : itsRates){
            averageRating = averageRating + (double)r.getRating();
        }
        averageRating = averageRating / (double)size;

        return averageRating;
    }

    public Double getRiderAverageRatingByAccountId(String aid){
        List<Rate> itsRates = getRiderRatesByAccountId(aid);
        int size = itsRates.size();
        if(size == 0){
            return null;
        }
        double averageRating = 0.0;
        for(Rate r : itsRates){
            averageRating = averageRating + (double)r.getRating();
        }
        averageRating = averageRating / (double)size;

        return averageRating;
    }

    public Map<String, List<Rate>> getDriverRate() {
        return this.rateToDriver;
    }

    public Map<String, List<Rate>> getRiderRate() {
        return this.rateToRider;
    }
}
