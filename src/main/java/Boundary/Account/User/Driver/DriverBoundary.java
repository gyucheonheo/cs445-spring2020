package Boundary.Account.User.Driver;

public interface DriverBoundary {
    void createTrip();
    void updateTrip();
    void deleteTrip();
    void confirmTrip();
    void denyTrip();
    void confirmPickingUp();
}
