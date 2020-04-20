package Entity.Bounded.Trip.Car;

import Entity.Boundary.Trip.Car.Car;
import Entity.Boundary.Trip.Car.Vehicle.Vehicle;

public class BoundedCar implements Car {
    private Vehicle vehicleInformation;
    private String plateState;
    private String plateSerial;

    public static Car Make(Vehicle vehicleInformation, String plateState, String plateSerial){
        return new BoundedCar(vehicleInformation, plateState, plateSerial);
    }
        private BoundedCar(Vehicle vehicleInformation, String plateState, String plateSerial){
            this.vehicleInformation = vehicleInformation;
            setPlateState(plateState);
            setPlateSerial(plateSerial);
        }

            private void setPlateState(String plateState){
                this.plateState = plateState.toUpperCase();
            }
            private void setPlateSerial(String plateSerial){
                this.plateSerial = plateSerial;
            }

    public boolean isNil(){
        return false;
    }

    public Vehicle getVehicleInformation() { return this.vehicleInformation; }
    public String getPlateState(){
        return this.plateState;
    }
    public String getPlateSerial(){
        return this.plateSerial;
    }
}
