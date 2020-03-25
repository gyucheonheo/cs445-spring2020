import Entity.Trip.Car.Vehicle.Vehicle;
import junit.framework.Assert;
import org.junit.Test;

public class VehicleTest {

    @Test(expected= Vehicle.MakeNotAllowedEmpty.class)
    public void if_make_is_empty_it_throws_MakeIsEmtpyException(){
        Vehicle vehicleEmptyMake = new Vehicle("", "Genesis", "Black");
    }
    @Test(expected=Vehicle.ModelNotAllowedEmpty.class)
    public void if_model_is_empty_it_throws_ModelIsEmptyException(){
        Vehicle vehicleEmptyModel = new Vehicle("Hyundai", "", "Black");
    }
    @Test(expected=Vehicle.ColorNotAllowedEmpty.class)
    public void if_color_is_empty_it_throws_ColorIsEmptyException(){
        Vehicle vehicleEmptyColor = new Vehicle("Hyundai", "Genesis", "");
    }
    @Test
    public void vehicle_constructor_test(){
        Vehicle v = new Vehicle("Ford", "F-150", "Military green");
        Assert.assertEquals("Ford", v.getMake());
        Assert.assertEquals("F-150", v.getModel());
        Assert.assertEquals("Military green", v.getColor());
    }
}
