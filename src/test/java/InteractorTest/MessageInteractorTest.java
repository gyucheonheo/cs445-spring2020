package InteractorTest;

import Boundary.MessageInteractorBoundary;
import Boundary.TripInteractorBoundary;
import Entity.Boundary.Account.CellPhoneFormat.CellPhoneFormat;
import Entity.Boundary.Account.User.User;
import Entity.Boundary.Trip.Car.Car;
import Entity.Boundary.Trip.Car.Vehicle.Vehicle;
import Entity.Boundary.Trip.DateTimeFormat.DateTimeFormat;
import Entity.Boundary.Trip.LocationInformation.Location.Location;
import Entity.Boundary.Trip.LocationInformation.LocationInformation;
import Entity.Boundary.Trip.Rules.Rules;
import Entity.Boundary.Trip.Trip;
import Entity.Bounded.Account.CellPhoneFormat.BoundedCellPhoneFormat;
import Entity.Bounded.Account.User.BoundedUser;
import Entity.Bounded.Trip.Car.BoundedCar;
import Entity.Bounded.Trip.Car.Vehicle.BoundedVehicle;
import Entity.Bounded.Trip.DateTimeFormat.BoundedDateTimeFormat;
import Entity.Bounded.Trip.LocationInformation.Location.BoundedLocation;
import Entity.Bounded.Trip.LocationInformation.BoundedLocationInformation;
import Entity.Bounded.Trip.Rules.BoundedRules;
import Interactor.MessageInteractor;
import Interactor.TripInteractor;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

public class MessageInteractorTest {
    private static MessageInteractorBoundary mb = MessageInteractor.INSTANCE;
    private static Trip trip;
    private static String driverAid;
    private static String riderAid;
    private static TripInteractorBoundary tb = TripInteractor.INSTANCE;

    @Before
    public void setUp_createTrip_before_sending_msg(){
        CellPhoneFormat driverCellPhone = BoundedCellPhoneFormat.Make("312","444","5555");
        User driver = BoundedUser.Make("Gyucheon", "Heo", driverCellPhone,"http://example.com/mypicture.jpg");
        driverAid = driver.getAid();

        CellPhoneFormat riderCellPhone = BoundedCellPhoneFormat.Make("312","444","5555");
        User rider = BoundedUser.Make("Jason", "King", riderCellPhone,"http://example.com/json.jpg");
        riderAid = rider.getAid();

        Location start = BoundedLocation.Make("Chicago", "60616");
        Location dest = BoundedLocation.Make("Spring Field", "62323");
        LocationInformation l = BoundedLocationInformation.Make(start, dest);
        Vehicle v = BoundedVehicle.Make("Hyundai", "Elantra", "White");
        Car car = BoundedCar.Make(v, "IL", "ABCDEF");
        List<String> conditions = new ArrayList<>();
        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime(2020,5,15,9,0);

        Rules r = BoundedRules.Make(2,6.0, conditions);

        trip = tb.createTrip(driverAid, l, car, dt, r);
        tb.registerTrip(trip);
    }

}
