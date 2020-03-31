package EntityTest.MessageTest;

import Entity.Boundary.Account.User.User;
import Entity.Boundary.Message.Message;
import Entity.Bounded.Account.CellPhoneFormat.BoundedCellPhoneFormat;
import Entity.Bounded.Account.User.BoundedUser;
import Entity.Bounded.Message.BoundedMessage;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;


public class MessageTest {
    private Message m;
    @Before
    public void setUp(){
       m = BoundedMessage.Make("1", "I am here");
    }
    @Test(expected= Message.SentByNotAllowedEmptyException.class)
    public void emptySentByNotAllowed_it_should_throw_SentByNotAllowedEmptyException(){
       BoundedMessage.Make("", "I am here");
    }
    @Test(expected= Message.BodyNotAllowedEmptyException.class)
    public void emptybodySentByNotAllowed_it_should_throw_BodyNotAllowedEmptyException(){
        BoundedMessage.Make("123", "");
    }
    @Test
    public void NormalMessage_isNil_must_return_false(){
        Assert.assertFalse(m.isNil());
    }
    @Test
    public void NullMessage_isNil_must_return_true(){
        Message nullMessage = BoundedMessage.Make();
        Assert.assertTrue(nullMessage.isNil());
    }
}

