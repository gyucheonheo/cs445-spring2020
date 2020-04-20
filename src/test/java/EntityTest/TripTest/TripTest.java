package EntityTest.TripTest;

import Entity.Boundary.Trip.Car.Car;
import Entity.Boundary.Trip.DateTimeFormat.DateTimeFormat;
import Entity.Boundary.Trip.LocationInformation.LocationInformation;
import Entity.Boundary.Trip.Rules.Rules;
import Entity.Bounded.Trip.Car.BoundedCar;
import Entity.Bounded.Trip.Car.Vehicle.BoundedVehicle;
import Entity.Bounded.Trip.DateTimeFormat.BoundedDateTimeFormat;
import Entity.Bounded.Trip.LocationInformation.Location.BoundedLocation;
import Entity.Bounded.Trip.LocationInformation.BoundedLocationInformation;
import Entity.Bounded.Trip.Rules.BoundedRules;
import Entity.Bounded.Trip.BoundedTrip;
import Entity.Boundary.Trip.Trip;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TripTest {

    private Trip testTrip;
    @Before
    public void setUp() throws ParseException {
        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make ("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo = BoundedRules.Make(2, 5, "");
        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime("05-May-2015, 09:20");

        testTrip = BoundedTrip.Make("aid", locationInfo, carInfo, dt, passengerInfo);
    }
    @Test
    public void NormalTrip_getNil_must_return_false(){
        Assert.assertFalse(testTrip.isNil());
    }

    @Test
    public void NullTrip_getNil_must_return_true(){
        Trip nullTrip = BoundedTrip.Make();
        Assert.assertTrue(nullTrip.isNil());
    }
}
