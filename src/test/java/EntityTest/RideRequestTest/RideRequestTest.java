package EntityTest.RideRequestTest;

import Boundary.Account.User.AccountInteractorBoundary;
import Entity.Boundary.Account.User.User;
import Entity.Boundary.RideRequest.RideRequest;
import Entity.Bounded.Account.CellPhoneFormat.BoundedCellPhoneFormat;
import Entity.Bounded.Account.User.BoundedUser;
import Entity.Bounded.RideRequest.BoundedRideRequest;
import Interactor.AccountInteractor;
import junit.framework.Assert;import org.junit.Before;

import org.junit.Test;

public class RideRequestTest {
    private User unauthorizedUser;
    private User authorizedUser;

    @Before
    public void setUp(){
        unauthorizedUser = BoundedUser.Make("unauthroized","test", BoundedCellPhoneFormat.Make("111","222","3333"), "google.com");
        authorizedUser = BoundedUser.Make("authorized","test", BoundedCellPhoneFormat.Make("111","222","3333"), "google.com");

        AccountInteractorBoundary ai = AccountInteractor.INSTANCE;
        ai.registerUser(unauthorizedUser);ai.registerUser(authorizedUser);
        ai.activateUser(authorizedUser.getAid());
    }

    @Test
    public void Make_initially_set_isRideConfirmed_to_null(){
        RideRequest rr = BoundedRideRequest.Make(authorizedUser.getAid(), 2);
        Assert.assertNull(rr.getIsRideConfirmed());
    }
    @Test
    public void Make_initially_set_isPickUpConfirmed_to_null(){
        RideRequest rr = BoundedRideRequest.Make(authorizedUser.getAid(), 2);
        Assert.assertNull(rr.getIsPickUpConfirmed());
    }
}
