package Entity.Bounded.Trip.Car;

import Entity.Boundary.Trip.Car.Car;
import Entity.Boundary.Trip.Car.Vehicle.Vehicle;

public class NullBoundedCar implements Car {
    @Override
    public Vehicle getVehicleInformation() {
        return null;
    }

    @Override
    public String getPlateState() {
        return null;
    }

    @Override
    public String getPlateSerial() {
        return null;
    }

    public boolean isNil(){
        return true;
    }
}
