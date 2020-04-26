package Entity.Bounded.Trip.Rules;

import Entity.Boundary.Trip.Rules.Rules;

public class BoundedRules implements Rules {
    private int maxPeople;
    private double amount_per_passenger = 0;
    private String conditions;

    public static Rules Make(){
        return new NullBoundedRules();
    }
        protected BoundedRules(){

        }
    public static Rules Make(int maxPeople, double amount_per_passenger, String conditions){
        return new BoundedRules(maxPeople, amount_per_passenger, conditions);
    }
        private BoundedRules(int maxPeople, double amount_per_passenger, String conditions ){
           this.maxPeople = maxPeople;
           this.amount_per_passenger = amount_per_passenger;
           this.conditions = conditions;
        }

    public int getMaxPeople(){
        return this.maxPeople;
    }

    public Double getAmountPerPassenger(){
        return this.amount_per_passenger;
    }

    public String getConditions(){
        return this.conditions;
    }

    public boolean isNil(){
        return false;
    }
}
