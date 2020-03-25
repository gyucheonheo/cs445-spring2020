package Entity.Trip.Car;

import Entity.Trip.Car.Vehicle.Vehicle;

public class Car {
    private Vehicle vehicleInformation;
    private String plateState;
    private String plateSerial;
    public Car(){

    }
    public Car(Vehicle vehicleInformation, String plateState, String plateSerial){
        this.vehicleInformation = vehicleInformation;
        setPlateState(plateState);
        setPlateSerial(plateSerial);
    }

        private void setPlateState(String plateState){
            if(plateState.length() != 2){
                throw new PlateStateNotAllowedToExceedTwoCharacter();
            }
            this.plateState = plateState;
        }
        private void setPlateSerial(String plateSerial){
            if(plateSerial.isEmpty()){
                throw new PlateSerialNotAllowedEmpty();
            }
            this.plateSerial = plateSerial;
        }

        public static class PlateSerialNotAllowedEmpty extends RuntimeException{}
        public static class PlateStateNotAllowedToExceedTwoCharacter extends RuntimeException{}

    public boolean isNil(){
        return false;
    }

    public String getPlateState(){
        return this.plateState;
    }
    public String getPlateSerial(){
        return this.plateSerial;
    }
}
