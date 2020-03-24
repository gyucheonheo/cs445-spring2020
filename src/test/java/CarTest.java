import Entity.Trip.Car.Car;
import junit.framework.Assert;
import org.junit.Test;

public class CarTest {
    @Test(expected=Car.MakeNotAllowedEmpty.class)
    public void if_make_is_empty_it_throws_MakeIsEmtpyException(){
        Car car = new Car("", "Elantra", "White", "IL", "ILLTECH");
    }
    @Test(expected=Car.ModelNotAllowedEmpty.class)
    public void if_model_is_empty_it_throws_ModelIsEmptyException(){
        Car car = new Car("Hyundai", "", "White", "IL", "ILLTECH");
    }
    @Test(expected=Car.ColorNotAllowedEmpty.class)
    public void if_color_is_empty_it_throws_ColorIsEmptyException(){
        Car car = new Car("Hyundai", "Elantra", "", "IL", "ILLTECH");
    }
    @Test(expected=Car.PlateSerialNotAllowedEmpty.class)
    public void if_PlateStateIsEmpty_it_throws_PlateStateIsEmptyException(){
        Car car = new Car("Hyundai", "Elantra", "White", "IL", "");
    }
    @Test(expected=Car.PlateStateNotAllowedToExceedTwoCharacter.class)
    public void if_PlateState_exceed_over_two_character_it_throws_PlateStateNotAllowedToExceedTwoCharacterException(){
        Car car = new Car("Hyundai", "Elantra", "White", "ILLINOIS", "ILLTECH");
    }

    @Test
    public void CarConstructorTest(){
        Car car = new Car("Hyundai", "Elantra", "White", "IL", "ILLTECH");
        Assert.assertEquals("Hyundai", car.getMake());
        Assert.assertEquals("Elantra", car.getModel());
        Assert.assertEquals("White", car.getColor());
        Assert.assertEquals("IL", car.getPlateState());
        Assert.assertEquals("ILLTECH", car.getPlateSerial());
    }
}
