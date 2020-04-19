package InteractorTest;

import Boundary.Account.AccountInteractorCommandBoundary;
import Boundary.Rate.RateInteractorCommandBoundary;
import Boundary.Rate.RateInteractorQueryBoundary;
import Boundary.RideRequest.RideRequestInteractorCommandBoundary;
import Boundary.RideRequest.RideRequestInteractorQueryBoundary;
import Boundary.Trip.TripInteractorCommandBoundary;
import Boundary.Trip.TripInteractorQueryBoundary;
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
import Interactor.Account.AccountInteractorCommand;
import Interactor.Rate.RateInteractorCommand;
import Interactor.Rate.RateInteractorQuery;
import Interactor.RideRequest.RideRequestInteractorCommand;
import Interactor.RideRequest.RideRequestInteractorQuery;
import Interactor.Trip.TripInteractorCommand;
import Interactor.Trip.TripInteractorQuery;
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
    private RateInteractorQueryBoundary rate_query_i;
    private RideRequestInteractorQueryBoundary request_query_i;
    private TripInteractorQueryBoundary trip_query_i;
    private RateInteractorCommandBoundary rate_command_i;
    private AccountInteractorCommandBoundary acc_command_i;
    private RideRequestInteractorCommandBoundary request_command_i;
    private TripInteractorCommandBoundary trip_command_i;
    @Before
    public void setUp() throws ParseException {
        acc_command_i = AccountInteractorCommand.INSTANCE;
        rate_query_i = RateInteractorQuery.INSTANCE;
        trip_query_i = TripInteractorQuery.INSTANCE;
        trip_command_i = TripInteractorCommand.INSTANCE;
        request_query_i = RideRequestInteractorQuery.INSTANCE;
        request_command_i = RideRequestInteractorCommand.INSTANCE;
        rate_command_i = RateInteractorCommand.INSTANCE;

        driver = BoundedUser.Make("driver", "Heo", BoundedCellPhoneFormat.Make("123","333","4444"), "http://google.com/gheo1/jake.png");
        rider = BoundedUser.Make("rider", "Heo", BoundedCellPhoneFormat.Make("123","333","4444"), "http://google.com/gheo1/jake.png");
        stranger = BoundedUser.Make("stranger", "Heo", BoundedCellPhoneFormat.Make("123","333","4444"), "http://google.com/gheo1/jake.png");
        acc_command_i.registerUser(driver);
        acc_command_i.registerUser(rider);
        acc_command_i.registerUser(stranger);
        acc_command_i.activateUser(driver.getAid());
        acc_command_i.activateUser(rider.getAid());
        acc_command_i.activateUser(stranger.getAid());

        String conditions = "";
        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo = BoundedRules.Make(2, 5, conditions);
        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime("15-May-2020, 09:20");

        t1 = trip_query_i.createTrip(driver.getAid(), locationInfo, carInfo, dt, passengerInfo);
        trip_command_i.registerTrip(t1);
        rr = BoundedRideRequest.Make(rider.getAid(),t1.getTid(), 2);
        request_query_i.requestRide(rr);
    }

    @Test
    public void rateDriver_succeeds_it_should_be_found(){
        request_command_i.confirmRide(driver.getAid(), t1.getTid(), rr.getJid());
        Rate r = BoundedRate.Make(t1.getTid(),"05-Apr-2020",rider.getAid(), 5, "Awesome!");
        rate_command_i.rateAccount(t1.getTid(), r);
        Assert.assertEquals(r.getSentBy(), rate_query_i.getDriverRateByRateId(driver.getAid(), r.getRid()).getSentBy());
        Assert.assertEquals(r.getRating(), rate_query_i.getDriverRateByRateId(driver.getAid(), r.getRid()).getRating());
        Assert.assertEquals(r.getComment(), rate_query_i.getDriverRateByRateId(driver.getAid(), r.getRid()).getComment());
    }

    @Test
    public void when_rateDriver_succeeds_it_should_be_found(){
        request_command_i.confirmRide(driver.getAid(), t1.getTid(), rr.getJid());
        Rate r = BoundedRate.Make(t1.getTid(), "05-Apr-2020",rider.getAid(), 5, "Hello you are good!");
        rate_command_i.rateAccount(t1.getTid(), r);

        Assert.assertEquals(r.getSentBy(), rate_query_i.getDriverRateByRateId(driver.getAid(), r.getRid()).getSentBy());
        Assert.assertEquals(r.getRating(), rate_query_i.getDriverRateByRateId(driver.getAid(), r.getRid()).getRating());
        Assert.assertEquals(r.getComment(), rate_query_i.getDriverRateByRateId(driver.getAid(), r.getRid()).getComment());
    }

    @Test
    public void rateDriver_twice_it_should_be_found() throws ParseException {
        request_command_i.confirmRide(driver.getAid(), t1.getTid(), rr.getJid());
        Rate r = BoundedRate.Make(t1.getTid(),"05-Apr-2020",rider.getAid(), 5, "Hello you are good!");
        rate_command_i.rateAccount(t1.getTid(), r);
        String conditions = "";
        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo = BoundedRules.Make(2, 5, conditions);
        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime("15-May-2020, 09:20");

        Trip t2 = trip_query_i.createTrip(driver.getAid(), locationInfo, carInfo, dt, passengerInfo);
        trip_command_i.registerTrip(t2);
        rr = BoundedRideRequest.Make(rider.getAid(),t2.getTid(), 2);
        request_query_i.requestRide(rr);
        request_command_i.confirmRide(driver.getAid(), t2.getTid(), rr.getJid());

        Rate r2 = BoundedRate.Make(t2.getTid(), "05-Apr-2020",rider.getAid(), 5, "Hello you are good!");
        rate_command_i.rateAccount(t2.getTid(), r2);
        Assert.assertEquals(r2.getSentBy(), rate_query_i.getDriverRateByRateId(driver.getAid(), r2.getRid()).getSentBy());
        Assert.assertEquals(r2.getRating(), rate_query_i.getDriverRateByRateId(driver.getAid(), r2.getRid()).getRating());
        Assert.assertEquals(r2.getComment(), rate_query_i.getDriverRateByRateId(driver.getAid(), r2.getRid()).getComment());
    }


    @Test
    public void when_rateRider_succeeds_it_should_be_found(){
        request_command_i.confirmRide(driver.getAid(), t1.getTid(), rr.getJid());
        Rate r = BoundedRate.Make(t1.getTid(),"05-Apr-2020",driver.getAid(), 5, "Hello you are good!");
        rate_command_i.rateAccount(t1.getTid(), r);

        Assert.assertEquals(r.getSentBy(), rate_query_i.getRiderRateByRateId(rider.getAid(), r.getRid()).getSentBy());
        Assert.assertEquals(r.getRating(), rate_query_i.getRiderRateByRateId(rider.getAid(), r.getRid()).getRating());
        Assert.assertEquals(r.getComment(), rate_query_i.getRiderRateByRateId(rider.getAid(), r.getRid()).getComment());
    }

    @Test
    public void rateRider_twice_it_should_be_found() throws ParseException {
        request_command_i.confirmRide(driver.getAid(), t1.getTid(), rr.getJid());
        Rate r = BoundedRate.Make(t1.getTid(), "05-Apr-2020",driver.getAid(), 5, "Hello you are good!");
        rate_command_i.rateAccount(t1.getTid(), r);
        String conditions = "";
        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo = BoundedRules.Make(2, 5, conditions);

        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime("15-May-2020, 09:20");

        Trip t2 = trip_query_i.createTrip(driver.getAid(), locationInfo, carInfo, dt, passengerInfo);
        trip_command_i.registerTrip(t2);
        rr = BoundedRideRequest.Make(rider.getAid(),t2.getTid(), 2);
        request_query_i.requestRide(rr);
        request_command_i.confirmRide(driver.getAid(), t2.getTid(), rr.getJid());

        Rate r2 = BoundedRate.Make(t2.getTid(), "05-Apr-2020",driver.getAid(), 5, "Hello you are good!");
        rate_command_i.rateAccount(t2.getTid(), r2);
        Assert.assertEquals(r2.getSentBy(), rate_query_i.getRiderRateByRateId(rider.getAid(), r2.getRid()).getSentBy());
        Assert.assertEquals(r2.getRating(), rate_query_i.getRiderRateByRateId(rider.getAid(), r2.getRid()).getRating());
        Assert.assertEquals(r2.getComment(), rate_query_i.getRiderRateByRateId(rider.getAid(), r2.getRid()).getComment());
    }

    @Test
    public void driver_has_one_rate_it_should_be_equal_to_getDriverRatesByAccountId(){
        request_command_i.confirmRide(driver.getAid(), t1.getTid(), rr.getJid());
        Rate r = rate_query_i.createRate(t1.getTid(),"05-Apr-2020",rider.getAid(), 5, "Awesome!");
        rate_command_i.rateAccount(t1.getTid(), r);
        List<Rate> rates = rate_query_i.getDriverRatesByAccountId(driver.getAid());
        Assert.assertEquals(1, rates.size());
    }
    @Test
    public void getDriverAverageRatingByAccountId(){
        request_command_i.confirmRide(driver.getAid(), t1.getTid(), rr.getJid());
        Rate r = BoundedRate.Make(t1.getTid(),"05-Apr-2020", rider.getAid(), 5, "Awesome!");
        rate_command_i.rateAccount(t1.getTid(), r);

        Assert.assertEquals(5.0, rate_query_i.getDriverAverageRatingByAccountId(driver.getAid()));
    }
    @Test
    public void rate_twice_getDriverAverageRatingByAccountId(){
        request_command_i.confirmRide(driver.getAid(), t1.getTid(), rr.getJid());
        Rate r = BoundedRate.Make(t1.getTid(),"05-Apr-2020",rider.getAid(),5, "Awesome!");
        Rate r1 = BoundedRate.Make(t1.getTid(),"05-Apr-2020",rider.getAid(),2, "Bad driver!");
        rate_command_i.rateAccount(t1.getTid(), r);
        rate_command_i.rateAccount(t1.getTid(), r1);
        Assert.assertEquals(3.5, rate_query_i.getDriverAverageRatingByAccountId(driver.getAid()));
    }
    @Test
    public void getDriverRateByRateId_should_return_nullRate_when_driverId_and_rateId_donot_exist(){
        Rate r = rate_query_i.getDriverRateByRateId(driver.getAid(),"asdf");
        Assert.assertTrue(r.isNil());
    }
    @Test
    public void getRiderRateByRateId_should_return_nullRate_when_driverId_and_rateId_donot_exist(){
        Rate r = rate_query_i.getRiderRateByRateId("asdf","asdf");
        Assert.assertTrue(r.isNil());
    }
    @Test
    public void getDriverRatesByAccountId_should_return_null_when_user_just_created(){
        Assert.assertEquals(null, rate_query_i.getDriverAverageRatingByAccountId("asdf"));
    }

    @Test
    public void getRiderAverageRatingByAccountId_should_return_null_when_user_does_not_have_anything_in_rating(){
        Assert.assertEquals(null, rate_query_i.getRiderAverageRatingByAccountId("asdf"));
    }

    /* TODO */
    @Test
    public void getRiderAverageRatingByAccountId_should_return_avg_rating_when_user_has_rating(){
        request_command_i.confirmRide(driver.getAid(), t1.getTid(), rr.getJid());
        Rate r1 = BoundedRate.Make(t1.getTid(),"05-Apr-2020",driver.getAid(),2, "Bad driver!");
        rate_command_i.rateAccount(t1.getAid(), r1);
        Assert.assertEquals(2.0, rate_query_i.getRiderAverageRatingByAccountId(rider.getAid()));
    }
}
