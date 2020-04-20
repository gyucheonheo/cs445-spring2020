package Interactor.Rate;

import Entity.Boundary.Account.User.RideInformation.Rate.Rate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum RateInteractor {
    INSTANCE;
    private Map<String, List<Rate>> rateToDriver = new HashMap<>();
    private Map<String, List<Rate>> rateToRider = new HashMap<>();


    public Map<String, List<Rate>> getDriverRate() {
        return this.rateToDriver;
    }

    public Map<String, List<Rate>> getRiderRate() {
        return this.rateToRider;
    }
}
