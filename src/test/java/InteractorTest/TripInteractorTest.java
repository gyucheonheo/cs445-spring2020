package InteractorTest;

import Boundary.Account.AccountInteractorCommandBoundary;
import Boundary.Account.AccountInteractorQueryBoundary;
import Boundary.Trip.TripInteractorCommandBoundary;
import Boundary.Trip.TripInteractorQueryBoundary;
import Entity.Boundary.Account.User.User;
import Entity.Boundary.Trip.Car.Car;
import Entity.Boundary.Trip.DateTimeFormat.DateTimeFormat;
import Entity.Boundary.Trip.LocationInformation.LocationInformation;
import Entity.Boundary.Trip.Rules.Rules;
import Entity.Boundary.Trip.Trip;
import Entity.Bounded.Account.CellPhoneFormat.BoundedCellPhoneFormat;
import Entity.Bounded.Trip.Car.BoundedCar;
import Entity.Bounded.Trip.Car.Vehicle.BoundedVehicle;
import Entity.Bounded.Trip.DateTimeFormat.BoundedDateTimeFormat;
import Entity.Bounded.Trip.LocationInformation.BoundedLocationInformation;
import Entity.Bounded.Trip.LocationInformation.Location.BoundedLocation;
import Entity.Bounded.Trip.Rules.BoundedRules;
import Interactor.Account.AccountInteractorCommand;
import Interactor.Account.AccountInteractorQuery;
import Interactor.Trip.TripInteractor;
import Interactor.Trip.TripInteractorCommand;
import Interactor.Trip.TripInteractorQuery;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TripInteractorTest {
    private static TripInteractorQueryBoundary trip_query_i;
    private static TripInteractorCommandBoundary trip_command_i;
    private static AccountInteractorQueryBoundary acc_query_i;
    private static AccountInteractorCommandBoundary acc_command_i;
    private static List<String> tids;
    private User driver1;
    private User driver2;
    private User driver3;
    private Trip t1;
    private Trip t2;
    private Trip t3;

    @Before
    public void setUp() throws ParseException {
        trip_query_i = TripInteractorQuery.INSTANCE;
        trip_command_i = TripInteractorCommand.INSTANCE;
        acc_query_i = AccountInteractorQuery.INSTANCE;
        acc_command_i = AccountInteractorCommand.INSTANCE;

        driver1 = acc_query_i.createUser("driver1", "lastName", BoundedCellPhoneFormat.Make("111","222","3333"), "http://example.com/test.png");
        driver2 = acc_query_i.createUser("driver2", "lastName", BoundedCellPhoneFormat.Make("111","222","3333"), "http://example.com/test.png");
        driver3 = acc_query_i.createUser("driver3", "lastName", BoundedCellPhoneFormat.Make("111","222","3333"), "http://example.com/test.png");

        acc_command_i.registerUser(driver1);
        acc_command_i.registerUser(driver2);
        acc_command_i.registerUser(driver3);

        acc_command_i.activateUser(driver1.getAid());
        acc_command_i.activateUser(driver2.getAid());
        acc_command_i.activateUser(driver3.getAid());

        String conditions = "";
        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo = BoundedRules.Make(2, 5, conditions);
        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime("15-May-2020, 09:00");
        t1 = trip_query_i.createTrip(driver1.getAid(), locationInfo, carInfo, dt, passengerInfo);
        trip_command_i.registerTrip(t1);

        LocationInformation locationInfo1 = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("St Louis",""));
        Car carInfo1 = BoundedCar.Make(BoundedVehicle.Make("Toyota", "HIGHLAND","White"), "NY", "CARDI");
        Rules passengerInfo1 = BoundedRules.Make(4, 15, conditions);
        DateTimeFormat dt1 = BoundedDateTimeFormat.MakeDateTime("16-May-2020, 09:00");
        t2 = trip_query_i.createTrip(driver2.getAid(), locationInfo1, carInfo1, dt1, passengerInfo1);
        trip_command_i.registerTrip(t2);

        LocationInformation locationInfo2 = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("New York",""));
        Car carInfo2 = BoundedCar.Make(BoundedVehicle.Make("Toyota", "HIGHLAND","White"), "WI", "WHOTATBE");
        Rules passengerInfo2 = BoundedRules.Make(4, 15, conditions);
        DateTimeFormat dt2 = BoundedDateTimeFormat.MakeDateTime("17-May-2020, 09:00");
        t3 = trip_query_i.createTrip(driver3.getAid(), locationInfo2, carInfo2, dt2, passengerInfo2);
        trip_command_i.registerTrip(t3);

        tids = new ArrayList<>();

        tids.add(t1.getTid());
        tids.add(t2.getTid());
        tids.add(t3.getTid());

    }

    @Test
    public void getTripsByAccountId_driver1_2_3_should_have_each_one(){
        List<Trip> tripDriver1 = trip_query_i.getTripByUserId(driver1.getAid());
        List<Trip> tripDriver2 = trip_query_i.getTripByUserId(driver2.getAid());
        List<Trip> tripDriver3 = trip_query_i.getTripByUserId(driver3.getAid());
        Assert.assertEquals(1, tripDriver1.size());
        Assert.assertEquals(1, tripDriver2.size());
        Assert.assertEquals(1, tripDriver3.size());
    }
    @Test
    public void registerTrip_should_increase_the_size_of_trips(){
        int size = trip_query_i.getAllTrips(null, null, null).size();
        trip_command_i.registerTrip(t1);
        int newSize = trip_query_i.getAllTrips(null, null, null).size();
        Assert.assertEquals(size+1,newSize);
    }
    @Test
    public void updateTrip_should_change_its_update_information() throws ParseException {
        String tripId = t1.getTid();
        String conditions = "";
        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("San Jose", ""), BoundedLocation.Make("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Big car","White"), "IL", "COVID19");
        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime("15-May-2020, 09:00");
        Rules passengerInfo = BoundedRules.Make(5, 5, conditions);
        trip_command_i.updateTrip(tripId, locationInfo, carInfo, dt, passengerInfo);
        Trip newTrip = trip_query_i.getTripById(tripId);
        Assert.assertEquals("San Jose", newTrip.getLocationInformation().getStartingPoint().getCity());
        Assert.assertEquals("", newTrip.getLocationInformation().getStartingPoint().getZip());
        Assert.assertEquals("Los Angeles", newTrip.getLocationInformation().getEndingPoint().getCity());
        Assert.assertEquals("", newTrip.getLocationInformation().getEndingPoint().getZip());
        Assert.assertEquals("Chevy", newTrip.getCarInformation().getVehicleInformation().getMake());
        Assert.assertEquals("Big car", newTrip.getCarInformation().getVehicleInformation().getModel());
        Assert.assertEquals("White", newTrip.getCarInformation().getVehicleInformation().getColor());
        Assert.assertEquals("IL", newTrip.getCarInformation().getPlateState());
        Assert.assertEquals("COVID19", newTrip.getCarInformation().getPlateSerial());
        Assert.assertEquals("15-May-2020", newTrip.getDateTimeFormat().getDate());
        Assert.assertEquals("09:00", newTrip.getDateTimeFormat().getTime());
        Assert.assertEquals(5, newTrip.getRules().getMaxPeople());
        Assert.assertEquals(5.0, newTrip.getRules().getAmountPerPassenger(), 0.01);
        Assert.assertTrue(newTrip.getRules().getConditions().isEmpty());
    }
    @Test
    public void deleteTrip_should_remove_trip_and_when_trying_to_find_it_should_return_nullTrip(){
        String tripId = t1.getTid();
        int size = trip_query_i.getAllTrips(null, null, null).size();
        trip_command_i.deleteTrip(tripId);
        Assert.assertEquals(size-1, trip_query_i.getAllTrips(null, null, null).size());
        Trip t = trip_query_i.getTripById(tripId);
        Assert.assertTrue(t.isNil());
    }

    @Test
    public void getTripById_for_t2_should_fetch_trip(){
        Trip newTrip = trip_query_i.getTripById(t2.getTid());

        Assert.assertEquals("Chicago", newTrip.getLocationInformation().getStartingPoint().getCity());
        Assert.assertEquals("60616", newTrip.getLocationInformation().getStartingPoint().getZip());
        Assert.assertEquals("St Louis", newTrip.getLocationInformation().getEndingPoint().getCity());
        Assert.assertEquals("", newTrip.getLocationInformation().getEndingPoint().getZip());
        Assert.assertEquals("Toyota", newTrip.getCarInformation().getVehicleInformation().getMake());
        Assert.assertEquals("HIGHLAND", newTrip.getCarInformation().getVehicleInformation().getModel());
        Assert.assertEquals("White", newTrip.getCarInformation().getVehicleInformation().getColor());
        Assert.assertEquals("NY", newTrip.getCarInformation().getPlateState());
        Assert.assertEquals("CARDI", newTrip.getCarInformation().getPlateSerial());
        Assert.assertEquals("16-May-2020", newTrip.getDateTimeFormat().getDate());
        Assert.assertEquals("09:00", newTrip.getDateTimeFormat().getTime());
        Assert.assertEquals(4, newTrip.getRules().getMaxPeople());
        Assert.assertEquals(15.0, newTrip.getRules().getAmountPerPassenger(), 0.01);
        Assert.assertTrue(newTrip.getRules().getConditions().isEmpty());
    }
    @Test
    public void getTripById_for_non_existing_should_fetch_nullTrip(){
        Trip t = trip_query_i.getTripById("asdf");
        Assert.assertTrue(t.isNil());
    }

    @Test
    public void getAllTrips_TTT_should_return_allTrips_its_size_is_equal_to_all_trips(){
        List<Trip> allTrips = trip_query_i.getAllTrips("","","");
        Assert.assertEquals(trip_query_i.getAllTrips(null, null, null).size(), allTrips.size());
    }
    @Test
    public void getAllTrips_TTF_should_return_all_trips_when_date_is_2020_05_15(){
        List<Trip> ttfTrips = trip_query_i.getAllTrips("","","15-May-2020");
        for(Trip t : ttfTrips){
            Assert.assertEquals("15-May-2020", t.getDateTimeFormat().getDate());
        }
    }
    @Test
    public void getAllTripsFromToDate_from_empty_to_new_york_and_depareture_time_empty_should_have_trips_from_everywhere_to_new_york(){
        List<Trip> chicagoTrip = trip_query_i.getAllTrips("", "New York", "");
        for(Trip t : chicagoTrip){
            Assert.assertEquals("New York", t.getLocationInformation().getEndingPoint().getCity());
        }
    }

    @Test
    public void getAllTripsFromToDate_from_Chicago_to_Emptyshould_have_trips_from_chicago_to_everywhere(){
        List<Trip> chicagoTrip = trip_query_i.getAllTrips("Chicago", "", "15-May-2020");
        for(Trip t : chicagoTrip){
            Assert.assertEquals("Chicago", t.getLocationInformation().getStartingPoint().getCity());
            Assert.assertEquals("15-May-2020", t.getDateTimeFormat().getDate());
        }
    }

    @Test
    public void getAllTripsTFF_hould_have_trips_from_everywhere_to_new_york_when_2020_05_15(){
        List<Trip> chicagoTrip = trip_query_i.getAllTrips("", "New York", "15-May-2020");
        for(Trip t : chicagoTrip){
            Assert.assertEquals("New York", t.getLocationInformation().getStartingPoint().getCity());
            Assert.assertEquals("15-May-2020", t.getDateTimeFormat().getDate());
        }
    }

    @Test
    public void getAllTripsFromToDate_from_Chicago_to_Empty_and_depareture_time_empty_should_have_trips_from_chicago_to_everywhere(){
        List<Trip> chicagoTrip = trip_query_i.getAllTrips("Chicago", "", "");
        for(Trip t : chicagoTrip){
            Assert.assertEquals("Chicago", t.getLocationInformation().getStartingPoint().getCity());
        }
    }

    @Test
    public void getAllTrips_FTT__should_have_trips_from_chicago_to_everywhere(){
        List<Trip> chicagoTrip = trip_query_i.getAllTrips("Chicago", "", "");
        for(Trip t : chicagoTrip){
            Assert.assertEquals("Chicago", t.getLocationInformation().getStartingPoint().getCity());
        }
    }

    @Test
    public void getAllTrips_FTF__should_have_trips_from_chicago_to_everywhere_but_there_is_not_trip_so_it_should_return_nothing(){
        List<Trip> chicagoTrip = trip_query_i.getAllTrips("Chicago", "", "19-May-2020");
        Assert.assertTrue(chicagoTrip.isEmpty());
    }

    @Test
    public void getAllTrips_FTF__should_have_trips_from_chicago_to_everywhere(){
        List<Trip> chicagoTrip = trip_query_i.getAllTrips("Chicago", "", "15-May-2020");
        for(Trip t : chicagoTrip){
            Assert.assertEquals("Chicago", t.getLocationInformation().getStartingPoint().getCity());
            Assert.assertEquals("15-May-2020", t.getDateTimeFormat().getDate());
        }
    }

    @Test
    public void getAllTrips_FFT__should_have_trips_from_chicago_to_everywhere(){
        List<Trip> chicagoTrip = trip_query_i.getAllTrips("Chicago", "New York", "");
        for(Trip t : chicagoTrip){
            Assert.assertEquals("Chicago", t.getLocationInformation().getStartingPoint().getCity());
            Assert.assertEquals("New York", t.getLocationInformation().getEndingPoint().getCity());
        }
    }
    @Test
    public void getAllTripsFromToDate_from_Chicago_to_NewYork_should_have_trips_from_chicago_to_New_york(){
        List<Trip> chicagoTrip = trip_query_i.getAllTrips("Chicago", "New York", "15-May-2020");
        for(Trip t : chicagoTrip){
            Assert.assertEquals("Chicago", t.getLocationInformation().getStartingPoint().getCity());
            Assert.assertEquals("New York", t.getLocationInformation().getEndingPoint().getCity());
            Assert.assertEquals("15-May-2020", t.getDateTimeFormat().getDate());
        }
    }
    @Test
    public void getAllTripsFromToDate_from_Chicago_to_Montana_should_have_Nothing(){
        List<Trip> chicagoTrip = trip_query_i.getAllTrips("Chicago", "Montana", "20-May-2020");
        Assert.assertTrue(chicagoTrip.isEmpty());
    }

    @Test
    public void getPostingTripsBetweenDates_two_empty_should_have_initial_three_trips() throws ParseException {
        List<Trip> t = trip_query_i.getPostingTripsBetweenDates("","");
        Assert.assertEquals(3, t.size());
    }
    @Test
    public void getPostingTripsBetweenDates_empty_to_should_have_two() throws ParseException {
        List<Trip> t = trip_query_i.getPostingTripsBetweenDates("15-May-2020","");
        Assert.assertEquals(3, t.size());
    }

    @Test
    public void getPostingTripsBetweenDates_empty_from_should_have_two() throws ParseException {
        List<Trip> t = trip_query_i.getPostingTripsBetweenDates("","16-May-2020");
        Assert.assertEquals(2, t.size());
    }
    @Test
    public void getPostingTripsBetweenDates_should_have_one() throws ParseException {
        List<Trip> t = trip_query_i.getPostingTripsBetweenDates("16-May-2020","16-May-2020");
        Assert.assertEquals(1, t.size());
    }
    @Test
    public void getTakingTripsBetweenDates_should_return_three() throws ParseException {
        List<Trip> t = trip_query_i.getTakingTripsBetweenDates("","");
        Assert.assertEquals(3, t.size());
    }
    @Test
    public void getTakingTripsBetweenDates_should_return_empty_because_nothing_confirmed_yet() throws ParseException {
        List<Trip> t = trip_query_i.getTakingTripsBetweenDates("15-May-2020","");
        List<Trip> t1 = trip_query_i.getTakingTripsBetweenDates("","16-May-2020");
        List<Trip> t2 = trip_query_i.getTakingTripsBetweenDates("15-May-2020","16-May-2020");
        Assert.assertEquals(0, t.size());
        Assert.assertEquals(0, t1.size());
        Assert.assertEquals(0, t2.size());
    }
    @Test
    public void getTotalRidesByAid_should_return_0_after_driver_post_because_nothing_confirmed_yet(){
        int total = trip_query_i.getTotalRidesByAid(driver1.getAid());
        Assert.assertEquals(0, total);

    }
    @After
    public void tearUp(){
        TripInteractor.INSTANCE.getTrips().clear();
    }
}
