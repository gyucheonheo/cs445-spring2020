package Entity.Boundary.Trip.Car.Vehicle;

public interface Vehicle {
    String getMake();
    String getModel();
    String getColor();

    class MakeNotAllowedEmpty extends RuntimeException {
    }

    class ModelNotAllowedEmpty extends RuntimeException {
    }

    class ColorNotAllowedEmpty extends RuntimeException {
    }
}
