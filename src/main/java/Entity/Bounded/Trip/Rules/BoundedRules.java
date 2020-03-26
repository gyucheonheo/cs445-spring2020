package Entity.Bounded.Trip.Rules;

import Entity.Boundary.Trip.Rules.Rules;

import java.util.ArrayList;
import java.util.List;

public class BoundedRules implements Rules {
    private int maxPeople;
    private double amount_per_passenger = 0;
    private List<String> conditions;

    public static Rules Make(){
        return new NullBoundedRules();
    }
    public static Rules Make(int maxPeople, double amount_per_passenger, List<String> conditions){
        return new BoundedRules(maxPeople, amount_per_passenger, conditions);
    }
        private BoundedRules(int maxPeople, double amount_per_passenger, List<String> conditions ){
           this.maxPeople = maxPeople;
           this.amount_per_passenger = amount_per_passenger;
           this.conditions = new ArrayList<>(conditions);
        }

    public int getMaxPeople(){
        return this.maxPeople;
    }

    public double getAmountPerPassenger(){
        return this.amount_per_passenger;
    }

    public List<String> getConditions(){
        return this.conditions;
    }

    public boolean isNil(){
        return false;
    }
}
