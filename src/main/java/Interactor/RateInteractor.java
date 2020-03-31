package Interactor;

import Boundary.RateInteractorBoundary;
import Entity.Boundary.Account.User.RideInformation.Rate.Rate;

import java.util.List;
import java.util.Map;

public enum RateInteractor implements RateInteractorBoundary {
    INSTANCE;
    private Map<String, List<Rate>> rateAsDriver;
    private Map<String, List<Rate>> rateAsRider;
}
