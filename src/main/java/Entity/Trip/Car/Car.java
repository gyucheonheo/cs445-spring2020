package Entity.Trip.Car;


public class Car {
    private String make;
    private String model;
    private String color;
    private String plateState;
    private String plateSerial;
    public Car(){

    }
    public Car(String make, String model, String color, String plateState, String plateSerial){
        setMake(make);
        setModel(model);
        setColor(color);
        setPlateState(plateState);
        setPlateSerial(plateSerial);
    }
        private void setMake(String make){
            if(make.isEmpty()){
                throw new MakeNotAllowedEmpty();
            }
            this.make = make;
        }
        private void setModel(String model){
            if(model.isEmpty()){
                throw new ModelNotAllowedEmpty();
            }
            this.model = model;
        }
        private void setColor(String color){
            if(color.isEmpty()){
                throw new ColorNotAllowedEmpty();
            }
            this.color = color;
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
        public static class MakeNotAllowedEmpty extends RuntimeException{}
        public static class ModelNotAllowedEmpty extends RuntimeException{}
        public static class ColorNotAllowedEmpty extends RuntimeException{}
        public static class PlateSerialNotAllowedEmpty extends RuntimeException{}
        public static class PlateStateNotAllowedToExceedTwoCharacter extends RuntimeException{}

    public boolean isNil(){
        return false;
    }
    public String getMake(){
        return this.make;
    }
    public String getModel(){
        return this.model;
    }
    public String getColor(){
        return this.color;
    }
    public String getPlateState(){
        return this.plateState;
    }
    public String getPlateSerial(){
        return this.plateSerial;
    }
}
