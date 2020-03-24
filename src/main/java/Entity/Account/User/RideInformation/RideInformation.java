package Entity.Account.User.RideInformation;
import Entity.Rate.Rate;

import java.util.ArrayList;
import java.util.List;

public class RideInformation {
    private int rides;
    private int ratings;
    private double averageRating;
    private List<Rate> rates;

    public RideInformation(){
        this.rides = 0;
        this.averageRating = 0.0;
        this.rates = new ArrayList<>();
        ratings = rates.size();
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
        return this.ratings;
    }
    public boolean isNil(){
        return false;
    }
}
