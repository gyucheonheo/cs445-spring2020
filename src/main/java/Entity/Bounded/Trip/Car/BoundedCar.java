package Entity.Bounded.Trip.Car;

import Entity.Boundary.Trip.Car.Car;
import Entity.Boundary.Trip.Car.Vehicle.Vehicle;
import Entity.Bounded.Trip.Car.Vehicle.BoundedVehicle;

public class BoundedCar implements Car {
    private Vehicle vehicleInformation;
    private String plateState;
    private String plateSerial;

    public static Car Make(){
        return new NullBoundedCar();
    }

    public static Car Make(Vehicle vehicleInformation, String plateState, String plateSerial){
        return new BoundedCar(vehicleInformation, plateState, plateSerial);
    }
        private BoundedCar(Vehicle vehicleInformation, String plateState, String plateSerial){
            this.vehicleInformation = vehicleInformation;
            setPlateState(plateState);
            setPlateSerial(plateSerial);
        }

            private void setPlateState(String plateState){
                if(plateState.length() != 2){
                    throw new PlateStateNotAllowedToExceedTwoCharacter();
                }
                if(!plateState.matches("[a-zA-Z]{2}")){
                    throw new PlateStateAllowedOnlyTwoCharacter();
                }

                this.plateState = plateState.toUpperCase();
            }
            private void setPlateSerial(String plateSerial){
                if(plateSerial.isEmpty()){
                    throw new PlateSerialNotAllowedEmpty();
                }
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
