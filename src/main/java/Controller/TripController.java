package Controller;

import Boundary.TripInteractorBoundary;

import Entity.Boundary.Trip.Trip;
import Interactor.TripInteractor;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/rides")
public class TripController {
    private TripInteractorBoundary tripI = TripInteractor.INSTANCE;
    private Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRides(){
        List<Trip> trips = tripI.getAllTrips();
        String s = gson.toJson(trips);
        return Response.status(Response.Status.OK).entity(s).build();
    }
}
