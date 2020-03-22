package Boundary.User.Driver;

public interface DriverBoundary {
    void postRide();
    void responseNote(Note n, /* More data */);
    String getRiderPicture(String nid);
}


