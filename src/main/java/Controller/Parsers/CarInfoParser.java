package Controller.Parsers;

import Entity.Boundary.Trip.Car.Car;
import Entity.Boundary.Trip.Car.Vehicle.Vehicle;
import Entity.Bounded.Trip.Car.BoundedCar;
import Entity.Bounded.Trip.Car.Vehicle.BoundedVehicle;
import com.google.gson.JsonObject;

public class CarInfoParser {
    public static Car parse(JsonObject car_info) {
        String make = car_info.get("make").getAsString();
        String model = car_info.get("model").getAsString();
        String color = car_info.get("color").getAsString();
        String plate_state = car_info.get("plate_state").getAsString();
        String plate_serial = car_info.get("plate_serial").getAsString();

        Vehicle v = BoundedVehicle.Make(make, model, color);
        return BoundedCar.Make(v, plate_state, plate_serial);
    }
}
