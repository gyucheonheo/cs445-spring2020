package Entity.Trip.Car.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class Vehicle {
    private final Map<String, String> VALUES_BY_NAME;

    public Vehicle(String make, String model, String color) {
        VALUES_BY_NAME = new HashMap<>();
        setElement("make", make, new MakeNotAllowedEmpty());
        setElement("model", model, new ModelNotAllowedEmpty());
        setElement("color", color, new ColorNotAllowedEmpty());
    }
        private void setElement(String key, String value, RuntimeException e){
            if(value.isEmpty()){
                throw e;
            }
            VALUES_BY_NAME.put(key, value);
        }

    public static class MakeNotAllowedEmpty extends RuntimeException {
    }

    public static class ModelNotAllowedEmpty extends RuntimeException {
    }

    public static class ColorNotAllowedEmpty extends RuntimeException {
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

