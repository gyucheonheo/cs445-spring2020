import Entity.Trip.LocationInformation.Location.Location;
import org.junit.Test;

public class LocationTest {
    @Test(expected=Location.CityNotAllowedEmpty.class)
    public void if_city_is_empty_it_throws_CityNotAllowedEmptyException(){
       Location location = new Location("", "60616");
    }
}
