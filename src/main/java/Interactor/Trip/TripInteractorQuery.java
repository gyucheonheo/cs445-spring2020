package Interactor.Trip;

import Boundary.RideRequest.RideRequestInteractorQueryBoundary;
import Boundary.Trip.TripInteractorQueryBoundary;
import Entity.Boundary.RideRequest.RideRequest;
import Entity.Boundary.Trip.Car.Car;
import Entity.Boundary.Trip.DateTimeFormat.DateTimeFormat;
import Entity.Boundary.Trip.LocationInformation.LocationInformation;
import Entity.Boundary.Trip.Rules.Rules;
import Entity.Boundary.Trip.Trip;
import Entity.Bounded.Trip.BoundedTrip;
import Interactor.RideRequest.RideRequestInteractorQuery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public enum TripInteractorQuery implements TripInteractorQueryBoundary {
    INSTANCE;
    private static List<Trip> trips = TripInteractor.INSTANCE.getTrips();

    private RideRequestInteractorQueryBoundary rri = RideRequestInteractorQuery.INSTANCE;

    public Trip createTrip(String aid, LocationInformation locationInformation, Car carInformation, DateTimeFormat dt, Rules rules) {
        return BoundedTrip.Make(aid, locationInformation, carInformation, dt, rules);
    }

    public List<Trip> getAllTrips(String from, String to, String date) {
        return wrappedGetAllTrips(from, to, date);
    }

        /* TODO Cyclometic Complexity is too high (Reason : Missing Polymorphism) */
        private List<Trip> wrappedGetAllTrips(String from, String to, String date) {

            final String from_regex = convertToRegex(from);
            final String to_regex = convertToRegex(to);
            final String date_regex = convertToRegex(date);
            List<Trip> results;
            results = trips.stream().filter(trip ->
                    trip.getLocationInformation().getStartingPoint().getCity().toLowerCase().matches(from_regex) &&
                    trip.getLocationInformation().getEndingPoint().getCity().toLowerCase().matches(to_regex) &&
                    trip.getDateTimeFormat().getDate().toLowerCase().matches(date_regex))
                    .collect(Collectors.toList());
            return results;
        }

    private String convertToRegex(String data) {
            if( data == null || data.isEmpty()){
                return "[a-zA-Z0-9\\- ]*";
            }
            return data.toLowerCase();
    }

    public Trip getTripById(String tid) {
        return this.wrappedGetTripById(tid);
    }

        private Trip wrappedGetTripById(String tid) {
            for(Trip t : trips){
                if(t.getTid().equals(tid)){
                    return t;
                }
            }
            return BoundedTrip.Make();
        }

    public List<Trip> getTripByUserId(String aid) {
        return this.wrappedGetTripByUserId(aid);
    }
        private List<Trip> wrappedGetTripByUserId(String aid){
            List<Trip> result = new ArrayList<>();
            for(Trip t : trips){
                if(t.getAid().equals(aid)){
                    result.add(t);
                }
            }
            return result;
        }
    public List<Trip> getPostingTripsBetweenDates(String start_date, String end_date) throws ParseException {
        Date start = convertStartingDate(start_date);
        Date end = convertEndingDate(end_date);
        List<Trip> result = new ArrayList<>();
        for(Trip t : trips){
            Date tripDate = new SimpleDateFormat("dd-MMM-yyyy").parse(t.getDateTimeFormat().getDate());
            if(!tripDate.before(start) && !tripDate.after(end)){
                result.add(t);
            }
        }
        return result;
    }
        private Date convertStartingDate(String date) throws ParseException {
            if(date.isEmpty()){
                return new Date(Long.MIN_VALUE);
            }
            return new SimpleDateFormat("dd-MMM-yyyy").parse(date);
        }
        private Date convertEndingDate(String date) throws ParseException {
            if(date.isEmpty()){
                return new Date(Long.MAX_VALUE);
            }
            return new SimpleDateFormat("dd-MMM-yyyy").parse(date);
        }

    private List<Trip> getTripsFromStartDateOnly(String start_date) throws ParseException {
        List<Trip> result = new ArrayList<>();
        for(Trip t : trips){
            Date start = new SimpleDateFormat("dd-MMM-yyyy").parse(start_date);
            Date tripDate = new SimpleDateFormat("dd-MMM-yyyy").parse(t.getDateTimeFormat().getDate());
            if(tripDate.after(start) || tripDate.equals(start)){
                result.add(t);
            }
        }
        return result;
    }

    private List<Trip> getTripsToEndDateOnly(String end_date) throws ParseException {
        List<Trip> result = new ArrayList<>();
        for(Trip t : trips){
            Date end = new SimpleDateFormat("dd-MMM-yyyy").parse(end_date);
            Date tripDate = new SimpleDateFormat("dd-MMM-yyyy").parse(t.getDateTimeFormat().getDate());
            if(tripDate.before(end) || tripDate.equals(end)){
                result.add(t);
            }
        }
        return result;
    }

    public List<Trip> getTakingTripsBetweenDates(String start_date, String end_date) throws ParseException {
        List<Trip> result = new ArrayList<>();
        Date start = convertStartingDate(start_date);
        Date end = convertEndingDate(end_date);
        for(Trip t : trips){
            Date tripDate = new SimpleDateFormat("dd-MMM-yyyy").parse(t.getDateTimeFormat().getDate());
            RideRequestInteractorQueryBoundary rri_t = RideRequestInteractorQuery.INSTANCE;
            if((!tripDate.before(start) && !tripDate.after(end))
                    &&  (!rri_t.getRequestByTripId(t.getTid()).isNil()
                    && rri_t.getRequestByTripId(t.getTid()).getIsPickUpConfirmed())
            ){
                result.add(t);
            }
        }
        return result;
    }

    public int getTotalRidesByAid(String aid) {
        return wrappedGetTotalRidesByAid(aid);
    }
        private int wrappedGetTotalRidesByAid(String aid){
            int total = 0;
            RideRequestInteractorQueryBoundary rri_temp = RideRequestInteractorQuery.INSTANCE;
            for(Trip t : trips){
                RideRequest req = rri_temp.getRequestByTripId(t.getTid());
                if(t.getAid().equals(aid) && ( req.getIsPickUpConfirmed() != null)){
                    total++;
                }
            }

            return total;
        }

        private List<Trip> getTakingTripsFromStartDateOnly(String start_date) throws ParseException {
            List<Trip> result = new ArrayList<>();
            for(Trip t : trips){
                Date start = new SimpleDateFormat("dd-MMM-yyyy").parse(start_date);
                Date tripDate = new SimpleDateFormat("dd-MMM-yyyy").parse(t.getDateTimeFormat().getDate());
                if(tripDate.after(start)
                        &&  (!rri.getRequestByTripId(t.getTid()).isNil() && rri.getRequestByTripId(t.getTid()).getIsPickUpConfirmed())
                ){
                    result.add(t);
                }
            }
            return result;
        }
            private Date parseStringDateToDate(String date) throws ParseException {
                return new SimpleDateFormat("dd-MMM-yyyy").parse(date);
            }
        private List<Trip> getTakingTripsToEndDateOnly(String end_date) throws ParseException {
            List<Trip> result = new ArrayList<>();
            for(Trip t : trips){
                Date end = new SimpleDateFormat("dd-MMM-yyyy").parse(end_date);
                Date tripDate = new SimpleDateFormat("dd-MMM-yyyy").parse(t.getDateTimeFormat().getDate());
                if(tripDate.before(end)
                        &&  (!rri.getRequestByTripId(t.getTid()).isNil() && rri.getRequestByTripId(t.getTid()).getIsPickUpConfirmed())
                ){
                    result.add(t);
                }
            }
            return result;
        }
}
