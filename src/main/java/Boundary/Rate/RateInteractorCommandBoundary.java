package Boundary.Rate;

import Entity.Boundary.Account.User.RideInformation.Rate.Rate;

public interface RateInteractorCommandBoundary {
    void rateAccount(String rid, Rate r);
}
