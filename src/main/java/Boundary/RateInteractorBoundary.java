package Boundary;

import Entity.Boundary.Account.User.RideInformation.Rate.Rate;

public interface RateInteractorBoundary {

    void rateDriver(String tid, Rate r);

    void rateRider(String tid, Rate r);

    Rate getDriverRateByRateId(String driverId, String rateId);
    Rate getRiderRateByRateId(String riderId, String rateId);

}
