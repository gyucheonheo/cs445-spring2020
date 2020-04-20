package ParserTest;

import Controller.Trip.Parser.CarInfoParser;
import Entity.Boundary.Trip.Car.Car;
import com.google.gson.JsonObject;
import junit.framework.Assert;
import org.junit.Test;

public class CarInfoParserTest {
   @Test
   public void car_parse_should_parse_and_return_BoundedCarInfo(){
      JsonObject car = new JsonObject();
      car.addProperty("make", "testmake");
      car.addProperty("model", "testmodel");
      car.addProperty("color", "testcolor");
      car.addProperty("plate_state", "testplatestate");
      car.addProperty("plate_serial", "testplateserial");

      Car c = CarInfoParser.parse(car);
      Assert.assertEquals("testmake", c.getVehicleInformation().getMake());
      Assert.assertEquals("testmodel", c.getVehicleInformation().getModel());
      Assert.assertEquals("testcolor", c.getVehicleInformation().getColor());
      Assert.assertEquals("TESTPLATESTATE", c.getPlateState());
      Assert.assertEquals("testplateserial", c.getPlateSerial());
   }
}
