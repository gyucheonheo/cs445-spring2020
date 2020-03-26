package InteractorTest;

import Boundary.Trip.TripInteractorBoundary;
import Entity.Boundary.Trip.Car.Car;
import Entity.Boundary.Trip.LocationInformation.LocationInformation;
import Entity.Boundary.Trip.Rules.Rules;
import Entity.Bounded.Trip.Car.BoundedCar;
import Entity.Bounded.Trip.Car.Vehicle.BoundedVehicle;
import Entity.Bounded.Trip.LocationInformation.Location.BoundedLocation;
import Entity.Bounded.Trip.LocationInformation.BoundedLocationInformation;
import Entity.Bounded.Trip.Rules.BoundedRules;
import Entity.Boundary.Trip.Trip;
import Interactor.TripInteractor;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TripInteractorTest {
    private static TripInteractorBoundary tb = TripInteractor.INSTANCE;
    private static List<String> tids;

    @BeforeClass
    public static void setUp() {
        tb = TripInteractor.INSTANCE;
        List<String> conditions = new ArrayList<>();
        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo = BoundedRules.Make(2, 5, conditions); ;
        tb.createTrip(locationInfo, carInfo, passengerInfo);

        LocationInformation locationInfo1 = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("St Louis",""));
        Car carInfo1 = BoundedCar.Make(BoundedVehicle.Make("Toyota", "HIGHLAND","White"), "NY", "CARDI");
        Rules passengerInfo1 = BoundedRules.Make(4, 15, conditions); ;
        tb.createTrip(locationInfo1, carInfo1, passengerInfo1);

        LocationInformation locationInfo2 = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("New York",""));
        Car carInfo2 = BoundedCar.Make(BoundedVehicle.Make("Toyota", "HIGHLAND","White"), "WI", "WHOTATBE");
        Rules passengerInfo2 = BoundedRules.Make(4, 15, conditions); ;
        tb.createTrip(locationInfo2, carInfo2, passengerInfo2);

        tids = new ArrayList<>();
        for(Map.Entry<String, Trip> entry : tb.getAllTrips().entrySet()){
            tids.add(entry.getKey());
        }

    }

    @Test(expected=TripInteractor.NotFoundByTripIdException.class)
    public void if_getTripById_cannot_find_trip_by_tid_it_throws_NotFoundByTripIdException(){
        tb.getTripById("ajsdkfjadkslf");
    }

    @Test(expected=TripInteractor.NotFoundByTripIdException.class)
    public void if_updateTrip_cannot_find_trip_by_tid_it_throws_NotFoundByTripIdException(){
        LocationInformation locationInfo = BoundedLocationInformation.Make();
        Car carInfo = BoundedCar.Make();
        Rules passengerInfo = BoundedRules.Make();
        tb.updateTrip("$$$", locationInfo, carInfo, passengerInfo);
    }

    @Test
    public void if_updateTrip_succeed_it_should_have_the_updated_information(){
        String tid = tids.get(0);
        List<String> conditions = new ArrayList<>();
        conditions.add("No Smoking.");
        conditions.add("No Vapor.");
        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("San Fransisco",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Honda", "CIVIC","Black"), "WI", "GOWSOX");
        Rules passengerInfo = BoundedRules.Make(2, 5, conditions); ;
        tb.updateTrip(tid, locationInfo, carInfo, passengerInfo);
        Trip updatedTrip = tb.getTripById(tid);
        Assert.assertEquals(tid, updatedTrip.getTid());
        Assert.assertEquals("Chicago", updatedTrip.getLocationInformation().getStartingPoint().getCity());
        Assert.assertEquals("60616", updatedTrip.getLocationInformation().getStartingPoint().getZip());
        Assert.assertEquals("San Fransisco", updatedTrip.getLocationInformation().getEndingPoint().getCity());
        Assert.assertEquals("", updatedTrip.getLocationInformation().getEndingPoint().getZip());

        Assert.assertEquals("Honda", updatedTrip.getCarInformation().getVehicleInformation().getMake());
        Assert.assertEquals("CIVIC", updatedTrip.getCarInformation().getVehicleInformation().getModel());
        Assert.assertEquals("Black", updatedTrip.getCarInformation().getVehicleInformation().getColor());
        Assert.assertEquals("WI", updatedTrip.getCarInformation().getPlateState());
        Assert.assertEquals("GOWSOX", updatedTrip.getCarInformation().getPlateSerial());

        Assert.assertEquals(2,updatedTrip.getRules().getMaxPeople());
        Assert.assertEquals(5.0E00, updatedTrip.getRules().getAmountPerPassenger());

        for(int i = 0; i < conditions.size(); i++){
            Assert.assertEquals(conditions.get(i), updatedTrip.getRules().getConditions().get(i));
        }
    }

    @Test(expected=TripInteractor.NotFoundByTripIdException.class)
    public void if_deleteTrip_cannot_find_trip_by_tid_it_throws_NotFoundByTripIdException(){
       tb.deleteTrip("@@@@");
    }
    @Test(expected=TripInteractor.NotFoundByTripIdException.class)
    public void if_deleteTrip_succeed_and_getPidById_should_throw_NotFOundByTripIdException(){
       String tid = tids.get(0);
       tb.deleteTrip(tid);
       tb.getTripById(tid);
    }
}
