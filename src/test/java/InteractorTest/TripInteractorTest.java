package InteractorTest;

import Boundary.AccountInteractorBoundary;
import Boundary.TripInteractorBoundary;
import Entity.Boundary.Account.User.User;
import Entity.Boundary.Trip.Car.Car;
import Entity.Boundary.Trip.DateTimeFormat.DateTimeFormat;
import Entity.Boundary.Trip.LocationInformation.LocationInformation;
import Entity.Boundary.Trip.Rules.Rules;
import Entity.Bounded.Account.CellPhoneFormat.BoundedCellPhoneFormat;
import Entity.Bounded.Trip.Car.BoundedCar;
import Entity.Bounded.Trip.Car.Vehicle.BoundedVehicle;
import Entity.Bounded.Trip.DateTimeFormat.BoundedDateTimeFormat;
import Entity.Bounded.Trip.LocationInformation.Location.BoundedLocation;
import Entity.Bounded.Trip.LocationInformation.BoundedLocationInformation;
import Entity.Bounded.Trip.Rules.BoundedRules;
import Entity.Boundary.Trip.Trip;
import Interactor.AccountInteractor;
import Interactor.TripInteractor;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

public class TripInteractorTest {
    private static TripInteractorBoundary tb;
    private static AccountInteractorBoundary ab;
    private static List<String> tids;
    private User driver1;
    private User driver2;
    private User driver3;
    private Trip t1;
    private Trip t2;
    private Trip t3;

    @Before
    public void setUp() {
        tb = TripInteractor.INSTANCE;
        ab = AccountInteractor.INSTANCE;
        driver1 = ab.createUser("driver1", "lastName", BoundedCellPhoneFormat.Make("111","222","3333"), "http://example.com/test.png");
        driver2 = ab.createUser("driver2", "lastName", BoundedCellPhoneFormat.Make("111","222","3333"), "http://example.com/test.png");
        driver3 = ab.createUser("driver3", "lastName", BoundedCellPhoneFormat.Make("111","222","3333"), "http://example.com/test.png");

        ab.registerUser(driver1);
        ab.registerUser(driver2);
        ab.registerUser(driver3);

        ab.activateUser(driver1.getAid());
        ab.activateUser(driver2.getAid());
        ab.activateUser(driver3.getAid());

        List<String> conditions = new ArrayList<>();
        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo = BoundedRules.Make(2, 5, conditions);
        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime(2020,5,15,9,20);
        t1 = tb.createTrip(driver1.getAid(), locationInfo, carInfo, dt, passengerInfo);
        tb.registerTrip(t1);

        LocationInformation locationInfo1 = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("St Louis",""));
        Car carInfo1 = BoundedCar.Make(BoundedVehicle.Make("Toyota", "HIGHLAND","White"), "NY", "CARDI");
        Rules passengerInfo1 = BoundedRules.Make(4, 15, conditions);
        DateTimeFormat dt1 = BoundedDateTimeFormat.MakeDateTime(2020,5,15,9,20);
        t2 = tb.createTrip(driver2.getAid(), locationInfo1, carInfo1, dt1, passengerInfo1);
        tb.registerTrip(t2);

        LocationInformation locationInfo2 = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("New York",""));
        Car carInfo2 = BoundedCar.Make(BoundedVehicle.Make("Toyota", "HIGHLAND","White"), "WI", "WHOTATBE");
        Rules passengerInfo2 = BoundedRules.Make(4, 15, conditions);
        DateTimeFormat dt2 = BoundedDateTimeFormat.MakeDateTime(2020,5,15,9,20);
        t3 = tb.createTrip(driver3.getAid(), locationInfo2, carInfo2, dt2, passengerInfo2);
        tb.registerTrip(t3);

        tids = new ArrayList<>();

        tids.add(t1.getTid());
        tids.add(t2.getTid());
        tids.add(t3.getTid());

    }
}
