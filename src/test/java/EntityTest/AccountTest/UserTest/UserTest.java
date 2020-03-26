package EntityTest.AccountTest.UserTest;

import Entity.Boundary.Account.CellPhoneFormat.CellPhoneFormat;
import Entity.Boundary.Account.User.User;
import Entity.Bounded.Account.CellPhoneFormat.BoundedCellPhoneFormat;
import Entity.Bounded.Account.User.BoundedUser;
import Entity.Bounded.Account.User.RideInformation.BoundedRideInformation;
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
    @Test(expected= User.FirstNameBeingEmptyIsInvalid.class)
    public void whenCreateUser_and_empty_first_name_throws_firstName_empty_exception(){
       BoundedUser.Make("", "a", cellPhone, pictureURL);
    }
    @Test(expected= User.LastNameBeingEmptyIsInvalid.class)
    public void whenCreateUser_and_empty_last_name_throws_lastName_empty_exception(){
        BoundedUser.Make("a", "", cellPhone, pictureURL);
    }
    @Test
    public void whenCreateUser_getAsDriver_must_be_of_RideInformation_class(){
        User testUser = BoundedUser.Make("John", "Doe", cellPhone, pictureURL);
        Assert.assertEquals(BoundedRideInformation.class, testUser.getAsDriver().getClass());
    }

    @Test
    public void whenCreateUser_getAsRider_must_be_of_RideInformation_class(){
        User testUser = BoundedUser.Make("John", "Doe", cellPhone, pictureURL);
        Assert.assertEquals(BoundedRideInformation.class, testUser.getAsRider().getClass());
    }

    @Test
    public void createUser_must_set_isActive_to_false_initially(){
        User testUser = BoundedUser.Make("John", "Doe", cellPhone, pictureURL);
        Assert.assertFalse(testUser.getIsActive());
    }
}
