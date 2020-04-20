package EntityTest.AccountTest.UserTest.RideInformationTest;

import Entity.Boundary.Account.User.RideInformation.RideInformation;
import Entity.Bounded.Account.User.RideInformation.BoundedRideInformation;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class RideInformationTest {
    private RideInformation rInfo;
    @Before
    public void setUp(){
        rInfo = BoundedRideInformation.Make();
    }

    @Test
    public void NormalRideInformation_isNil_must_return_false(){
       Assert.assertFalse(rInfo.isNil());
    }

    @Test
    public void whenJustCreateUser_Driver_should_not_be_null(){
        Assert.assertNotNull(rInfo);
    }
    @Test
    public void whenJustCreateUser_rides_should_be_zero(){
        Assert.assertEquals(0, rInfo.getRides());
    }
    @Test
    public void whenJustCreateUser_average_ratings_should_be_zero(){
        Assert.assertEquals(0.0, rInfo.getAverageRating(), 0.001);
    }
    @Test
    public void rides_should_not_be_negative(){
        Assert.assertTrue(rInfo.getRides() >= 0);
    }

    @Test
    public void average_ratings_should_not_be_negative(){
        Assert.assertTrue(rInfo.getAverageRating() >=0);
    }
    @Test
    public void ratings_should_be_equal_to_size_of_rate(){
        Assert.assertEquals(rInfo.getRatings(), rInfo.getRates().size());
    }

}
