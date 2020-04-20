package InteractorTest;

import Boundary.Account.AccountInteractorCommandBoundary;
import Boundary.RideRequest.RideRequestInteractorCommandBoundary;
import Boundary.RideRequest.RideRequestInteractorQueryBoundary;
import Boundary.Trip.TripInteractorCommandBoundary;
import Boundary.Trip.TripInteractorQueryBoundary;
import Entity.Boundary.Account.User.User;
import Entity.Boundary.RideRequest.RideRequest;
import Entity.Boundary.Trip.Car.Car;
import Entity.Boundary.Trip.DateTimeFormat.DateTimeFormat;
import Entity.Boundary.Trip.LocationInformation.LocationInformation;
import Entity.Boundary.Trip.Rules.Rules;
import Entity.Boundary.Trip.Trip;
import Entity.Bounded.Account.CellPhoneFormat.BoundedCellPhoneFormat;
import Entity.Bounded.Account.User.BoundedUser;
import Entity.Bounded.RideRequest.BoundedRideRequest;
import Entity.Bounded.Trip.Car.BoundedCar;
import Entity.Bounded.Trip.Car.Vehicle.BoundedVehicle;
import Entity.Bounded.Trip.DateTimeFormat.BoundedDateTimeFormat;
import Entity.Bounded.Trip.LocationInformation.BoundedLocationInformation;
import Entity.Bounded.Trip.LocationInformation.Location.BoundedLocation;
import Entity.Bounded.Trip.Rules.BoundedRules;
import Interactor.Account.AccountInteractorCommand;
import Interactor.RideRequest.RideRequestInteractorCommand;
import Interactor.RideRequest.RideRequestInteractorQuery;
import Interactor.Trip.TripInteractorCommand;
import Interactor.Trip.TripInteractorQuery;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.List;

public class RideRequestInteractorTest {
    private User unauthorizedUser;
    private User authorizedUser;
    private User driver;
    private User rider;
    private User stranger;
    private RideRequestInteractorQueryBoundary request_query_i;
    private RideRequestInteractorCommandBoundary request_command_i;
    private TripInteractorCommandBoundary trip_command_i;
    private TripInteractorQueryBoundary trip_query_i;
    @Before
    public void setUp(){
        unauthorizedUser = BoundedUser.Make("unauthroized","test", BoundedCellPhoneFormat.Make("111","222","3333"), "google.com");
        authorizedUser = BoundedUser.Make("authorized","test", BoundedCellPhoneFormat.Make("111","222","3333"), "google.com");
        driver = BoundedUser.Make("driver","test", BoundedCellPhoneFormat.Make("111","222","3333"), "google.com");
        rider = BoundedUser.Make("rider","test", BoundedCellPhoneFormat.Make("111","222","3333"), "google.com");
        stranger = BoundedUser.Make("stranger","test", BoundedCellPhoneFormat.Make("111","222","3333"), "google.com");
        trip_command_i = TripInteractorCommand.INSTANCE;
        request_query_i = RideRequestInteractorQuery.INSTANCE;
        request_command_i = RideRequestInteractorCommand.INSTANCE;
        AccountInteractorCommandBoundary ai = AccountInteractorCommand.INSTANCE;

        ai.registerUser(unauthorizedUser);
        ai.registerUser(authorizedUser);
        ai.registerUser(driver);
        ai.registerUser(rider);
        ai.registerUser(stranger);
        ai.activateUser(authorizedUser.getAid());
        ai.activateUser(driver.getAid());
        ai.activateUser(rider.getAid());
        ai.activateUser(stranger.getAid());
    }


    @Test
    public void requestRide_to_existing_ride_return_jid() throws ParseException {
        request_query_i = RideRequestInteractorQuery.INSTANCE;
        trip_query_i = TripInteractorQuery.INSTANCE;
        trip_command_i = TripInteractorCommand.INSTANCE;

        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo = BoundedRules.Make(2, 5, "");

        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime("15-May-2020, 09:20");
        Trip t1 = trip_query_i.createTrip( driver.getAid(), locationInfo, carInfo, dt, passengerInfo);
        trip_command_i.registerTrip(t1);
        RideRequest rr = BoundedRideRequest.Make(authorizedUser.getAid(), t1.getTid(), 2);
        request_query_i.requestRide(rr);
    }

    @Test
    public void confirmRideToExistingRide_must_change_ride_confirmed_to_true() throws ParseException {
        request_query_i = RideRequestInteractorQuery.INSTANCE;
        trip_query_i = TripInteractorQuery.INSTANCE;
        trip_command_i = TripInteractorCommand.INSTANCE;

        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo = BoundedRules.Make(2, 5, "");
        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime("15-May-2020, 09:20");
        Trip t1 = trip_query_i.createTrip(driver.getAid(), locationInfo, carInfo, dt, passengerInfo);
        trip_command_i.registerTrip(t1);
        RideRequest rr = BoundedRideRequest.Make(authorizedUser.getAid(), t1.getTid(), 2);
        request_query_i.requestRide(rr);
        request_command_i.confirmRide(driver.getAid(), t1.getTid(),rr.getJid());

        Assert.assertTrue(rr.getIsRideConfirmed());
    }

