package Boundary.Rate;

import Entity.Boundary.Account.User.RideInformation.Rate.Rate;

import java.util.List;

public interface RateInteractorQueryBoundary {
    Rate getDriverRateByRateId(String driverId, String rateId);
    Rate getRiderRateByRateId(String riderId, String rateId);
    List<Rate> getDriverRatesByAccountId(String aid);
    List<Rate> getRiderRatesByAccountId(String aid);
    Double getDriverAverageRatingByAccountId(String aid);
    Double getRiderAverageRatingByAccountId(String aid);
    Rate createRate(String rid, String date, String sentBy, int rating, String comment);
}
