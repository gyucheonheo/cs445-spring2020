package InteractorTest;

import Boundary.Account.User.AccountInteractorBoundary;
import Boundary.RideRequestInteractorBoundary;
import Boundary.Trip.TripInteractorBoundary;
import Entity.Boundary.Account.User.RideInformation.Rate.Rate;
import Entity.Boundary.Account.User.User;
import Entity.Boundary.RideRequest.RideRequest;
import Entity.Boundary.Trip.Car.Car;
import Entity.Boundary.Trip.DateTimeFormat.DateTimeFormat;
import Entity.Boundary.Trip.LocationInformation.LocationInformation;
import Entity.Boundary.Trip.Rules.Rules;
import Entity.Boundary.Trip.Trip;
import Entity.Bounded.Account.CellPhoneFormat.BoundedCellPhoneFormat;
import Entity.Bounded.Account.User.BoundedUser;
import Entity.Bounded.Account.User.RideInformation.Rate.BoundedRate;
import Entity.Bounded.RideRequest.BoundedRideRequest;
import Entity.Bounded.Trip.Car.BoundedCar;
import Entity.Bounded.Trip.Car.Vehicle.BoundedVehicle;
import Entity.Bounded.Trip.DateTimeFormat.BoundedDateTimeFormat;
import Entity.Bounded.Trip.LocationInformation.BoundedLocationInformation;
import Entity.Bounded.Trip.LocationInformation.Location.BoundedLocation;
import Entity.Bounded.Trip.Rules.BoundedRules;
import Interactor.AccountInteractor;
import Interactor.RideRequestInteractor;
import Interactor.TripInteractor;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RateInteractorTest {
    private User driver;
    private User rider;
    private User stranger;
    private AccountInteractorBoundary ai;
    private RideRequestInteractorBoundary rri;
    @Before
    public void setUp(){
        driver = BoundedUser.Make("driver", "Heo", BoundedCellPhoneFormat.Make("123","333","4444"), "http://google.com/gheo1/jake.png");
        rider = BoundedUser.Make("rider", "Heo", BoundedCellPhoneFormat.Make("123","333","4444"), "http://google.com/gheo1/jake.png");
        stranger = BoundedUser.Make("stranger", "Heo", BoundedCellPhoneFormat.Make("123","333","4444"), "http://google.com/gheo1/jake.png");
        ai = AccountInteractor.INSTANCE;
        ai.registerUser(driver);
        ai.registerUser(rider);
        ai.registerUser(stranger);
        ai.activateUser(driver.getAid());
        ai.activateUser(rider.getAid());
        ai.activateUser(stranger.getAid());
    }
    @Test(expected= AccountInteractorBoundary.UserDoNotHavePermissionToRate.class)
    public void rateUserCalledByStranger_to_not_related_ride_it_throws_UserDoNotHavePermissionToRate(){
        TripInteractorBoundary tb = TripInteractor.INSTANCE;
        rri = RideRequestInteractor.INSTANCE;
        List<String> conditions = new ArrayList<>();
        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo = BoundedRules.Make(2, 5, conditions);
        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime(2020,5,10,9,12);

        Trip t1 = tb.createTrip(driver.getAid(), locationInfo, carInfo, dt, passengerInfo);
        tb.registerTrip(t1);

        RideRequest rr = BoundedRideRequest.Make(rider.getAid(),2);
        rri.requestRide(t1.getTid(), rr);
        rri.confirmRide(driver.getAid(), t1.getTid(), rr.getJid());

        Rate r = BoundedRate.Make( stranger.getAid(), stranger.getFirstName(), 4, "Nice");
        ai.rateUser(t1.getTid(), r);
    }

    @Test(expected= AccountInteractorBoundary.UserDoNotHavePermissionToRate.class)
    public void rateUser_to_deniedRide_throws_UserDoNotHavePermission(){

        TripInteractorBoundary tb = TripInteractor.INSTANCE;
        rri = RideRequestInteractor.INSTANCE;
        List<String> conditions = new ArrayList<>();
        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo = BoundedRules.Make(2, 5, conditions);
        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime(2020,5,10,9,12);

        Trip t1 = tb.createTrip(driver.getAid(), locationInfo, carInfo, dt, passengerInfo);
        tb.registerTrip(t1);

        RideRequest rr = BoundedRideRequest.Make(rider.getAid(),2);
        rri.requestRide(t1.getTid(), rr);
        rri.denyRide(driver.getAid(), t1.getTid(), rr.getJid());

        Rate r = BoundedRate.Make( rider.getAid(), rider.getFirstName(), 4, "Nice");
        ai.rateUser(t1.getTid(), r);
    }
    @Test(expected= AccountInteractorBoundary.UserDoNotHavePermissionToRate.class)
    public void rateUser_to_not_confirmedRide_throws_UserDoNotHavePermission(){

        TripInteractorBoundary tb = TripInteractor.INSTANCE;
        rri = RideRequestInteractor.INSTANCE;
        List<String> conditions = new ArrayList<>();
        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo = BoundedRules.Make(2, 5, conditions);
        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime(2020,5,10,9,12);

        Trip t1 = tb.createTrip(driver.getAid(), locationInfo, carInfo, dt, passengerInfo);
        tb.registerTrip(t1);

        RideRequest rr = BoundedRideRequest.Make(rider.getAid(),2);
        rri.requestRide(t1.getTid(), rr);

        Rate r = BoundedRate.Make( rider.getAid(), rider.getFirstName(), 4, "Nice");
        ai.rateUser(t1.getTid(), r);
    }

}
