package EntityTest.CarTest;

import Entity.Boundary.Trip.Car.Car;
import Entity.Boundary.Trip.Car.Vehicle.Vehicle;
import Entity.Bounded.Trip.Car.BoundedCar;
import Entity.Bounded.Trip.Car.Vehicle.BoundedVehicle;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class CarTest {
    private Vehicle vehicleFullValid;
    @Before
     public void setUp() {
        vehicleFullValid = BoundedVehicle.Make("Hyundai", "Genesis", "Black");
    }

    @Test
    public void NormalCar_isNil_must_return_false(){
        Car car = BoundedCar.Make(vehicleFullValid, "IL", "ILLTECH");
        Assert.assertFalse(car.isNil());
    }

    @Test
    public void getVehicleInformation_should_return_VehicleDataType(){
       Car car = BoundedCar.Make(vehicleFullValid, "IL", "ILLTECH");
       Assert.assertEquals(BoundedVehicle.class, car.getVehicleInformation().getClass());
    }

    @Test
    public void if_PlateState_has_lowercase_letters_it_should_convert_to_uppercase(){
       Car c = BoundedCar.Make(vehicleFullValid, "il", "ILLTECH");
       Assert.assertEquals("IL", c.getPlateState());
    }
    @Test
    public void CarConstructorTest(){
        Car car = BoundedCar.Make(vehicleFullValid, "IL", "ILLTECH");
        Assert.assertEquals("IL", car.getPlateState());
        Assert.assertEquals("ILLTECH", car.getPlateSerial());
    }
}
