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
import Interactor.Account.AccountInteractor;
import Interactor.Rate.RateInteractor;
import Interactor.RideRequest.RideRequestInteractor;
import Interactor.Trip.TripInteractor;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
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
    public void setUp() throws ParseException {
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

        String conditions = "";
        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo = BoundedRules.Make(2, 5, conditions);
        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime("15-May-2020, 09:20");

        t1 = tb.createTrip(driver.getAid(), locationInfo, carInfo, dt, passengerInfo);
        tb.registerTrip(t1);
        rr = BoundedRideRequest.Make(rider.getAid(),t1.getTid(), 2);
        rri.requestRide(rr);
    }

    @Test
    public void rateDriver_succeeds_it_should_be_found(){
        rri.confirmRide(driver.getAid(), t1.getTid(), rr.getJid());
        Rate r = BoundedRate.Make(t1.getTid(),rider.getAid(), 5, "Awesome!");
        rateI.rateAccount(t1.getTid(), r);
        Assert.assertEquals(r.getSentBy(), rateI.getDriverRateByRateId(driver.getAid(), r.getRid()).getSentBy());
        Assert.assertEquals(r.getRating(), rateI.getDriverRateByRateId(driver.getAid(), r.getRid()).getRating());
        Assert.assertEquals(r.getComment(), rateI.getDriverRateByRateId(driver.getAid(), r.getRid()).getComment());
    }

    @Test
    public void when_rateDriver_succeeds_it_should_be_found(){
        rri.confirmRide(driver.getAid(), t1.getTid(), rr.getJid());
        Rate r = BoundedRate.Make(t1.getTid(), rider.getAid(), 5, "Hello you are good!");
        rateI.rateAccount(t1.getTid(), r);

        Assert.assertEquals(r.getSentBy(), rateI.getDriverRateByRateId(driver.getAid(), r.getRid()).getSentBy());
        Assert.assertEquals(r.getRating(), rateI.getDriverRateByRateId(driver.getAid(), r.getRid()).getRating());
        Assert.assertEquals(r.getComment(), rateI.getDriverRateByRateId(driver.getAid(), r.getRid()).getComment());

    }
    @Test
    public void rateDriver_twice_it_should_be_found() throws ParseException {
        rri.confirmRide(driver.getAid(), t1.getTid(), rr.getJid());
        Rate r = BoundedRate.Make(t1.getTid(),rider.getAid(), 5, "Hello you are good!");
        rateI.rateAccount(t1.getTid(), r);
        String conditions = "";
        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo = BoundedRules.Make(2, 5, conditions);
        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime("15-May-2020, 09:20");

        Trip t2 = tb.createTrip(driver.getAid(), locationInfo, carInfo, dt, passengerInfo);
        tb.registerTrip(t2);
        rr = BoundedRideRequest.Make(rider.getAid(),t2.getTid(), 2);
        rri.requestRide(rr);
        rri.confirmRide(driver.getAid(), t2.getTid(), rr.getJid());

        Rate r2 = BoundedRate.Make(t2.getTid(), rider.getAid(), 5, "Hello you are good!");
        rateI.rateAccount(t2.getTid(), r2);
        Assert.assertEquals(r2.getSentBy(), rateI.getDriverRateByRateId(driver.getAid(), r2.getRid()).getSentBy());
        Assert.assertEquals(r2.getRating(), rateI.getDriverRateByRateId(driver.getAid(), r2.getRid()).getRating());
        Assert.assertEquals(r2.getComment(), rateI.getDriverRateByRateId(driver.getAid(), r2.getRid()).getComment());
    }


    @Test
    public void when_rateRider_succeeds_it_should_be_found(){
        rri.confirmRide(driver.getAid(), t1.getTid(), rr.getJid());
        Rate r = BoundedRate.Make(t1.getTid(),driver.getAid(), 5, "Hello you are good!");
        rateI.rateAccount(t1.getTid(), r);

        Assert.assertEquals(r.getSentBy(), rateI.getRiderRateByRateId(rider.getAid(), r.getRid()).getSentBy());
        Assert.assertEquals(r.getRating(), rateI.getRiderRateByRateId(rider.getAid(), r.getRid()).getRating());
        Assert.assertEquals(r.getComment(), rateI.getRiderRateByRateId(rider.getAid(), r.getRid()).getComment());

    }
    @Test
    public void rateRider_twice_it_should_be_found() throws ParseException {
        rri.confirmRide(driver.getAid(), t1.getTid(), rr.getJid());
        Rate r = BoundedRate.Make(t1.getTid(), driver.getAid(), 5, "Hello you are good!");
        rateI.rateAccount(t1.getTid(), r);
        String conditions = "";
        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo = BoundedRules.Make(2, 5, conditions);

        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime("15-May-2020, 09:20");

        Trip t2 = tb.createTrip(driver.getAid(), locationInfo, carInfo, dt, passengerInfo);
        tb.registerTrip(t2);
        rr = BoundedRideRequest.Make(rider.getAid(),t2.getTid(), 2);
        rri.requestRide(rr);
        rri.confirmRide(driver.getAid(), t2.getTid(), rr.getJid());

        Rate r2 = BoundedRate.Make(t2.getTid(), driver.getAid(), 5, "Hello you are good!");
        rateI.rateAccount(t2.getTid(), r2);
        Assert.assertEquals(r2.getSentBy(), rateI.getRiderRateByRateId(rider.getAid(), r2.getRid()).getSentBy());
        Assert.assertEquals(r2.getRating(), rateI.getRiderRateByRateId(rider.getAid(), r2.getRid()).getRating());
        Assert.assertEquals(r2.getComment(), rateI.getRiderRateByRateId(rider.getAid(), r2.getRid()).getComment());
    }

    @Test
    public void driver_has_one_rate_it_should_be_equal_to_getDriverRatesByAccountId(){
        rri.confirmRide(driver.getAid(), t1.getTid(), rr.getJid());
        Rate r = BoundedRate.Make(t1.getTid(),rider.getAid(), 5, "Awesome!");
        rateI.rateAccount(t1.getTid(), r);
        List<Rate> rates = rateI.getDriverRatesByAccountId(driver.getAid());
        Assert.assertEquals(1, rates.size());
    }
    @Test
    public void getDriverAverageRatingByAccountId(){
        rri.confirmRide(driver.getAid(), t1.getTid(), rr.getJid());
        Rate r = BoundedRate.Make(t1.getTid(), rider.getAid(), 5, "Awesome!");
        rateI.rateAccount(t1.getTid(), r);

        Assert.assertEquals(5.0, rateI.getDriverAverageRatingByAccountId(driver.getAid()));
    }
    @Test
    public void rate_twice_getDriverAverageRatingByAccountId(){
        rri.confirmRide(driver.getAid(), t1.getTid(), rr.getJid());
        Rate r = BoundedRate.Make(t1.getTid(),rider.getAid(),5, "Awesome!");
        Rate r1 = BoundedRate.Make(t1.getTid(),rider.getAid(),2, "Bad driver!");
        rateI.rateAccount(t1.getTid(), r);
        rateI.rateAccount(t1.getTid(), r1);
        Assert.assertEquals(3.5, rateI.getDriverAverageRatingByAccountId(driver.getAid()));
    }
}
