import Entity.Account.CellPhoneFormat.CellPhoneFormat;
import Entity.Account.User.User;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
    private CellPhoneFormat cellPhone;
    private String pictureURL;
    @Before
    public void setUp(){
        cellPhone = new CellPhoneFormat("312","444","5555");
        pictureURL = "http://example.com/gheo1/picutre.jpg";
    }
    @Test(expected=User.FirstNameBeingEmptyIsInvalid.class)
    public void whenCreateUser_and_empty_first_name_throws_firstName_empty_exception(){
       User testUser = new User("", "a", cellPhone, pictureURL);
    }
    @Test(expected=User.LastNameBeingEmptyIsInvalid.class)
    public void whenCreateUser_and_empty_last_name_throws_lastName_empty_exception(){
        User testUser = new User("a", "", cellPhone, pictureURL);
    }
   @Test
    public void createUser_must_set_isActive_to_false_initially(){
        User testUser = new User("John", "Doe", cellPhone, pictureURL);
        Assert.assertFalse(testUser.getIsActive());
    }
}
