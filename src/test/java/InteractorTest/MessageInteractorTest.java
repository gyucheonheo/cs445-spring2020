package InteractorTest;

import Boundary.Message.MessageInteractorCommandBoundary;
import Boundary.Message.MessageInteractorQueryBoundary;
import Boundary.Trip.TripInteractorCommandBoundary;
import Boundary.Trip.TripInteractorQueryBoundary;
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
import Entity.Bounded.Trip.LocationInformation.BoundedLocationInformation;
import Entity.Bounded.Trip.LocationInformation.Location.BoundedLocation;
import Entity.Bounded.Trip.Rules.BoundedRules;
import Interactor.Message.MessageInteractorCommand;
import Interactor.Message.MessageInteractorQuery;
import Interactor.Trip.TripInteractorCommand;
import Interactor.Trip.TripInteractorQuery;
import org.junit.Before;

import java.text.ParseException;

public class MessageInteractorTest {
    private static MessageInteractorQueryBoundary msg_query_i = MessageInteractorQuery.INSTANCE;
    private static MessageInteractorCommandBoundary msg_command_i = MessageInteractorCommand.INSTANCE;
    private static Trip trip;
    private static String driverAid;
    private static String riderAid;
    private static TripInteractorQueryBoundary trip_query_i = TripInteractorQuery.INSTANCE;
    private static TripInteractorCommandBoundary trip_command_i = TripInteractorCommand.INSTANCE;

    @Before
    public void setUp_createTrip_before_sending_msg() throws ParseException {
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
        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime("15-May-2020, 09:20");

        Rules r = BoundedRules.Make(2,6.0, "");

        trip = trip_query_i.createTrip(driverAid, l, car, dt, r);
        trip_command_i.registerTrip(trip);
    }

}
