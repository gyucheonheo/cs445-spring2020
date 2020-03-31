package InteractorTest;

import Boundary.Account.User.AccountInteractorBoundary;
import Entity.Boundary.Account.User.User;
import Entity.Bounded.Account.CellPhoneFormat.BoundedCellPhoneFormat;
import Entity.Bounded.Account.User.BoundedUser;
import Interactor.AccountInteractor;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AccountInteractorTest {

    private User initialUser;
    private AccountInteractorBoundary ai;
    @Before
    public void setUp(){
        initialUser = BoundedUser.Make("Test","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        ai = AccountInteractor.INSTANCE;
    }

    @Test
    public void before_activating_user_isActive_must_be_false() {
        Assert.assertFalse(initialUser.getIsActive());
    }
    @Test(expected=AccountInteractorBoundary.UserNotFoundException.class)
    public void activateUser_to_no_aid_throws_UserNotFoundException(){
        ai.activateUser("asdf");
    }
    @Test public void activateUser_must_set_isActive_to_true(){
        ai.registerUser(initialUser);
        String aid = initialUser.getAid();
        ai.activateUser(aid);
        Assert.assertTrue(initialUser.getIsActive());
    }

    @Test
    public void updateUser_succeeds_when_everything_is_ok(){
        ai.registerUser(initialUser);
        ai.updateUser(initialUser.getAid(),"Jake", "Heo", BoundedCellPhoneFormat.Make("111","222","3333"), "google.com");
        Assert.assertEquals(initialUser.getFirstName(), "Jake");
        Assert.assertEquals(initialUser.getLastName(), "Heo");
        Assert.assertEquals(initialUser.getCellPhoneFormat().getFirst(), "111");
        Assert.assertEquals(initialUser.getCellPhoneFormat().getMiddle(), "222");
        Assert.assertEquals(initialUser.getCellPhoneFormat().getLast(), "3333");
    }
    @Test(expected=AccountInteractorBoundary.UserNotFoundException.class)
    public void updateUser_throws_UserNotFoundException_when_there_is_no_aid(){
        ai.updateUser("asdf", "Jake", "Heo", BoundedCellPhoneFormat.Make("111","222","3333"), "google.com");
    }

    @Test(expected=AccountInteractorBoundary.UserNotFoundException.class)
    public void deleteUser_succeeds_and_should_throws_UserNotFoundException_when_trying_to_access(){
        String aid = initialUser.getAid();
        ai.deleteUser(initialUser.getAid());
        ai.getUserById(aid);
    }

    @Test
    public void searchUserByKeyword_empty_keyword_should_matchAll(){
        User user1 = BoundedUser.Make("Test","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        User user2 = BoundedUser.Make("Test","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        User user3 = BoundedUser.Make("Test","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        User user4 = BoundedUser.Make("Test","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        ai.registerUser(user1);
        ai.registerUser(user2);
        ai.registerUser(user3);
        ai.registerUser(user4);

        List<User> results = ai.searchUserByKeyword("");
        Assert.assertEquals(4, results.size());
    }

    @Test
    public void searchUserByKeyword_Test_keyword_should_match_2(){
        User user1 = BoundedUser.Make("Test","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        User user2 = BoundedUser.Make("Test2","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        User user3 = BoundedUser.Make("Pearson","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        User user4 = BoundedUser.Make("Peterson","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        ai.registerUser(user1);
        ai.registerUser(user2);
        ai.registerUser(user3);
        ai.registerUser(user4);

        List<User> results = ai.searchUserByKeyword("Test");
        Assert.assertEquals(2, results.size());
    }
    @Test
    public void searchUserByKeyword_no_match_keyword_should_match_zero(){
        User user1 = BoundedUser.Make("Test","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        User user2 = BoundedUser.Make("Test2","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        User user3 = BoundedUser.Make("Pearson","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        User user4 = BoundedUser.Make("Peterson","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        ai.registerUser(user1);
        ai.registerUser(user2);
        ai.registerUser(user3);
        ai.registerUser(user4);

        List<User> results = ai.searchUserByKeyword("!@%@#@!");
        Assert.assertEquals(0, results.size());
    }



}
