package Entity.Bounded.Trip.LocationInformation.Location;

import Entity.Boundary.Trip.LocationInformation.Location.Location;

public class BoundedLocation implements Location {
    private String city;
    private String zip;

    public static Location Make(String city, String zip){
        return new BoundedLocation(city, zip);
    }
        private BoundedLocation(String city, String zip){
            setCity(city);
            setZip(zip);
        }
            private void setCity(String city){
                this.city = city.trim();
            }
            private void setZip(String zip){
                this.zip = zip;
            }
    public String getCity(){
        return this.city;
    }
    public String getZip(){
        return this.zip;
    }
    public boolean isNil(){
        return false;
    }
}
