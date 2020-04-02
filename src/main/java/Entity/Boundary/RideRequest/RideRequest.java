package Entity.Boundary.RideRequest;

public interface RideRequest {
    String getAid();
    String getJid();
    String getRideId();
    int getPassengers();
    Boolean getIsRideConfirmed();
    Boolean getIsPickUpConfirmed();

    void setIsRideConfirmed(Boolean b);
    void setIsPickUpConfirmed(Boolean b);

}
