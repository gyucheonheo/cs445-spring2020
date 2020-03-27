package Entity.Boundary.Trip.LocationInformation.Location;

public interface Location {
    String getCity();
    String getZip();
    boolean isNil();

    class CityNotAllowedEmptyException extends RuntimeException{}

    class CityNotAllowedSpecialCharactersException extends RuntimeException{}
}
