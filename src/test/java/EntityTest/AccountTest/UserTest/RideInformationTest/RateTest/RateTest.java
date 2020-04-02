package EntityTest.AccountTest.UserTest.RideInformationTest.RateTest;

import Entity.Boundary.Account.User.RideInformation.Rate.Rate;
import Entity.Bounded.Account.User.RideInformation.Rate.BoundedRate;
import junit.framework.Assert;
import org.junit.Test;

public class RateTest {

    @Test
    public void Make_with_rating_on_minimum_boundary_should_be_one() {
        Rate r = BoundedRate.Make("5", "Jake", 1, "Good");
        Assert.assertEquals(1, r.getRating());
    }

    @Test
    public void Make_with_rating_on_minimum_boundary_pluas_one_should_be_two() {
        Rate r = BoundedRate.Make("5", "Jake", 2, "Good");
        Assert.assertEquals(2, r.getRating());
    }


    @Test
    public void Make_with_rating_on_maximum_boudnary_should_be_five() {
        Rate r = BoundedRate.Make("5", "Jake", 5, "Good");
        Assert.assertEquals(5, r.getRating());
    }

    @Test
    public void Make_with_rating_on_maximum_boundary_minus_one_should_be_four() {
        Rate r = BoundedRate.Make("5", "Jake", 4, "Good");
        Assert.assertEquals(4, r.getRating());
    }

    @Test
    public void AfterMakeOne_its_rate_should_be_equal_to_that_provided() {
        Rate r = BoundedRate.Make("5", "Jake", 4, "Hello");
        Assert.assertEquals(4, r.getRating());
    }

    @Test
    public void AfterMakeOne_its_sentBy_should_be_equal_to_that_provided() {
        Rate r = BoundedRate.Make("5", "Jake", 4, "Hello");
        Assert.assertEquals("5", r.getSentBy());
    }

    @Test
    public void AfterMakeOne_its_firstName_should_be_equal_to_that_provided() {
        Rate r = BoundedRate.Make("5", "Jake", 4, "Hello");
        Assert.assertEquals("Jake", r.getFirstName());
    }

    @Test
    public void AfterMakeOne_its_comment_should_be_equal_to_that_provided() {
        Rate r = BoundedRate.Make("5", "Jake", 4, "Hello");
        Assert.assertEquals("Hello", r.getComment());
    }
}