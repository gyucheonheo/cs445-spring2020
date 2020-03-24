package Entity.Trip.LocationInformation.Location;

public class Location {
    private String city;
    private String zip;

    public Location(){

    }
    public Location(String city, String zip){
        setCity(city);
        setZip(zip);
    }
        private void setCity(String city){
            if(city.isEmpty()){
                throw new CityNotAllowedEmpty();
            }
            this.city = city;
        }
        public class CityNotAllowedEmpty extends RuntimeException{}
        private void setZip(String zip){
            this.zip = zip;
        }
    public boolean isNil(){
        return false;
    }
}
