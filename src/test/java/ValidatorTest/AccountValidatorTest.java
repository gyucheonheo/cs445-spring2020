package ValidatorTest;

import Controller.Account.Validator.AccountValidator;
import com.google.gson.JsonObject;
import junit.framework.Assert;
import org.junit.Test;

public class AccountValidatorTest {
    @Test
    public void null_first_name_should_have_err_message(){
       JsonObject j = new JsonObject();
       boolean b = AccountValidator.isValid(j);
       Assert.assertFalse(b);
       Assert.assertEquals("First name is null", AccountValidator.getErrorMessage());
    }

    @Test
    public void empty_first_name_should_have_err_message(){
        JsonObject j = new JsonObject();
        j.addProperty("first_name", "");
        boolean b = AccountValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("First name is empty", AccountValidator.getErrorMessage());
    }

    @Test
    public void digit_first_name_should_have_err_message(){
        JsonObject j = new JsonObject();
        j.addProperty("first_name", "1234");
        boolean b = AccountValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("The first name appears to be invalid.", AccountValidator.getErrorMessage());
    }

    @Test
    public void null_last_name_should_have_err_message(){
        JsonObject j = new JsonObject();
        j.addProperty("first_name", "Test");
        boolean b = AccountValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("Last name is null", AccountValidator.getErrorMessage());
    }
    @Test
    public void empty_last_name_should_have_err_message(){
        JsonObject j = new JsonObject();
        j.addProperty("first_name", "Test");
        j.addProperty("last_name", "");
        boolean b = AccountValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("Last name is empty", AccountValidator.getErrorMessage());
    }

    @Test
    public void digit_last_name_should_have_err_message(){
        JsonObject j = new JsonObject();
        j.addProperty("first_name", "Test");
        j.addProperty("last_name", "1234");
        boolean b = AccountValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("The last name appears to be invalid.", AccountValidator.getErrorMessage());
    }
    @Test
    public void null_phone_number_should_have_err_message(){
        JsonObject j = new JsonObject();
        j.addProperty("first_name", "Test");
        j.addProperty("last_name", "TestLast");
        boolean b = AccountValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("phone is null", AccountValidator.getErrorMessage());
    }
    @Test
    public void invalid_phone_number_should_have_err_message(){
        JsonObject j = new JsonObject();
        j.addProperty("first_name", "Test");
        j.addProperty("last_name", "TestLast");
        j.addProperty("phone", "12342-12342");
        boolean b = AccountValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("Invalid phone number", AccountValidator.getErrorMessage());
    }
    @Test
    public void invalid_phone_number_should_have_err_message_digit_combination(){
        JsonObject j = new JsonObject();
        j.addProperty("first_name", "Test");
        j.addProperty("last_name", "TestLast");
        j.addProperty("phone", "312-2O1-2939");
        boolean b = AccountValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("Invalid phone number", AccountValidator.getErrorMessage());
    }
    @Test
    public void isActive_must_not_be_true_or_null(){
        JsonObject j = new JsonObject();
        j.addProperty("first_name", "Test");
        j.addProperty("last_name", "TestLast");
        j.addProperty("phone", "312-224-2939");
        j.addProperty("is_active", true);
        boolean b = AccountValidator.isValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("Invalid value for is_active", AccountValidator.getErrorMessage());
    }

    @Test
    public void when_perfect_it_should_return_true(){
        JsonObject j = new JsonObject();
        j.addProperty("first_name", "Test");
        j.addProperty("last_name", "TestLast");
        j.addProperty("phone", "312-224-2939");
        j.addProperty("is_active", false);
        boolean b = AccountValidator.isValid(j);
        Assert.assertTrue(b);
    }
    @Test
    public void null_first_name_should_have_err_message_isActiveInfoValid(){
        JsonObject j = new JsonObject();
        boolean b = AccountValidator.isActivateInfoValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("First name is null", AccountValidator.getErrorMessage());
    }

    @Test
    public void empty_first_name_should_have_err_message_isActivateInfoValid(){
        JsonObject j = new JsonObject();
        j.addProperty("first_name", "");
        boolean b = AccountValidator.isActivateInfoValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("First name is empty", AccountValidator.getErrorMessage());
    }

    @Test
    public void digit_first_name_should_have_err_message_isActivateInfoValid(){
        JsonObject j = new JsonObject();
        j.addProperty("first_name", "1234");
        boolean b = AccountValidator.isActivateInfoValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("The first name appears to be invalid.", AccountValidator.getErrorMessage());
    }

    @Test
    public void null_last_name_should_have_err_message_isActivateInfoValid(){
        JsonObject j = new JsonObject();
        j.addProperty("first_name", "Test");
        boolean b = AccountValidator.isActivateInfoValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("Last name is null", AccountValidator.getErrorMessage());
    }
    @Test
    public void empty_last_name_should_have_err_message_isActivateInfoValid(){
        JsonObject j = new JsonObject();
        j.addProperty("first_name", "Test");
        j.addProperty("last_name", "");
        boolean b = AccountValidator.isActivateInfoValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("Last name is empty", AccountValidator.getErrorMessage());
    }

    @Test
    public void digit_last_name_should_have_err_message_isActivateInfoValid(){
        JsonObject j = new JsonObject();
        j.addProperty("first_name", "Test");
        j.addProperty("last_name", "1234");
        boolean b = AccountValidator.isActivateInfoValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("The last name appears to be invalid.", AccountValidator.getErrorMessage());
    }
    @Test
    public void null_phone_number_should_have_err_message_isActivateInfoValid(){
        JsonObject j = new JsonObject();
        j.addProperty("first_name", "Test");
        j.addProperty("last_name", "TestLast");
        boolean b = AccountValidator.isActivateInfoValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("phone is null", AccountValidator.getErrorMessage());
    }
    @Test
    public void invalid_phone_number_should_have_err_message_isActivateInfoValid(){
        JsonObject j = new JsonObject();
        j.addProperty("first_name", "Test");
        j.addProperty("last_name", "TestLast");
        j.addProperty("phone", "12342-12342");
        boolean b = AccountValidator.isActivateInfoValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("Invalid phone number", AccountValidator.getErrorMessage());
    }
    @Test
    public void invalid_phone_number_should_have_err_message_2_isActivateInfoValid(){
        JsonObject j = new JsonObject();
        j.addProperty("first_name", "Test");
        j.addProperty("last_name", "TestLast");
        j.addProperty("phone", "312-2O1-2939");
        boolean b = AccountValidator.isActivateInfoValid(j);
        Assert.assertFalse(b);
        Assert.assertEquals("Invalid phone number", AccountValidator.getErrorMessage());
    }

    @Test
    public void when_perfect_of_isActiveInfoValid_should_return_true(){
        JsonObject j = new JsonObject();
        j.addProperty("first_name", "Test");
        j.addProperty("last_name", "TestLast");
        j.addProperty("phone", "312-211-2939");
        boolean b = AccountValidator.isActivateInfoValid(j);
        Assert.assertTrue(b);
    }
}
