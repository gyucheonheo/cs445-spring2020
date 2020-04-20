package ValidatorTest;

import Boundary.Account.AccountInteractorCommandBoundary;
import Boundary.Rate.RateInteractorCommandBoundary;
import Boundary.Rate.RateInteractorQueryBoundary;
import Boundary.RideRequest.RideRequestInteractorCommandBoundary;
import Boundary.RideRequest.RideRequestInteractorQueryBoundary;
import Boundary.Trip.TripInteractorCommandBoundary;
import Boundary.Trip.TripInteractorQueryBoundary;
import Controller.Rate.Validator.RateValidator;
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
import Interactor.Rate.RateInteractorCommand;
import Interactor.Rate.RateInteractorQuery;
import Interactor.RideRequest.RideRequestInteractorCommand;
import Interactor.RideRequest.RideRequestInteractorQuery;
import Interactor.Trip.TripInteractorCommand;
import Interactor.Trip.TripInteractorQuery;
import com.google.gson.JsonObject;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

public class RateValidatorTest {
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
        request_command_i.confirmRide(driver.getAid(), t1.getTid(), rr.getJid());
        rr.setIsPickUpConfirmed(true);
    }

    @Test
    public void no_rid_should_be_errormsg_rid_is_null(){
        JsonObject j = new JsonObject();
        boolean b = RateValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("rid is null", RateValidator.getErrorMessage());
    }
    @Test
    public void empty_rid_should_be_errormsg_rid_is_empty(){
        JsonObject j = new JsonObject();
        j.addProperty("rid","");
        boolean b = RateValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("rid is empty", RateValidator.getErrorMessage());
    }
    @Test
    public void stranger_attempting_to_rate_should_have_error(){
        JsonObject j = new JsonObject();
        j.addProperty("rid",t1.getTid());
        j.addProperty("sent_by_id", stranger.getAid());
        boolean b = RateValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("This account ("+stranger.getAid()+") didn't create this ride ("+t1.getTid()+") nor was it a passenger", RateValidator.getErrorMessage());
    }
    @Test
    public void rating_should_be_between_1_and_5(){
        JsonObject j = new JsonObject();
        j.addProperty("rid",t1.getTid());
        j.addProperty("sent_by_id", rider.getAid());
        j.addProperty("rating", -1);
        boolean b = RateValidator.isValid(j);
        Assert.assertEquals("Invalid rating value", RateValidator.getErrorMessage());
    }
    @Test
    public void rating_should_be_between_1_and_5_second(){
        JsonObject j = new JsonObject();
        j.addProperty("rid",t1.getTid());
        j.addProperty("sent_by_id", rider.getAid());
        j.addProperty("rating", 6);
        boolean b = RateValidator.isValid(j);
        Assert.assertEquals("Invalid rating value", RateValidator.getErrorMessage());
    }
    @Test
    public void when_perfect_it_should_return_true(){
        JsonObject j = new JsonObject();
        j.addProperty("rid",t1.getTid());
        j.addProperty("sent_by_id", rider.getAid());
        j.addProperty("rating", 4);
        boolean b = RateValidator.isValid(j);
        Assert.assertTrue(b);
    }
}
