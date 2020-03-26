package EntityTest.TripTest.LocationInformationTest;

import Entity.Boundary.Trip.LocationInformation.LocationInformation;
import Entity.Bounded.Trip.LocationInformation.BoundedLocationInformation;
import junit.framework.Assert;
import org.junit.Test;

public class LocationInformationTest {
    @Test
    public void NullLocationInformation_isNil_must_be_true(){
        LocationInformation nullLInfo = BoundedLocationInformation.Make();
        Assert.assertTrue(nullLInfo.isNil());
    }
}
