package EntityTest.AccountTest.UserTest.RideInformationTest.RateTest;

import Entity.Boundary.Account.User.RideInformation.Rate.Rate;
import Entity.Bounded.Account.User.RideInformation.Rate.BoundedRate;
import junit.framework.Assert;
import org.junit.Test;

public class RateTest {

    @Test(expected = Rate.SentByNotAllowedEmptyException.class)
    public void Make_without_sentBy_should_throw_sentByNotAllowedEmptyException() {
        BoundedRate.Make("", "Jake", 5, "Good");
    }

    @Test(expected = Rate.SentByAllowOnlyNumberException.class)
    public void Make_with_ChractersentBy_should_throw_SentByAllowOnlyNumberException() {
        BoundedRate.Make("asdf", "Jake", 5, "Good");
    }

    @Test(expected = Rate.FirstNameByNotAllowedEmptyException.class)
    public void Make_without_firstName_should_throw_FirstNameByNotAllowedEmptyException() {
        BoundedRate.Make("5", "", 5, "Good");
    }

    @Test(expected = Rate.LessThanOneRatingNotAllowedException.class)
    public void Make_with_Zero_should_throw_LessThanOneRatingNotAllowedException() {
        BoundedRate.Make("5", "Jake", 0, "Good");
    }

    @Test(expected = Rate.LessThanOneRatingNotAllowedException.class)
    public void Make_with_Negative_should_throw_LessThanOneRatingNotAllowedException() {
        BoundedRate.Make("5", "Jake", -1, "Good");
    }

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

    @Test(expected = Rate.GreaterThanFiveRatingNotAllowedException.class)
    public void Make_with_rating_on_boudnary_plus_one_should_throw_GreaterThanFiveRatingNotAllowedException() {
        BoundedRate.Make("5", "Jake", 6, "Good");
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

    @Test(expected = Rate.CommentNotAllowedEmptyException.class)
    public void Make_without_comment_should_throw_CommentNotAllowedEmptyException() {
        BoundedRate.Make("5", "Jake", 4, "");
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