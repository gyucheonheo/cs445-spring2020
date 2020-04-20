package Controller.Trip.Parser;

import Entity.Boundary.Trip.LocationInformation.Location.Location;
import Entity.Boundary.Trip.LocationInformation.LocationInformation;
import Entity.Bounded.Trip.LocationInformation.BoundedLocationInformation;
import Entity.Bounded.Trip.LocationInformation.Location.BoundedLocation;
import com.google.gson.JsonObject;

public class LocationInfoParser {
    public static LocationInformation parse(JsonObject j){
       String from_city = j.get("from_city").getAsString();
       String from_zip = j.get("from_zip").getAsString();
       String to_city = j.get("to_city").getAsString();
       String to_zip = j.get("to_zip").getAsString();
       Location startingPoint = BoundedLocation.Make(from_city, from_zip);
       Location endingPoint = BoundedLocation.Make(to_city, to_zip);
       return BoundedLocationInformation.Make(startingPoint, endingPoint);
    }
}
