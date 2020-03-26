package Entity.Boundary.Trip.Car;

import Entity.Boundary.Trip.Car.Vehicle.Vehicle;

public interface Car {
    Vehicle getVehicleInformation();
    String getPlateState();
    String getPlateSerial();

    boolean isNil();

    class PlateSerialNotAllowedEmpty extends RuntimeException{}
    class PlateStateNotAllowedToExceedTwoCharacter extends RuntimeException{}
}
