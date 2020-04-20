package EntityTest.AccountTest.UserTest;

import Entity.Boundary.Account.CellPhoneFormat.CellPhoneFormat;
import Entity.Boundary.Account.User.User;
import Entity.Bounded.Account.CellPhoneFormat.BoundedCellPhoneFormat;
import Entity.Bounded.Account.User.BoundedUser;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
    private CellPhoneFormat cellPhone;
    private String pictureURL;
    @Before
    public void setUp(){
        cellPhone = BoundedCellPhoneFormat.Make("312","444","5555");
        pictureURL = "http://example.com/gheo1/picutre.jpg";
    }

    @Test
    public void createUser_must_set_isActive_to_false_initially(){
        User testUser = BoundedUser.Make("John", "Doe", cellPhone, pictureURL);
        Assert.assertFalse(testUser.getIsActive());
    }

    @Test
    public void createUser_must_set_isActive_to_the_given_boolean(){
        User testUser = BoundedUser.Make("John", "Doe", cellPhone, pictureURL);
        testUser.setIsActive(true);
        Assert.assertTrue(testUser.getIsActive());
        testUser.setIsActive(false);
        Assert.assertFalse(testUser.getIsActive());
    }
    @Test
    public void NullUser_isNil_should_be_true(){
        User nullUser = BoundedUser.Make();
        Assert.assertTrue(nullUser.isNil());
    }
    @Test
    public void RegularUser_isNil_should_be_false(){
        User testUser = BoundedUser.Make("John", "Doe", cellPhone, pictureURL);
        Assert.assertFalse(testUser.isNil());
    }
}
