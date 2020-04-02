package Entity.Bounded.Trip.Car.Vehicle;

import Entity.Boundary.Trip.Car.Vehicle.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class BoundedVehicle implements Vehicle {
    private final Map<String, String> VALUES_BY_NAME;

    public static Vehicle Make(String make, String model, String color){
        return new BoundedVehicle(make, model, color);
    }
        private BoundedVehicle(String make, String model, String color) {
            VALUES_BY_NAME = new HashMap<>();
            setElement("make", make);
            setElement("model", model);
            setElement("color", color);
        }
            private void setElement(String key, String value){
                VALUES_BY_NAME.put(key, value);
            }

    public String getMake() {
        return VALUES_BY_NAME.get("make");
    }

    public String getModel() {
        return VALUES_BY_NAME.get("model");
    }

    public String getColor() {
        return VALUES_BY_NAME.get("color");
    }

}

