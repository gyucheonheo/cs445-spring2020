package EntityTest.TripTest.LocationInformationTest.LocationTest;

import Entity.Boundary.Trip.LocationInformation.Location.Location;
import Entity.Bounded.Trip.LocationInformation.Location.BoundedLocation;
import junit.framework.Assert;
import org.junit.Test;

public class LocationTest {
    @Test(expected= Location.CityNotAllowedEmptyException.class)
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
    public void leadingSpacesInCity_must_get_trimmed(){
        Location location = BoundedLocation.Make("    Chicago", "60616");
        Assert.assertEquals("Chicago", location.getCity());
    }
    @Test
    public void endingSpacesInCity_must_get_trimmed(){
        Location location = BoundedLocation.Make("Chicago ", "60616");
        Assert.assertEquals("Chicago", location.getCity());
    }
    @Test
    public void bothSpacesInCity_must_get_trimmed(){
        Location location = BoundedLocation.Make("   Chicago ", "60616");
        Assert.assertEquals("Chicago", location.getCity());
    }
    @Test
    public void cityMustBeCombinationOfCharactersAndSpaces(){
        Location location = BoundedLocation.Make("San Fransisco", "");
        Assert.assertEquals("San Fransisco", location.getCity());
    }
    @Test
    public void cityMustBeCombinationOfCharactersAndSpaces2(){
        Location location = BoundedLocation.Make("San Fransisco   ", "");
        Assert.assertEquals("San Fransisco", location.getCity());
    }

    @Test(expected=Location.CityNotAllowedSpecialCharactersException.class)
    public void if_city_contains_special_characters_it_throws_CityNotAllowedSpecialChracterException2(){
        BoundedLocation.Make("San Fra nsisco", "");
    }

    @Test(expected=Location.CityNotAllowedSpecialCharactersException.class)
    public void if_city_contains_special_characters_it_throws_CityNotAllowedSpecialChracterException(){
       BoundedLocation.Make("San@ Fransisco", "");
    }
    @Test(expected=Location.CityNotAllowedSpecialCharactersException.class)
    public void if_city_contains_many_special_characters_it_throws_CityNotAllowedSpecialChracterException(){
        BoundedLocation.Make("%1252 Frans0isco", "");
    }
    @Test(expected=Location.CityNotAllowedSpecialCharactersException.class)
    public void if_city_contains_all_spaces_it_throws_CityNotAllowedSpecialCharactersException(){
        BoundedLocation.Make("     ", "");
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
