package EntityTest.CarTest;

import Entity.Boundary.Trip.Car.Car;
import Entity.Boundary.Trip.Car.Vehicle.Vehicle;
import Entity.Bounded.Trip.Car.BoundedCar;
import Entity.Bounded.Trip.Car.NullBoundedCar;
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
    public void NullCar_is_Nil_must_return_true(){
        Car car = BoundedCar.Make();
        System.out.println(car.isNil());
        Assert.assertTrue(car.isNil());
    }
    @Test
    public void getVehicleInformation_should_return_VehicleDataType(){
       Car car = BoundedCar.Make(vehicleFullValid, "IL", "ILLTECH");
       Assert.assertEquals(BoundedVehicle.class, car.getVehicleInformation().getClass());
    }

    @Test(expected= Car.PlateSerialNotAllowedEmpty.class)
    public void if_PlateStateIsEmpty_it_throws_PlateStateIsEmptyException(){
        BoundedCar.Make(vehicleFullValid, "IL", "");
    }
    @Test(expected= Car.PlateStateNotAllowedToExceedTwoCharacter.class)
    public void if_PlateState_exceed_over_two_character_it_throws_PlateStateNotAllowedToExceedTwoCharacterException(){
        BoundedCar.Make(vehicleFullValid, "ILLINOIS", "ILLTECH");
    }

    @Test
    public void CarConstructorTest(){
        Car car = BoundedCar.Make(vehicleFullValid, "IL", "ILLTECH");
        Assert.assertEquals("IL", car.getPlateState());
        Assert.assertEquals("ILLTECH", car.getPlateSerial());
    }
}
