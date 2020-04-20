package Entity.Bounded.Account.User;

import Entity.Boundary.Account.CellPhoneFormat.CellPhoneFormat;
import Entity.Boundary.Account.User.User;
import Entity.Boundary.Trip.DateTimeFormat.DateTimeFormat;
import Entity.Bounded.Trip.DateTimeFormat.BoundedDateTimeFormat;
import Lib.UniqueId;


public class BoundedUser implements User {
    private String id;
    private DateTimeFormat whenCreated;
    private String firstName;
    private String lastName;
    private CellPhoneFormat cellphone;
    private String picture;

    private boolean isActive;

    public static User Make(){
        return new NullBoundedUser();
    }
        protected BoundedUser(){

        }
    public static User Make(String first, String last, CellPhoneFormat cellPhone, String picture){
        return new BoundedUser(first, last, cellPhone, picture);
    }
        private BoundedUser(String firstName, String lastName, CellPhoneFormat cellPhone, String picture){
            this.id = UniqueId.getUniqueID();
            this.whenCreated = BoundedDateTimeFormat.MakeNow();
            setFirst(firstName);
            setLast(lastName);
            this.cellphone = cellPhone;
            this.picture = picture;
            this.isActive = false;
        }

            private void setFirst(String firstName){
                this.firstName = firstName;
            }
            private void setLast(String lastName){
                this.lastName = lastName;
            }


    public String getAid() { return this.id; }

    public boolean getIsActive(){
        return this.isActive;
    }

    public void setIsActive(boolean b) {
        this.isActive = b;
    }

    public void setFirstName(String first) { this.firstName = first;}

    public void setLastName(String last) { this.lastName = last;}

    public void setCellPhoneFormat(CellPhoneFormat cellPhone) { this.cellphone = cellPhone; }

    public void setPicture(String picture) { this.picture = picture; }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPicture() { return this.picture;}

    public String getWhenCreated() {
        return this.whenCreated.getDate()+", " + this.whenCreated.getTime();
    }

    public CellPhoneFormat getCellPhoneFormat() {
        return this.cellphone;
    }

    public boolean isNil() { return false; }

}
