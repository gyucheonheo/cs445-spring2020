package Entity.Trip;

import Entity.Trip.Condition.Condition;
import Lib.UniqueId;

import java.util.Date;
import java.util.List;

public class Trip {
    private String tid;
    private Location startingPoint;
    private Location endingPoint;
    private Date departureTime;
    private int maxPeople;
    private int fee = 0;
    // I could use Decoration Design Pattern for conditions.
    private List<Condition> conditions;

    public Trip(){
        this.tid = UniqueId.getUniqueID();
    }

    public boolean isNil(){
        return false;
    }
}
