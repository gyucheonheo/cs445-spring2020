package Boundary;

import Entity.Boundary.Account.User.RideInformation.Rate.Rate;
import com.google.gson.JsonObject;

import java.util.List;

public interface RateInteractorBoundary {
    Rate getDriverRateByRateId(String driverId, String rateId);
    Rate getRiderRateByRateId(String riderId, String rateId);
    List<Rate> getDriverRatesByAccountId(String aid);
    List<Rate> getRiderRatesByAccountId(String aid);
    Double getDriverAverageRatingByAccountId(String aid);
    Double getRiderAverageRatingByAccountId(String aid);
    Rate createRate(String rid, String sentBy, int rating, String comment);
    void rateAccount(String rid, Rate r);
}