    @Test
    public void denyRideToExistingRide_must_change_ride_confirmed_to_true() throws ParseException {
        request_query_i = RideRequestInteractorQuery.INSTANCE;
        trip_query_i = TripInteractorQuery.INSTANCE;
        trip_command_i = TripInteractorCommand.INSTANCE;

        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo = BoundedRules.Make(2, 5, "");
        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime("15-May-2020, 09:20");
        Trip t1 = trip_query_i.createTrip(driver.getAid(), locationInfo, carInfo, dt, passengerInfo);
        trip_command_i.registerTrip(t1);
        RideRequest rr = BoundedRideRequest.Make(authorizedUser.getAid(), t1.getTid(),2);
        request_query_i.requestRide(rr);
        request_command_i.denyRide(driver.getAid(), t1.getTid(), rr.getJid());
        Assert.assertFalse(rr.getIsRideConfirmed());
    }

    @Test(expected=Test.None.class)
    public void denyRide_to_none_existing_should_do_nothing() throws ParseException {
        request_query_i = RideRequestInteractorQuery.INSTANCE;
        trip_query_i = TripInteractorQuery.INSTANCE;
        trip_command_i = TripInteractorCommand.INSTANCE;

        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo = BoundedRules.Make(2, 5, "");
        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime("15-May-2020, 09:20");
        Trip t1 = trip_query_i.createTrip(driver.getAid(), locationInfo, carInfo, dt, passengerInfo);
        trip_command_i.registerTrip(t1);
        RideRequest rr = BoundedRideRequest.Make(authorizedUser.getAid(), t1.getTid(),2);
        request_query_i.requestRide(rr);
        request_command_i.denyRide(driver.getAid(), t1.getTid(), "asdf");
    }

    @Test
    public void getRequestByTripId_should_return_null_when_tripId_not_exist(){
        RideRequest r = request_query_i.getRequestByTripId("asdf");
        Assert.assertTrue(r.isNil());
    }

    @Test
    public void getRequestByTripId_should_return_appropriate_request() throws ParseException {
        request_query_i = RideRequestInteractorQuery.INSTANCE;
        trip_query_i = TripInteractorQuery.INSTANCE;
        trip_command_i = TripInteractorCommand.INSTANCE;

        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo = BoundedRules.Make(2, 5, "");
        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime("15-May-2020, 09:20");
        Trip t1 = trip_query_i.createTrip(driver.getAid(), locationInfo, carInfo, dt, passengerInfo);
        trip_command_i.registerTrip(t1);
        RideRequest rr = BoundedRideRequest.Make(authorizedUser.getAid(), t1.getTid(),2);
        request_query_i.requestRide(rr);
        RideRequest r = request_query_i.getRequestByTripId(t1.getTid());
        Assert.assertNull(r.getIsRideConfirmed());
        Assert.assertNull(r.getIsPickUpConfirmed());
    }

    @Test
    public void getRequestByJid_should_return_null_when_jid_not_exist(){
        RideRequest r = request_query_i.getRequestByJid("asdf");
        Assert.assertTrue(r.isNil());
    }

    @Test
    public void getRequestByJid_should_return_appropriate_request_when_jid_exists() throws ParseException {
        request_query_i = RideRequestInteractorQuery.INSTANCE;
        trip_query_i = TripInteractorQuery.INSTANCE;
        trip_command_i = TripInteractorCommand.INSTANCE;

        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo = BoundedRules.Make(2, 5, "");
        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime("15-May-2020, 09:20");
        Trip t1 = trip_query_i.createTrip(driver.getAid(), locationInfo, carInfo, dt, passengerInfo);
        trip_command_i.registerTrip(t1);
        RideRequest rr = BoundedRideRequest.Make(authorizedUser.getAid(), t1.getTid(),2);
        request_query_i.requestRide(rr);
        RideRequest r = request_query_i.getRequestByJid(rr.getJid());
        String jid = r.getJid();

        Assert.assertEquals(rr.getJid(), jid);
}
    @Test
    public void getTotalRidesByAid_shoudl_return_zero_when_driver_never_confirm(){
        int rides = request_query_i.getTotalRidesByAid(rider.getAid());
        Assert.assertEquals(0, rides);
    }
    @Test
    public void getTotalRidesByAid_should_increase_by_1_when_passenger_confirms_pick_up() throws ParseException {
        request_query_i = RideRequestInteractorQuery.INSTANCE;
        trip_query_i = TripInteractorQuery.INSTANCE;
        trip_command_i = TripInteractorCommand.INSTANCE;

        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo = BoundedRules.Make(2, 5, "");
        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime("15-May-2020, 09:20");
        Trip t1 = trip_query_i.createTrip(driver.getAid(), locationInfo, carInfo, dt, passengerInfo);
        trip_command_i.registerTrip(t1);
        RideRequest rr = BoundedRideRequest.Make(authorizedUser.getAid(), t1.getTid(),2);
        request_query_i.requestRide(rr);
        request_command_i.confirmRide(driver.getAid(), t1.getTid(), rr.getJid());
        rr.setIsPickUpConfirmed(true);
        int rides = request_query_i.getTotalRidesByAid(authorizedUser.getAid());
        Assert.assertEquals(1, rides);
    }



}
