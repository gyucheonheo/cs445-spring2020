package InteractorTest;

import Boundary.AccountInteractorBoundary;
import Boundary.RateInteractorBoundary;
import Boundary.RideRequestInteractorBoundary;
import Boundary.TripInteractorBoundary;
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
import Interactor.RateInteractor;
import Interactor.RideRequestInteractor;
import Interactor.TripInteractor;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RateInteractorTest {
    private User driver;
    private User rider;
    private User stranger;
    private Trip t1;
    private RideRequest rr;
    private RateInteractorBoundary rateI;
    private AccountInteractorBoundary ai;
    private RideRequestInteractorBoundary rri;
    private TripInteractorBoundary tb;
    @Before
    public void setUp(){
        rateI = RateInteractor.INSTANCE;
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
        tb = TripInteractor.INSTANCE;
        rri = RideRequestInteractor.INSTANCE;
        List<String> conditions = new ArrayList<>();
        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo = BoundedRules.Make(2, 5, conditions);
        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime(2020,5,10,9,12);

        t1 = tb.createTrip(driver.getAid(), locationInfo, carInfo, dt, passengerInfo);
        tb.registerTrip(t1);
        rr = BoundedRideRequest.Make(rider.getAid(),t1.getTid(), 2);
        rri.requestRide(rr);
    }

    @Test
    public void rateDriver_succeeds_it_should_be_found(){
        rri.confirmRide(driver.getAid(), t1.getTid(), rr.getJid());
        Rate r = BoundedRate.Make(rider.getAid(), rider.getFirstName(), 5, "Awesome!");
        rateI.rateDriver(t1.getTid(), r);
        Assert.assertEquals(r.getSentBy(), rateI.getDriverRateByRateId(driver.getAid(), r.getRid()).getSentBy());
        Assert.assertEquals(r.getFirstName(), rateI.getDriverRateByRateId(driver.getAid(), r.getRid()).getFirstName());
        Assert.assertEquals(r.getRating(), rateI.getDriverRateByRateId(driver.getAid(), r.getRid()).getRating());
        Assert.assertEquals(r.getComment(), rateI.getDriverRateByRateId(driver.getAid(), r.getRid()).getComment());
    }

    @Test
    public void when_rateDriver_succeeds_it_should_be_found(){
        rri.confirmRide(driver.getAid(), t1.getTid(), rr.getJid());
        Rate r = BoundedRate.Make(rider.getAid(), rider.getFirstName(), 5, "Hello you are good!");
        rateI.rateDriver(t1.getTid(), r);

        Assert.assertEquals(r.getSentBy(), rateI.getDriverRateByRateId(driver.getAid(), r.getRid()).getSentBy());
        Assert.assertEquals(r.getFirstName(), rateI.getDriverRateByRateId(driver.getAid(), r.getRid()).getFirstName());
        Assert.assertEquals(r.getRating(), rateI.getDriverRateByRateId(driver.getAid(), r.getRid()).getRating());
        Assert.assertEquals(r.getComment(), rateI.getDriverRateByRateId(driver.getAid(), r.getRid()).getComment());

    }
    @Test
    public void rateDriver_twice_it_should_be_found(){
        rri.confirmRide(driver.getAid(), t1.getTid(), rr.getJid());
        Rate r = BoundedRate.Make(rider.getAid(), rider.getFirstName(), 5, "Hello you are good!");
        rateI.rateDriver(t1.getTid(), r);

        List<String> conditions = new ArrayList<>();
        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo = BoundedRules.Make(2, 5, conditions);
        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime(2020,5,10,9,12);

        Trip t2 = tb.createTrip(driver.getAid(), locationInfo, carInfo, dt, passengerInfo);
        tb.registerTrip(t2);
        rr = BoundedRideRequest.Make(rider.getAid(),t2.getTid(), 2);
        rri.requestRide(rr);
        rri.confirmRide(driver.getAid(), t2.getTid(), rr.getJid());

        Rate r2 = BoundedRate.Make(rider.getAid(), rider.getFirstName(), 5, "Hello you are good!");
        rateI.rateDriver(t2.getTid(), r2);
        Assert.assertEquals(r2.getSentBy(), rateI.getDriverRateByRateId(driver.getAid(), r2.getRid()).getSentBy());
        Assert.assertEquals(r2.getFirstName(), rateI.getDriverRateByRateId(driver.getAid(), r2.getRid()).getFirstName());
        Assert.assertEquals(r2.getRating(), rateI.getDriverRateByRateId(driver.getAid(), r2.getRid()).getRating());
        Assert.assertEquals(r2.getComment(), rateI.getDriverRateByRateId(driver.getAid(), r2.getRid()).getComment());
    }


    @Test
    public void when_rateRider_succeeds_it_should_be_found(){
        rri.confirmRide(driver.getAid(), t1.getTid(), rr.getJid());
        Rate r = BoundedRate.Make(driver.getAid(), driver.getFirstName(), 5, "Hello you are good!");
        rateI.rateRider(t1.getTid(), r);

        Assert.assertEquals(r.getSentBy(), rateI.getRiderRateByRateId(rider.getAid(), r.getRid()).getSentBy());
        Assert.assertEquals(r.getFirstName(), rateI.getRiderRateByRateId(rider.getAid(), r.getRid()).getFirstName());
        Assert.assertEquals(r.getRating(), rateI.getRiderRateByRateId(rider.getAid(), r.getRid()).getRating());
        Assert.assertEquals(r.getComment(), rateI.getRiderRateByRateId(rider.getAid(), r.getRid()).getComment());

    }
    @Test
    public void rateRider_twice_it_should_be_found(){
        rri.confirmRide(driver.getAid(), t1.getTid(), rr.getJid());
        Rate r = BoundedRate.Make(driver.getAid(), driver.getFirstName(), 5, "Hello you are good!");
        rateI.rateRider(t1.getTid(), r);

        List<String> conditions = new ArrayList<>();
        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo = BoundedRules.Make(2, 5, conditions);
        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime(2020,5,10,9,12);

        Trip t2 = tb.createTrip(driver.getAid(), locationInfo, carInfo, dt, passengerInfo);
        tb.registerTrip(t2);
        rr = BoundedRideRequest.Make(rider.getAid(),t2.getTid(), 2);
        rri.requestRide(rr);
        rri.confirmRide(driver.getAid(), t2.getTid(), rr.getJid());

        Rate r2 = BoundedRate.Make(driver.getAid(), driver.getFirstName(), 5, "Hello you are good!");
        rateI.rateRider(t2.getTid(), r2);
        Assert.assertEquals(r2.getSentBy(), rateI.getRiderRateByRateId(rider.getAid(), r2.getRid()).getSentBy());
        Assert.assertEquals(r2.getFirstName(), rateI.getRiderRateByRateId(rider.getAid(), r2.getRid()).getFirstName());
        Assert.assertEquals(r2.getRating(), rateI.getRiderRateByRateId(rider.getAid(), r2.getRid()).getRating());
        Assert.assertEquals(r2.getComment(), rateI.getRiderRateByRateId(rider.getAid(), r2.getRid()).getComment());
    }
}
