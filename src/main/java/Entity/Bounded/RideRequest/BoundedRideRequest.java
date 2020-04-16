package Entity.Bounded.RideRequest;

import Entity.Boundary.RideRequest.RideRequest;
import Lib.UniqueId;

public class BoundedRideRequest implements RideRequest{
    private String aid;
    private String rideId;
    private String jid;
    private int passengers;
    private Boolean isRideConfirmed;
    private Boolean isPickUpConfirmed;

    public static RideRequest Make(){
        return new NullBoundedRideRequest();
    }
        protected BoundedRideRequest(){

        }
    public static RideRequest Make(String aid, String rideId, int passengers){
        return new BoundedRideRequest(aid, rideId, passengers);
    }
        private BoundedRideRequest(String aid, String rideId, int passengers){
            this.aid = aid;
            this.rideId = rideId;
            this.jid = UniqueId.getUniqueID();
            this.passengers = passengers;
            this.isRideConfirmed = null;
            this.isPickUpConfirmed = null;
        }
    public String getAid(){ return this.aid;}
    public String getJid(){ return this.jid; }
    public String getRideId() { return this.rideId; }
    public int getPassengers(){ return this.passengers; }
    public Boolean getIsRideConfirmed(){ return this.isRideConfirmed; }
    public Boolean getIsPickUpConfirmed(){ return this.isPickUpConfirmed;}

    public void setIsRideConfirmed(Boolean b){ this.isRideConfirmed = b;}
    public void setIsPickUpConfirmed(Boolean b){ this.isPickUpConfirmed = b;}
    public boolean isNil(){
        return false;
    }
}
