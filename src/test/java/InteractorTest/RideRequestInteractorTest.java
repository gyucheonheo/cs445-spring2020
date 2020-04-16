package InteractorTest;

import Boundary.AccountInteractorBoundary;
import Boundary.RideRequestInteractorBoundary;
import Boundary.TripInteractorBoundary;
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
import Interactor.Account.AccountInteractor;
import Interactor.RideRequest.RideRequestInteractor;
import Interactor.Trip.TripInteractor;
import junit.framework.Assert;
import org.junit.Before;

import org.junit.Test;

import java.text.ParseException;

public class RideRequestInteractorTest {
    private User unauthorizedUser;
    private User authorizedUser;
    private User driver;
    private User rider;
    private User stranger;
    private RideRequestInteractorBoundary rb;
    private TripInteractorBoundary tb;
    @Before
    public void setUp(){
        unauthorizedUser = BoundedUser.Make("unauthroized","test", BoundedCellPhoneFormat.Make("111","222","3333"), "google.com");
        authorizedUser = BoundedUser.Make("authorized","test", BoundedCellPhoneFormat.Make("111","222","3333"), "google.com");
        driver = BoundedUser.Make("driver","test", BoundedCellPhoneFormat.Make("111","222","3333"), "google.com");
        rider = BoundedUser.Make("rider","test", BoundedCellPhoneFormat.Make("111","222","3333"), "google.com");
        stranger = BoundedUser.Make("stranger","test", BoundedCellPhoneFormat.Make("111","222","3333"), "google.com");
        tb = TripInteractor.INSTANCE;
        rb = RideRequestInteractor.INSTANCE;

        AccountInteractorBoundary ai = AccountInteractor.INSTANCE;
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
        tb = TripInteractor.INSTANCE;
        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo = BoundedRules.Make(2, 5, "");

        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime("15-May-2020, 09:20");
        Trip t1 = tb.createTrip( driver.getAid(), locationInfo, carInfo, dt, passengerInfo);
        tb.registerTrip(t1);
        RideRequest rr = BoundedRideRequest.Make(authorizedUser.getAid(), t1.getTid(), 2);
        rb.requestRide(rr);

    }

    @Test
    public void confirmRideToExistingRide_must_change_ride_confirmed_to_true() throws ParseException {
        tb = TripInteractor.INSTANCE;
        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo = BoundedRules.Make(2, 5, "");
        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime("15-May-2020, 09:20");
        Trip t1 = tb.createTrip(driver.getAid(), locationInfo, carInfo, dt, passengerInfo);
        tb.registerTrip(t1);
        RideRequest rr = BoundedRideRequest.Make(authorizedUser.getAid(), t1.getTid(), 2);
        rb.requestRide(rr);
        rb.confirmRide(driver.getAid(), t1.getTid(),rr.getJid());

        Assert.assertTrue(rr.getIsRideConfirmed());
    }

    @Test
    public void denyRideToExistingRide_must_change_ride_confirmed_to_true() throws ParseException {
        tb = TripInteractor.INSTANCE;
        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo = BoundedRules.Make(2, 5, "");
        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime("15-May-2020, 09:20");
        Trip t1 = tb.createTrip(driver.getAid(), locationInfo, carInfo, dt, passengerInfo);
        tb.registerTrip(t1);
        RideRequest rr = BoundedRideRequest.Make(authorizedUser.getAid(), t1.getTid(),2);
        rb.requestRide(rr);
        rb.denyRide(driver.getAid(), t1.getTid(), rr.getJid());
        Assert.assertFalse(rr.getIsRideConfirmed());
    }

}
