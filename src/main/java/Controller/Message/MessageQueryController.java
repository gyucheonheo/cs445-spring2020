package Controller.Message;

import Boundary.Message.MessageInteractorQueryBoundary;
import Boundary.Trip.TripInteractorQueryBoundary;
import Entity.Boundary.Message.Message;
import Entity.Boundary.Trip.Trip;
import Interactor.Message.MessageInteractorQuery;
import Interactor.Trip.TripInteractorQuery;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("rides")
public class MessageQueryController {

    private Gson gson = new Gson();
    private MessageInteractorQueryBoundary mi = MessageInteractorQuery.INSTANCE;

    @Path("{rid}/messages")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewAllRideMessages(@PathParam("rid") String rid){
        TripInteractorQueryBoundary ti = TripInteractorQuery.INSTANCE;
        Trip t = ti.getTripById(rid);
        if(t.isNil()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        List<Message> messages = mi.getAllMessagesByRid(rid);
        JsonArray jsonArray = new JsonArray();
        for(Message m : messages){
            JsonObject message = new JsonObject();
            message.addProperty("mid", m.getMid());
            message.addProperty("sent_by_aid", m.getSentByAid());
            message.addProperty("date", m.getDate());
            message.addProperty("body", m.getBody());
            jsonArray.add(message);
        }
        return Response.status(Response.Status.OK).entity(gson.toJson(jsonArray)).build();
    }
}

