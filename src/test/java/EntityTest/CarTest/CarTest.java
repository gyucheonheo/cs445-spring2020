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
    @Test(expected= Car.PlateStateAllowedOnlyTwoCharacter.class)
    public void if_PlateState_has_Number_it_throws_PlateStateOnlyAllowedCharacter(){
        BoundedCar.Make(vehicleFullValid, "12", "ILLTECH");
    }
    @Test(expected= Car.PlateStateAllowedOnlyTwoCharacter.class)
    public void if_PlateState_has_two_spaces_it_throws_PlateStateOnlyAllowedCharacter(){
        BoundedCar.Make(vehicleFullValid, "  ", "ILLTECH");
    }
    @Test(expected=Car.PlateStateAllowedOnlyTwoCharacter.class)
    public void if_PlateState_has_one_space_and_letter_it_throws_PlateStateOnlyAllowedCharacter(){
        BoundedCar.Make(vehicleFullValid, " L", "ILLTECH");
    }
    @Test(expected=Car.PlateStateAllowedOnlyTwoCharacter.class)
    public void if_PlateState_has_one_letter_and_space_it_throws_PlateStateOnlyAllowedCharacter(){
        BoundedCar.Make(vehicleFullValid, "I ", "ILLTECH");
    }
    @Test(expected=Car.PlateStateNotAllowedToExceedTwoCharacter.class)
    public void if_PlateState_has_space_two_letters_it_throws_PlateStateOnlyAllowedCharacter(){
        BoundedCar.Make(vehicleFullValid, " IL", "ILLTECH");
    }
    @Test(expected=Car.PlateStateNotAllowedToExceedTwoCharacter.class)
    public void if_PlateState_has_two_letters_and_space_it_throws_PlateStateOnlyAllowedCharacter(){
        BoundedCar.Make(vehicleFullValid, "IL ", "ILLTECH");
    }
    @Test(expected= Car.PlateStateAllowedOnlyTwoCharacter.class)
    public void if_PlateState_has_two_special_characters_it_throws_PlateStateOnlyAllowedCharacter(){
        BoundedCar.Make(vehicleFullValid, "@@", "ILLTECH");
    }
    @Test(expected= Car.PlateStateAllowedOnlyTwoCharacter.class)
    public void if_PlateState_has_one_special_characters_it_throws_PlateStateOnlyAllowedCharacter(){
        BoundedCar.Make(vehicleFullValid, "I@", "ILLTECH");
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
