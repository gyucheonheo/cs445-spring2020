package Interactor.Trip;

import Boundary.RideRequestInteractorBoundary;
import Boundary.TripInteractorBoundary;
import Entity.Boundary.RideRequest.RideRequest;
import Entity.Boundary.Trip.Car.Car;
import Entity.Boundary.Trip.DateTimeFormat.DateTimeFormat;
import Entity.Boundary.Trip.LocationInformation.LocationInformation;
import Entity.Boundary.Trip.Rules.Rules;
import Entity.Bounded.Trip.BoundedTrip;
import Entity.Boundary.Trip.Trip;
import Interactor.RideRequest.RideRequestInteractor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public enum TripInteractor implements TripInteractorBoundary {
    INSTANCE;
    private static List<Trip> trips = new ArrayList<>();
    private RideRequestInteractorBoundary rri = RideRequestInteractor.INSTANCE;
    public Trip createTrip(String aid, LocationInformation locationInformation, Car carInformation, DateTimeFormat dt, Rules rules) {
        return BoundedTrip.Make(aid, locationInformation, carInformation, dt, rules);
    }

    public void registerTrip(Trip t) {
        wrappedRegisterTrip(t);
    }

    private void wrappedRegisterTrip(Trip t) {
        trips.add(t);
    }

    public void updateTrip(String tid, LocationInformation locationInformation, Car carInformation, DateTimeFormat dt, Rules rules) {
        wrappedUpdateTrip(tid, locationInformation, carInformation, dt, rules);
    }

        private void wrappedUpdateTrip(String tid, LocationInformation locationInformation, Car carInformation, DateTimeFormat dt, Rules rules) {
            Trip currentTrip = getTripById(tid);
            currentTrip.setCarInformation(carInformation);
            currentTrip.setLocationInformation(locationInformation);
            currentTrip.setRules(rules);
            currentTrip.setDateTimeFormat(dt);
        }

    public void deleteTrip(String tid) {
        wrappedDeleteTrip(tid);
    }

    private void wrappedDeleteTrip(String tid) {
        Trip t = getTripById(tid);
        trips.remove(t);
    }

    /* TODO Missing Polymorphism => Cyclometic Complexity is too high 47*/
    public List<Trip> getAllTrips(String from, String to, String date) {
        return wrappedGetAllTrips(from, to, date);
    }

        private List<Trip> wrappedGetAllTrips(String from, String to, String date) {
            if (from == null && to == null && date == null) {
                return trips;
            }
            List<Trip> results = new ArrayList<>();
            if (from.isEmpty() && to.isEmpty() && date.isEmpty()) {
                return trips;
            } else if (from.isEmpty() && to.isEmpty() && !date.isEmpty()) {
                for (Trip t : trips) {
                    if (t.getDateTimeFormat().getDate().equalsIgnoreCase(date)) {
                        results.add(t);
                    }
                }
            } else if (from.isEmpty() && !to.isEmpty() && date.isEmpty()) {
                for (Trip t : trips) {
                    if (t.getLocationInformation().getEndingPoint().getCity().equalsIgnoreCase(to)) {
                        results.add(t);
                    }
                }
            } else if (from.isEmpty() && !to.isEmpty() && !date.isEmpty()) {
                for (Trip t : trips) {
                    if (t.getLocationInformation().getEndingPoint().getCity().equalsIgnoreCase(to) &&
                            t.getDateTimeFormat().getDate().equalsIgnoreCase(date)
                    ) {
                        results.add(t);
                    }
                }
            } else if (!from.isEmpty() && to.isEmpty() && date.isEmpty()) {
                for (Trip t : trips) {
                    if (t.getLocationInformation().getStartingPoint().getCity().equalsIgnoreCase(from)) {
                        results.add(t);
                    }
                }
            } else if (!from.isEmpty() && !to.isEmpty() && date.isEmpty()) {
                for (Trip t : trips) {
                    if (t.getLocationInformation().getStartingPoint().getCity().equalsIgnoreCase(from) &&
                            t.getLocationInformation().getEndingPoint().getCity().equalsIgnoreCase(to)
                    ) {
                        results.add(t);
                    }
                }
            } else if (!from.isEmpty() && !to.isEmpty() && !date.isEmpty()) {
                for (Trip t : trips) {
                    if (t.getLocationInformation().getStartingPoint().getCity().equalsIgnoreCase(from) &&
                            t.getLocationInformation().getEndingPoint().getCity().equalsIgnoreCase(to) &&
                            t.getDateTimeFormat().getDate().equalsIgnoreCase(date)
                    ) {
                        results.add(t);
                    }
                }
            } else if (!from.isEmpty() && to.isEmpty() && !date.isEmpty()) {
                for (Trip t : trips) {
                    if (t.getLocationInformation().getStartingPoint().getCity().equalsIgnoreCase(from) &&
                            t.getDateTimeFormat().getDate().equalsIgnoreCase(date)
                    ) {
                        results.add(t);
                    }
                }
            }
            return results;
        }

    public Trip getTripById(String tid) {
        return this.wrappedGetTripById(tid);
    }

        private Trip wrappedGetTripById(String tid) {
            for (Trip t : trips) {
                if (t.getTid().equals(tid)) {
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
        if(start_date.isEmpty() && end_date.isEmpty()){
            return this.getAllTrips("","","");
        }
        if(!start_date.isEmpty() && end_date.isEmpty()){
            return this.getTripsFromStartDateOnly(start_date);
        }
        if(start_date.isEmpty() && !end_date.isEmpty()){
            return this.getTripsToEndDateOnly(end_date);
        }
        List<Trip> result = new ArrayList<>();
        for(Trip t : trips){
            Date start = new SimpleDateFormat("dd-MMM-yyyy").parse(start_date);
            Date end = new SimpleDateFormat("dd-MMM-yyyy").parse(end_date);
            Date tripDate = new SimpleDateFormat("dd-MMM-yyyy").parse(t.getDateTimeFormat().getDate());
            if(tripDate.after(start) && tripDate.before(end)){
                result.add(t);
            }
        }
        return result;
    }

        private List<Trip> getTripsFromStartDateOnly(String start_date) throws ParseException {
            List<Trip> result = new ArrayList<>();
            for(Trip t : trips){
                Date start = new SimpleDateFormat("dd-MMM-yyyy").parse(start_date);
                Date tripDate = new SimpleDateFormat("dd-MMM-yyyy").parse(t.getDateTimeFormat().getDate());
                if(tripDate.after(start)){
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
                if(tripDate.before(end)){
                    result.add(t);
                }
            }
            return result;
        }

    public List<Trip> getTakingTripsBetweenDates(String start_date, String end_date) throws ParseException {
        if(start_date.isEmpty() && end_date.isEmpty()){
            return this.getAllTrips("","","");
        }
        if(!start_date.isEmpty() && end_date.isEmpty()){
            return this.getTakingTripsFromStartDateOnly(start_date);
        }
        if(start_date.isEmpty() && !end_date.isEmpty()){
            return this.getTakingTripsToEndDateOnly(end_date);
        }
        List<Trip> result = new ArrayList<>();
        for(Trip t : trips){
            Date start = new SimpleDateFormat("dd-MMM-yyyy").parse(start_date);
            Date end = new SimpleDateFormat("dd-MMM-yyyy").parse(end_date);
            Date tripDate = new SimpleDateFormat("dd-MMM-yyyy").parse(t.getDateTimeFormat().getDate());
            System.out.println(t.getDateTimeFormat().getDate() + "," + (!tripDate.before(start) && !tripDate.after(end)));
            RideRequestInteractorBoundary rri_t = RideRequestInteractor.INSTANCE;

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
            RideRequestInteractorBoundary rri_temp = RideRequestInteractor.INSTANCE;
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

    public List<Trip> getTrips() {
        return trips;
    }
}
