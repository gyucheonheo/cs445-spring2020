package EntityTest.CarTest.VehicleTest;

import Entity.Boundary.Trip.Car.Vehicle.Vehicle;
import Entity.Bounded.Trip.Car.Vehicle.BoundedVehicle;
import junit.framework.Assert;
import org.junit.Test;

public class VehicleTest {

    @Test
    public void vehicle_constructor_test(){
        Vehicle v = BoundedVehicle.Make("Ford", "F-150", "Military green");
        Assert.assertEquals("Ford", v.getMake());
        Assert.assertEquals("F-150", v.getModel());
        Assert.assertEquals("Military green", v.getColor());
    }
}
