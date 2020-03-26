package InteractorTest;

import Boundary.MessageInteractorBoundary;
import Boundary.Trip.TripInteractorBoundary;
import Entity.Boundary.Account.CellPhoneFormat.CellPhoneFormat;
import Entity.Boundary.Account.User.User;
import Entity.Boundary.Message.Message;
import Entity.Boundary.Trip.Car.Car;
import Entity.Boundary.Trip.Car.Vehicle.Vehicle;
import Entity.Boundary.Trip.LocationInformation.Location.Location;
import Entity.Boundary.Trip.LocationInformation.LocationInformation;
import Entity.Boundary.Trip.Rules.Rules;
import Entity.Bounded.Account.CellPhoneFormat.BoundedCellPhoneFormat;
import Entity.Bounded.Account.User.BoundedUser;
import Entity.Bounded.Message.BoundedMessage;
import Entity.Bounded.Trip.Car.BoundedCar;
import Entity.Bounded.Trip.Car.Vehicle.BoundedVehicle;
import Entity.Bounded.Trip.LocationInformation.Location.BoundedLocation;
import Entity.Bounded.Trip.LocationInformation.BoundedLocationInformation;
import Entity.Bounded.Trip.Rules.BoundedRules;
import Interactor.MessageInteractor;
import Interactor.TripInteractor;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MessageInteractorTest {
    private static MessageInteractorBoundary mb = MessageInteractor.INSTANCE;
    private static String driverAid;
    private static String riderAid;
    private static TripInteractorBoundary tb = TripInteractor.INSTANCE;

    @BeforeClass
    public static void setUp_createTrip_before_sending_msg(){
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

        Rules p = BoundedRules.Make(2,6.0, conditions);
        tb.createTrip(l, car, p);

    }
    @Test
    public void whenSendMsgToRide_succeed_it_should_have_same_body_and_id(){
/*        mb.sendMsgToRide(rid, );
        Assert.assertEquals(rid, )
 */
    }
    @Test(expected=MessageInteractorBoundary.RideMatchedByRidNotFoundException.class)
    public void whenSendMsgToRide_failed_because_of_rid_not_found_it_should_throws_exception(){
       Message m = BoundedMessage.Make("5", "I am here!");
       mb.sendMsgToRide("asdf", m);
    }
}
