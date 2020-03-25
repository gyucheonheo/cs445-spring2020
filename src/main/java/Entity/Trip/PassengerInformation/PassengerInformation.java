package Entity.Trip.PassengerInformation;

import java.util.ArrayList;
import java.util.List;

public class PassengerInformation {
    private int maxPeople;
    private double amount_per_passenger = 0;
    private List<String> conditions;

    public PassengerInformation(int maxPeople, double amount_per_passenger, List<String> conditions ){
       this.maxPeople = maxPeople;
       this.amount_per_passenger = amount_per_passenger;
       this.conditions = new ArrayList<>(conditions);
    }
}
