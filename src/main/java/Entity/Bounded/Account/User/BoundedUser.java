package Entity.Bounded.Account.User;

import Entity.Boundary.Account.CellPhoneFormat.CellPhoneFormat;
import Entity.Boundary.Account.User.RideInformation.RideInformation;
import Entity.Boundary.Account.User.User;
import Entity.Bounded.Account.User.RideInformation.BoundedRideInformation;
import Lib.UniqueId;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BoundedUser implements User {
    private String id;
    private String whenCreated;
    private String firstName;
    private String lastName;
    private CellPhoneFormat cellphone;
    private String picture;
    private RideInformation asDriver;
    private RideInformation asRider;
    private boolean isActive;

    public static User Make(String first, String last, CellPhoneFormat cellPhone, String picture){
        return new BoundedUser(first, last, cellPhone, picture);
    }
        private BoundedUser(String firstName, String lastName, CellPhoneFormat cellPhone, String picture){
            this.id = UniqueId.getUniqueID();
            this.whenCreated = new SimpleDateFormat("dd-mm-yyyy, hh:mm:ss").format(new Date());
            setFirst(firstName);
            setLast(lastName);
            this.cellphone = cellPhone;
            this.picture = picture;
            this.asDriver = BoundedRideInformation.Make();
            this.asRider =  BoundedRideInformation.Make();
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


    public String getAid() { return this.id; }

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
