package ValidatorTest;

import Boundary.Account.AccountInteractorCommandBoundary;
import Controller.RideRequest.Validator.RequestValidator;
import Entity.Boundary.Account.User.User;
import Entity.Bounded.Account.CellPhoneFormat.BoundedCellPhoneFormat;
import Entity.Bounded.Account.User.BoundedUser;
import Interactor.Account.AccountInteractorCommand;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import junit.framework.Assert;
import org.junit.Test;

public class RideRequestValidatorTest {
    @Test
    public void NullUser_should_return_false_and_User_does_not_exist_err_message(){
        JsonObject j = new JsonObject();
        j.addProperty("aid", "asdf");
        j.add("ride_confirmed", JsonNull.INSTANCE);
        j.add("pickup_confirmed", JsonNull.INSTANCE);
        boolean b = RequestValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("User does not exist", RequestValidator.getErrorMessage());
    }
    @Test
    public void User_is_not_active_should_return_error_message(){
        User user1 = BoundedUser.Make("Test","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        AccountInteractorCommandBoundary acc_command_i = AccountInteractorCommand.INSTANCE;
        acc_command_i.registerUser(user1);
        JsonObject j = new JsonObject();
        j.addProperty("aid", user1.getAid());
        j.add("ride_confirmed", JsonNull.INSTANCE);
        j.add("pickup_confirmed", JsonNull.INSTANCE);
        boolean b = RequestValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("This account ("+user1.getAid()+") is not active, may not create a join ride request.", RequestValidator.getErrorMessage());
    }
    @Test
    public void rideConfirmedBeingTrue_is_failed(){
        User user1 = BoundedUser.Make("Test","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        AccountInteractorCommandBoundary acc_command_i = AccountInteractorCommand.INSTANCE;
        acc_command_i.registerUser(user1);
        user1.setIsActive(true);
        JsonObject j = new JsonObject();
        j.addProperty("aid", user1.getAid());
        j.addProperty("ride_confirmed",true);
        j.add("pickup_confirmed", JsonNull.INSTANCE);
        boolean b = RequestValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("Invalid value for ride_confirmed", RequestValidator.getErrorMessage());
    }
    @Test
    public void pickupConfirmedBeingFalse_is_failed(){
        User user1 = BoundedUser.Make("Test","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        AccountInteractorCommandBoundary acc_command_i = AccountInteractorCommand.INSTANCE;
        acc_command_i.registerUser(user1);
        user1.setIsActive(true);
        JsonObject j = new JsonObject();
        j.addProperty("aid", user1.getAid());
        j.add("ride_confirmed",JsonNull.INSTANCE);
        j.addProperty("pickup_confirmed", false);
        boolean b = RequestValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("Invalid value for pickup_confirmed", RequestValidator.getErrorMessage());
    }
    @Test
    public void everything_perfect_should_be_true(){
        User user1 = BoundedUser.Make("Test","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        AccountInteractorCommandBoundary acc_command_i = AccountInteractorCommand.INSTANCE;
        acc_command_i.registerUser(user1);
        user1.setIsActive(true);
        JsonObject j = new JsonObject();
        j.addProperty("aid", user1.getAid());
        j.add("ride_confirmed",JsonNull.INSTANCE);
        j.add("pickup_confirmed", JsonNull.INSTANCE);
        boolean b = RequestValidator.isValid(j);
        Assert.assertTrue(b);
    }
}
