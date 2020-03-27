package EntityTest.TripTest.LocationInformationTest;

import Entity.Boundary.Trip.LocationInformation.LocationInformation;
import Entity.Bounded.Trip.LocationInformation.BoundedLocationInformation;
import Entity.Bounded.Trip.LocationInformation.Location.BoundedLocation;
import junit.framework.Assert;
import org.junit.Test;

public class LocationInformationTest {
    @Test(expected=LocationInformation.PassingNullLocationNotAllowedException.class)
    public void NullStartingPointLocation_throws_PassingNullLocationNotAllowedException(){
        BoundedLocationInformation.Make(BoundedLocation.Make(), BoundedLocation.Make("Chicago", "60616"));
    }
    @Test(expected=LocationInformation.PassingNullLocationNotAllowedException.class)
    public void NullEndingPointLocation_throws_PassingNullLocationNotAllowedException(){
        BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make());
    }
    @Test(expected=LocationInformation.PassingNullLocationNotAllowedException.class)
    public void NullStartingAndEndingPointLocations_throw_PassingNullLocationNotAllowedException(){
        BoundedLocationInformation.Make(BoundedLocation.Make(), BoundedLocation.Make());
    }
    @Test
    public void NullLocationInformation_isNil_must_be_true(){
        LocationInformation nullLInfo = BoundedLocationInformation.Make();
        Assert.assertTrue(nullLInfo.isNil());
    }
}
