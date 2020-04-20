package EntityTest.AccountTest.UserTest.RideInformationTest.RateTest;

import Entity.Boundary.Account.User.RideInformation.Rate.Rate;
import Entity.Bounded.Account.User.RideInformation.Rate.BoundedRate;
import junit.framework.Assert;
import org.junit.Test;

public class RateTest {

    @Test
    public void Make_with_rating_on_minimum_boundary_should_be_one() {
        Rate r = BoundedRate.Make("1", "05-Apr-2020","5", 1, "Good");
        Assert.assertEquals(1, r.getRating());
    }

    @Test
    public void Make_with_rating_on_minimum_boundary_pluas_one_should_be_two() {
        Rate r = BoundedRate.Make("1", "05-Apr-2020","5", 2, "Good");
        Assert.assertEquals(2, r.getRating());
    }


    @Test
    public void Make_with_rating_on_maximum_boudnary_should_be_five() {
        Rate r = BoundedRate.Make("1", "05-Apr-2020","5",5, "Good");
        Assert.assertEquals(5, r.getRating());
    }

    @Test
    public void Make_with_rating_on_maximum_boundary_minus_one_should_be_four() {
        Rate r = BoundedRate.Make("1", "05-Apr-2020","5", 4, "Good");
        Assert.assertEquals(4, r.getRating());
    }

    @Test
    public void AfterMakeOne_its_rate_should_be_equal_to_that_provided() {
        Rate r = BoundedRate.Make("1", "05-Apr-2020","5",  4, "Hello");
        Assert.assertEquals(4, r.getRating());
    }

    @Test
    public void AfterMakeOne_its_sentBy_should_be_equal_to_that_provided() {
        Rate r = BoundedRate.Make("1","05-Apr-2020", "5", 4, "Hello");
        Assert.assertEquals("5", r.getSentBy());
    }


    @Test
    public void AfterMakeOne_its_comment_should_be_equal_to_that_provided() {
        Rate r = BoundedRate.Make("1", "05-Apr-2020","5", 4, "Hello");
        Assert.assertEquals("Hello", r.getComment());
    }

    @Test
    public void NullRate_isNil_should_return_true(){
        Rate r = BoundedRate.Make();
        Assert.assertTrue(r.isNil());
    }

    @Test
    public void RegularRate_isNil_should_return_false(){
        Rate r = BoundedRate.Make("1", "05-Apr-2020","5", 4, "Hello");
        Assert.assertFalse(r.isNil());
    }
}