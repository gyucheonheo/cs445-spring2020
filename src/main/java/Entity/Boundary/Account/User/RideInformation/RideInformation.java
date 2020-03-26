package Entity.Boundary.Account.User.RideInformation;

import Entity.Boundary.Account.User.RideInformation.Rate.Rate;

import java.util.List;

public interface RideInformation {
    List<Rate> getRates();
    double getAverageRating();
    int getRides();
    int getRatings();
    boolean isNil();
}
