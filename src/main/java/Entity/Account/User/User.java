package Entity.Account.User;

import Entity.Account.CellPhoneFormat.CellPhoneFormat;
import Entity.Account.User.RideInformation.RideInformation;
import Lib.UniqueId;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private String id;
    private String whenCreated;
    private String firstName;
    private String lastName;
    private CellPhoneFormat cellphone;
    private String picture;
    private RideInformation asDriver;
    private RideInformation asRider;
    private boolean isActive;

    public User(String firstName, String lastName, CellPhoneFormat cellPhone, String picture){
        this.id = UniqueId.getUniqueID();
        this.whenCreated = new SimpleDateFormat("dd-mm-yyyy, hh:mm:ss").format(new Date());
        setFirst(firstName);
        setLast(lastName);
        this.cellphone = cellPhone;
        this.picture = picture;
        this.asDriver = new RideInformation();
        this.asRider = new RideInformation();
        this.isActive = false;
    }

        private void setFirst(String firstName){
            if(firstName.isEmpty()){
                throw new FirstNameBeingEmptyIsInvalid();
            }
            this.firstName = firstName;
        }
        private void setLast(String lastName){
            if(lastName.isEmpty()){
                throw new LastNameBeingEmptyIsInvalid();
            }
            this.lastName = lastName;
        }

        public class FirstNameBeingEmptyIsInvalid extends RuntimeException { }
        public class LastNameBeingEmptyIsInvalid extends RuntimeException { }

    public boolean getIsActive(){
        return this.isActive;
    }

    public RideInformation getAsDriver(){
        return this.asDriver;
    }

    public RideInformation getAsRider(){
        return this.asRider;
    }
}
