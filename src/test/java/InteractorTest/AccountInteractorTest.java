package InteractorTest;

import Boundary.Account.AccountInteractorCommandBoundary;
import Boundary.Account.AccountInteractorQueryBoundary;
import Entity.Boundary.Account.User.User;
import Entity.Bounded.Account.CellPhoneFormat.BoundedCellPhoneFormat;
import Entity.Bounded.Account.User.BoundedUser;
import Interactor.Account.AccountInteractor;
import Interactor.Account.AccountInteractorCommand;
import Interactor.Account.AccountInteractorQuery;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AccountInteractorTest {

    private User initialUser;
    private AccountInteractorQueryBoundary acc_query_i;
    private AccountInteractorCommandBoundary acc_command_i;

    @Before
    public void setUp(){
        initialUser = BoundedUser.Make("Test","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        acc_query_i = AccountInteractorQuery.INSTANCE;
        acc_command_i = AccountInteractorCommand.INSTANCE;
    }
    @After
    public void tearUp(){
        AccountInteractor acc = AccountInteractor.INSTANCE;
        acc.getUsers().clear();
    }
    @Test
    public void before_activating_user_isActive_must_be_false() {
        Assert.assertFalse(initialUser.getIsActive());
    }

    @Test public void activateUser_must_set_isActive_to_true(){
        acc_command_i.registerUser(initialUser);
        String aid = initialUser.getAid();
        acc_command_i.activateUser(aid);
        Assert.assertTrue(initialUser.getIsActive());
    }

    @Test
    public void updateUser_succeeds_when_everything_is_ok(){
        acc_command_i.registerUser(initialUser);
        acc_command_i.updateUser(initialUser.getAid(),"Jake", "Heo", BoundedCellPhoneFormat.Make("111","222","3333"), "google.com");
        Assert.assertEquals(initialUser.getFirstName(), "Jake");
        Assert.assertEquals(initialUser.getLastName(), "Heo");
        Assert.assertEquals(initialUser.getCellPhoneFormat().getFirst(), "111");
        Assert.assertEquals(initialUser.getCellPhoneFormat().getMiddle(), "222");
        Assert.assertEquals(initialUser.getCellPhoneFormat().getLast(), "3333");
    }

    @Test
    public void searchUserByKeyword_empty_keyword_should_matchAll(){
        User user1 = BoundedUser.Make("Test","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        User user2 = BoundedUser.Make("Test","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        User user3 = BoundedUser.Make("Test","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        User user4 = BoundedUser.Make("Test","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        acc_command_i.registerUser(user1);
        acc_command_i.registerUser(user2);
        acc_command_i.registerUser(user3);
        acc_command_i.registerUser(user4);

        List<User> results = acc_query_i.getAllUsers("");

        Assert.assertEquals(4, results.size());
    }

    @Test
    public void searchUserByKeyword_Test_keyword_should_match_2(){
        User user1 = BoundedUser.Make("Test","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        User user2 = BoundedUser.Make("Test2","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        User user3 = BoundedUser.Make("Pearson","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        User user4 = BoundedUser.Make("Peterson","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        acc_command_i.registerUser(user1);
        acc_command_i.registerUser(user2);
        acc_command_i.registerUser(user3);
        acc_command_i.registerUser(user4);

        List<User> results = acc_query_i.getAllUsers("Test");
        Assert.assertEquals(2, results.size());
    }
    @Test
    public void searchUserByKeyword_no_match_keyword_should_match_zero(){
        User user1 = BoundedUser.Make("Test","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        User user2 = BoundedUser.Make("Test2","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        User user3 = BoundedUser.Make("Pearson","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        User user4 = BoundedUser.Make("Peterson","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        acc_command_i.registerUser(user1);
        acc_command_i.registerUser(user2);
        acc_command_i.registerUser(user3);
        acc_command_i.registerUser(user4);

        List<User> results = acc_query_i.getAllUsers("!@%@#@!");
        Assert.assertEquals(0, results.size());
    }

    @Test
    public void deleteUser_should_decrease_the_size_of_list_by_one(){
        User user1 = acc_query_i.createUser("Test","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        User user2 = acc_query_i.createUser("Test2","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        User user3 = acc_query_i.createUser("Pearson","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        User user4 = acc_query_i.createUser("Peterson","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        acc_command_i.registerUser(user1);
        acc_command_i.registerUser(user2);
        acc_command_i.registerUser(user3);
        acc_command_i.registerUser(user4);
        int size = acc_query_i.getAllUsers("").size();
        acc_command_i.deleteUser(user1.getAid());

        Assert.assertEquals(size-1 , acc_query_i.getAllUsers("").size());
    }
    @Test
    public void getUserByAid_should_find_initialUser_with_its_aid(){
        initialUser = BoundedUser.Make("Test","Person", BoundedCellPhoneFormat.Make("222","111","4444"), "http://example.com/testperson.png" );
        acc_command_i.registerUser(initialUser);
        User found = acc_query_i.getUserById(initialUser.getAid());
        Assert.assertEquals(initialUser.getAid(), found.getAid());
        Assert.assertEquals(initialUser.getFirstName(), found.getFirstName());
        Assert.assertEquals(initialUser.getLastName(), found.getLastName());
        Assert.assertEquals(initialUser.getPicture(), found.getPicture());
        Assert.assertEquals(initialUser.getCellPhoneFormat().getFirst(), found.getCellPhoneFormat().getFirst());
        Assert.assertEquals(initialUser.getCellPhoneFormat().getMiddle(), found.getCellPhoneFormat().getMiddle());
        Assert.assertEquals(initialUser.getCellPhoneFormat().getLast(), found.getCellPhoneFormat().getLast());
    }
    @Test
    public void getUserByAid_should_return_null_with_gibberish_aid(){
        User found = acc_query_i.getUserById("asdf");
        Assert.assertTrue(found.isNil());
    }
    @Test(expected = Test.None.class)
    public void activateUser_should_execute_its_tasks_with_nullUser(){
       acc_command_i.activateUser("asdf");
    }
}
