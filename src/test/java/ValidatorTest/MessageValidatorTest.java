package ValidatorTest;

import Boundary.Account.AccountInteractorCommandBoundary;
import Controller.Message.Validator.MessageValidator;
import Entity.Boundary.Account.User.User;
import Entity.Bounded.Account.CellPhoneFormat.BoundedCellPhoneFormat;
import Entity.Bounded.Account.User.BoundedUser;
import Interactor.Account.AccountInteractorCommand;
import com.google.gson.JsonObject;
import junit.framework.Assert;
import org.junit.Test;

public class MessageValidatorTest {
    @Test
    public void null_aid_should_return_err_message(){
        JsonObject j = new JsonObject();
        boolean b = MessageValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("aid is null", MessageValidator.getErrorMessage());
    }
    @Test
    public void empty_aid_should_return_err_message(){
        JsonObject j = new JsonObject();
        j.addProperty("aid", "");
        boolean b = MessageValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("aid is empty", MessageValidator.getErrorMessage());
    }
    @Test
    public void inactive_user_should_return_err_message(){
        User user1 = BoundedUser.Make("Test","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        AccountInteractorCommandBoundary acc_command_i = AccountInteractorCommand.INSTANCE;
        acc_command_i.registerUser(user1);
        JsonObject j = new JsonObject();
        j.addProperty("aid", user1.getAid());
        boolean b = MessageValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("Account is not active", MessageValidator.getErrorMessage());
    }
    @Test
    public void null_msg_body_should_return_err_message(){
        User user1 = BoundedUser.Make("Test","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        AccountInteractorCommandBoundary acc_command_i = AccountInteractorCommand.INSTANCE;
        acc_command_i.registerUser(user1);
        user1.setIsActive(true);
        JsonObject j = new JsonObject();
        j.addProperty("aid", user1.getAid());
        boolean b = MessageValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("msg is null", MessageValidator.getErrorMessage());
    }
    @Test
    public void empty_message_body_should_return_err_message(){
        User user1 = BoundedUser.Make("Test","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        AccountInteractorCommandBoundary acc_command_i = AccountInteractorCommand.INSTANCE;
        acc_command_i.registerUser(user1);
        user1.setIsActive(true);
        JsonObject j = new JsonObject();
        j.addProperty("aid", user1.getAid());
        j.addProperty("msg", "");
        boolean b = MessageValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("msg is empty", MessageValidator.getErrorMessage());
    }
    @Test
    public void everything_is_perfect_should_be_true(){
        User user1 = BoundedUser.Make("Test","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        AccountInteractorCommandBoundary acc_command_i = AccountInteractorCommand.INSTANCE;
        acc_command_i.registerUser(user1);
        user1.setIsActive(true);
        JsonObject j = new JsonObject();
        j.addProperty("aid", user1.getAid());
        j.addProperty("msg", "Hello");
        boolean b = MessageValidator.isValid(j);
        Assert.assertTrue(b);
    }
}
