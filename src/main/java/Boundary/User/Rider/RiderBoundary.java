package Boundary.User.Rider;

public interface RiderBoundary {
    void getAvailableTripsBetween(Date start, Date end);
    void postNoteToTrip(Trip trip, Note note);
    void getDriverInformation(/*Something*/);
}
