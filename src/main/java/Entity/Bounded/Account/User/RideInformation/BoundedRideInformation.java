package Entity.Bounded.Account.User.RideInformation;
import Entity.Boundary.Account.User.RideInformation.Rate.Rate;
import Entity.Boundary.Account.User.RideInformation.RideInformation;

import java.util.ArrayList;
import java.util.List;

public class BoundedRideInformation implements RideInformation {
    private int rides;
    private double averageRating;
    private List<Rate> rates;

    public static RideInformation Make(){
        return new BoundedRideInformation();
    }
        protected BoundedRideInformation(){
            this.rides = 0;
            this.averageRating = 0.0;
            this.rates = new ArrayList<>();
        }

    public List<Rate> getRates(){
        return this.rates;
    }
    public double getAverageRating(){
        return this.averageRating;
    }
    public int getRides(){
        return this.rides;
    }
    public int getRatings(){
        return this.rates.size();
    }
    public boolean isNil(){
        return false;
    }
}
