package EntityTest.TripTest.LocationInformationTest.LocationTest;

import Entity.Boundary.Trip.LocationInformation.Location.Location;
import Entity.Bounded.Trip.LocationInformation.Location.BoundedLocation;
import junit.framework.Assert;
import org.junit.Test;

public class LocationTest {
    @Test(expected= Location.CityNotAllowedEmpty.class)
    public void if_city_is_empty_it_throws_CityNotAllowedEmptyException(){
       BoundedLocation.Make("", "60616");
    }
    @Test
    public void NormalLocation_isNil_must_return_false(){
        Location location = BoundedLocation.Make("Chicago", "60616");
        Assert.assertFalse(location.isNil());
    }
    @Test
    public void NullLocation_isNil_must_return_false(){
        Location location = BoundedLocation.Make();
        Assert.assertTrue(location.isNil());
    }
    @Test
    public void getCity_must_return_what_it_is(){
       Location location = BoundedLocation.Make("Chicago", "60616");
       Assert.assertEquals("Chicago", location.getCity());
    }

    @Test
    public void getZip_must_return_what_it_is(){
        Location location = BoundedLocation.Make("Chicago", "60616");
        Assert.assertEquals("60616", location.getZip());
    }
}
