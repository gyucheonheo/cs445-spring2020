package ValidatorTest;

import Controller.Trip.Validator.CarInfoValidator;
import Controller.Trip.Validator.DateTimeValidator;
import Controller.Trip.Validator.LocationInfoValidator;
import com.google.gson.JsonObject;
import junit.framework.Assert;
import org.junit.Test;

public class TripValidatorTest {
    @Test
    public void car_info_is_null_error_message_should_be_car_info_is_null(){
        boolean b = CarInfoValidator.isValid(null);
        Assert.assertFalse(b);
        Assert.assertEquals("car_info is null.", CarInfoValidator.getErrorMessage());
    }

    @Test
    public void make_is_null_error_message_should_be_make_is_null(){
        JsonObject j = new JsonObject();
        boolean b = CarInfoValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("make is null", CarInfoValidator.getErrorMessage());
    }

    @Test
    public void make_is_empty_error_message_should_be_make_is_empty(){
        JsonObject j = new JsonObject();
        j.addProperty("make", "");
        boolean b = CarInfoValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("make is empty.", CarInfoValidator.getErrorMessage());
    }

    @Test
    public void model_is_null_error_message_should_be_model_is_null(){
        JsonObject j = new JsonObject();
        j.addProperty("make", "make");
        boolean b = CarInfoValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("model is null", CarInfoValidator.getErrorMessage());
    }
    @Test
    public void model_is_empty_error_message_should_be_model_is_empty(){
        JsonObject j = new JsonObject();
        j.addProperty("make", "make");
        j.addProperty("model", "");
        boolean b = CarInfoValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("model is empty.", CarInfoValidator.getErrorMessage());
    }
    @Test
    public void color_is_null_error_message_should_be_color_is_null(){
        JsonObject j = new JsonObject();
        j.addProperty("make", "make");
        j.addProperty("model", "model");
        boolean b = CarInfoValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("color is null", CarInfoValidator.getErrorMessage());
    }
    @Test
    public void color_is_empty_error_message_should_be_color_is_empty(){
        JsonObject j = new JsonObject();
        j.addProperty("make", "make");
        j.addProperty("model", "model");
        j.addProperty("color", "");
        boolean b = CarInfoValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("color is empty.", CarInfoValidator.getErrorMessage());
    }
    @Test
    public void plate_state_is_null_error_message_should_be_plate_state_is_null(){
        JsonObject j = new JsonObject();
        j.addProperty("make", "make");
        j.addProperty("model", "model");
        j.addProperty("color", "color");
        boolean b = CarInfoValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("plate_state is null", CarInfoValidator.getErrorMessage());
    }
    @Test
    public void plate_state_is_empty_error_message_should_be_plate_state_is_empty(){
        JsonObject j = new JsonObject();
        j.addProperty("make", "make");
        j.addProperty("model", "model");
        j.addProperty("color", "color");
        j.addProperty("plate_state", "");
        boolean b = CarInfoValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("plate_state is empty.", CarInfoValidator.getErrorMessage());
    }
    @Test
    public void plate_serial_is_null_error_message_should_be_plate_serial_is_null(){
        JsonObject j = new JsonObject();
        j.addProperty("make", "make");
        j.addProperty("model", "model");
        j.addProperty("color", "color");
        j.addProperty("plate_state", "plate_state");
        boolean b = CarInfoValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("plate_serial is null", CarInfoValidator.getErrorMessage());
    }
    @Test
    public void plate_serial_is_empty_error_message_should_be_plate_serial_is_empty(){
        JsonObject j = new JsonObject();
        j.addProperty("make", "make");
        j.addProperty("model", "model");
        j.addProperty("color", "color");
        j.addProperty("plate_state", "plate_state");
        j.addProperty("plate_serial", "");
        boolean b = CarInfoValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("plate_serial is empty.", CarInfoValidator.getErrorMessage());
    }
    @Test
    public void car_info_is_perfect_should_return_true(){
        JsonObject j = new JsonObject();
        j.addProperty("make", "make");
        j.addProperty("model", "model");
        j.addProperty("color", "color");
        j.addProperty("plate_state", "plate_state");
        j.addProperty("plate_serial", "plate_seiral");
        boolean b = CarInfoValidator.isValid(j);
        Assert.assertTrue(b);
    }
    @Test
    public void date_is_null(){
        JsonObject j = new JsonObject();
        boolean b = DateTimeValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("date is null.", DateTimeValidator.getErrorMessage());
    }
    @Test
    public void time_is_null(){
        JsonObject j = new JsonObject();
        j.addProperty("date", "04-Apr-2020");
        boolean b = DateTimeValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("time is null.", DateTimeValidator.getErrorMessage());
    }
    @Test
    public void date_format_should_be_mm_DDD_yyyy(){
        JsonObject j = new JsonObject();
        j.addProperty("date", "04-11-2020");
        j.addProperty("time", "09:05");
        boolean b = DateTimeValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("Invalid date", DateTimeValidator.getErrorMessage());
    }
    @Test
    public void time_format_should_be_hh_mm(){
        JsonObject j = new JsonObject();
        j.addProperty("date", "04-Apr-2020");
        j.addProperty("time", "eight:05");
        boolean b = DateTimeValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("Invalid time", DateTimeValidator.getErrorMessage());
    }
    @Test
    public void everything_is_perfect_should_return_true(){
        JsonObject j = new JsonObject();
        j.addProperty("date", "04-Apr-2020");
        j.addProperty("time", "08:05");
        boolean b = DateTimeValidator.isValid(j);
        Assert.assertTrue(b);
    }
    @Test
    public void loc_info_is_null(){
        boolean b = LocationInfoValidator.isValid(null);
        Assert.assertFalse(b);
        Assert.assertEquals("location_info is null.", LocationInfoValidator.getErrorMessage());
    }
    @Test
    public void from_city_is_null(){
        JsonObject j = new JsonObject();
        boolean b = LocationInfoValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("from_city is null", LocationInfoValidator.getErrorMessage());
    }
    @Test
    public void from_city_is_empty(){
        JsonObject j = new JsonObject();
        j.addProperty("from_city", "");
        j.addProperty("from_zip", "");
        boolean b = LocationInfoValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("from_city is empty", LocationInfoValidator.getErrorMessage());
    }

    @Test
    public void null_city_is_empty(){
        JsonObject j = new JsonObject();
        j.addProperty("from_city", "Chicago");
        j.addProperty("from_zip", "");
        boolean b = LocationInfoValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("to_city is null", LocationInfoValidator.getErrorMessage());
    }

    @Test
    public void to_city_is_empty(){
        JsonObject j = new JsonObject();
        j.addProperty("from_city", "Chicago");
        j.addProperty("from_zip", "");
        j.addProperty("to_city", "");
        j.addProperty("to_zip", "");
        boolean b = LocationInfoValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("to_city is empty", LocationInfoValidator.getErrorMessage());
    }
    @Test
    public void when_perfect_LocationInfoValidator_should_return_true(){
        JsonObject j = new JsonObject();
        j.addProperty("from_city", "Chicago");
        j.addProperty("from_zip", "");
        j.addProperty("to_city", "St Louis");
        j.addProperty("to_zip", "");
        boolean b = LocationInfoValidator.isValid(j);
        Assert.assertTrue(b);
    }
}
