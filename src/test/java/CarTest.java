import Entity.Trip.Car.Car;
import Entity.Trip.Car.Vehicle.Vehicle;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class CarTest {
    private Vehicle vehicleFullValid;
    @Before
     public void setUp() {
        vehicleFullValid = new Vehicle("Hyundai", "Genesis", "Black");
    }
    @Test(expected=Car.PlateSerialNotAllowedEmpty.class)
    public void if_PlateStateIsEmpty_it_throws_PlateStateIsEmptyException(){
        Car car = new Car(vehicleFullValid, "IL", "");
    }
    @Test(expected=Car.PlateStateNotAllowedToExceedTwoCharacter.class)
    public void if_PlateState_exceed_over_two_character_it_throws_PlateStateNotAllowedToExceedTwoCharacterException(){
        Car car = new Car(vehicleFullValid, "ILLINOIS", "ILLTECH");
    }

    @Test
    public void CarConstructorTest(){
        Car car = new Car(vehicleFullValid, "IL", "ILLTECH");
        Assert.assertEquals("IL", car.getPlateState());
        Assert.assertEquals("ILLTECH", car.getPlateSerial());
    }
}
