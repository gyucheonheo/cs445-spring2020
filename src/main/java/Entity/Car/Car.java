package Entity.Car;

import Lib.UniqueId;

public class Car {
    private String cid;
    private String model;
    private String color;
    private String plateNumber;
    private String state;
    public Car(){
        this.cid = UniqueId.getUniqueID();
    }
    public boolean isNil(){
        return false;
    }
}
