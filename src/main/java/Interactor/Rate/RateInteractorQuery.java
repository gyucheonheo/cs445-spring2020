package Interactor.Rate;

import Boundary.Rate.RateInteractorQueryBoundary;
import Entity.Boundary.Account.User.RideInformation.Rate.Rate;
import Entity.Bounded.Account.User.RideInformation.Rate.BoundedRate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public enum RateInteractorQuery implements RateInteractorQueryBoundary {
    INSTANCE;
    private Map<String, List<Rate>> rateToDriver = RateInteractor.INSTANCE.getDriverRate();
    private Map<String, List<Rate>> rateToRider = RateInteractor.INSTANCE.getRiderRate();

    public Rate createRate(String rid, String date, String sentBy, int rating, String comment) {
        return BoundedRate.Make(rid, date, sentBy, rating, comment);
    }

    public Rate getDriverRateByRateId(String driverId, String rateId) {
        return wrappedGetDriverRateByRateId(driverId, rateId);
    }

    private Rate wrappedGetDriverRateByRateId(String driverId, String rateId) {
        List<Rate> rates = rateToDriver.getOrDefault(driverId, new ArrayList<>());
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
        List<Rate> rates = rateToRider.getOrDefault(riderId, new ArrayList<>());
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

}
