package Entity.Bounded.RideRequest;

import Entity.Boundary.RideRequest.RideRequest;
import Lib.UniqueId;

public class BoundedRideRequest implements RideRequest{
    private String aid;
    private String jid;
    private int passengers;
    private Boolean isRideConfirmed;
    private Boolean isPickUpConfirmed;

    public static RideRequest Make(String aid, int passengers){
        return new BoundedRideRequest(aid, passengers);
    }
        private BoundedRideRequest(String aid, int passengers){
            this.aid = aid;
            this.jid = UniqueId.getUniqueID();
            this.passengers = passengers;
            this.isRideConfirmed = null;
            this.isPickUpConfirmed = null;
        }
    public String getAid(){ return this.aid;}
    public String getJid(){ return this.jid; }
    public int getPassengers(){ return this.passengers; }
    public Boolean getIsRideConfirmed(){ return this.isRideConfirmed; }
    public Boolean getIsPickUpConfirmed(){ return this.isPickUpConfirmed;}

    public void setIsRideConfirmed(Boolean b){ this.isRideConfirmed = b;}
    public void setIsPickUpConfirmed(Boolean b){ this.isPickUpConfirmed = b;}
}
