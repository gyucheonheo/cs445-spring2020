package Controller.Message;

import Boundary.Message.MessageInteractorCommandBoundary;
import Boundary.Message.MessageInteractorQueryBoundary;
import Boundary.Trip.TripInteractorQueryBoundary;
import Controller.ErrorHandler.ErrorMessage;
import Controller.Message.Validator.MessageValidator;
import Entity.Boundary.Message.Message;
import Entity.Boundary.Trip.Trip;
import Interactor.Message.MessageInteractorCommand;
import Interactor.Message.MessageInteractorQuery;
import Interactor.Trip.TripInteractorQuery;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("rides")
public class MessageCommandController {
    private Gson gson = new Gson();
    private MessageInteractorQueryBoundary msg_query_i = MessageInteractorQuery.INSTANCE;
    private MessageInteractorCommandBoundary msg_command_i = MessageInteractorCommand.INSTANCE;
    private ErrorMessage error = new ErrorMessage();

    @Path("{rid}/messages")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMessageToRide(@PathParam("rid") String rid, String json){
        TripInteractorQueryBoundary ti = TripInteractorQuery.INSTANCE;
        Trip t = ti.getTripById(rid);
        if(t.isNil()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        if(!MessageValidator.isValid(jsonObject)){
            JsonObject err = error.createError(MessageValidator.getErrorMessage(), 400, "/rides/"+rid+"/messages");
            return Response.status(Response.Status.BAD_REQUEST).entity(err).build();
        }

        String aid = jsonObject.get("aid").getAsString();
        String msg = jsonObject.get("msg").getAsString();
        Message m = msg_query_i.createMessage(aid, msg);
        msg_command_i.sendMsgToRide(rid, m);
        JsonObject result = new JsonObject();
        result.addProperty("mid", m.getMid());
        return Response.status(Response.Status.CREATED).entity(gson.toJson(result)).header("Location","Location").build();
    }

}
