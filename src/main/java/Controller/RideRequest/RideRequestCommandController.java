package Controller.RideRequest;

import Boundary.RideRequest.RideRequestInteractorQueryBoundary;
import Controller.ErrorHandler.ErrorMessage;
import Controller.RideRequest.Parser.RideRequestParser;
import Controller.RideRequest.Validator.RequestValidator;
import Entity.Boundary.RideRequest.RideRequest;
import Interactor.RideRequest.RideRequestInteractorQuery;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rides")
public class RideRequestCommandController {
    private RideRequestInteractorQueryBoundary req_query_i = RideRequestInteractorQuery.INSTANCE;
    private Gson gson = new Gson();
    private ErrorMessage error = new ErrorMessage();

    @Path("{rid}/join_requests")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response requestToJoin(@PathParam("rid") String rid, String json){
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

        if(!RequestValidator.isValid(jsonObject)){
            JsonObject err = error.createError(RequestValidator.getErrorMessage(), 400, "/rides/"+rid+"/join_requests");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(err)).build();
        }
        RideRequest r = RideRequestParser.parse(jsonObject, rid);

        String jid = req_query_i.requestRide(r);
        JsonObject result = new JsonObject();
        result.addProperty("jid", jid);
        return Response.status(Response.Status.CREATED).entity(gson.toJson(result)).header("Location", "Location").build();
    }

    @Path("{rid}/join_requests/{jid}")
    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    public Response confirmOrDenyOrPickUpConfirmRequest(@PathParam("rid") String rid, @PathParam("jid") String jid, String json){
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        RideRequest r = req_query_i.getRequestByJid(jid);

        /* TODO : REFACTOR (Don't ask tell) */
        if (jsonObject.has("ride_confirmed")) {
            if(jsonObject.get("ride_confirmed").isJsonNull()){
                JsonObject err = error.createError("Invalid ride_confirmed", 400, "/rides/"+rid+"/join_requests/"+jid);
                return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(err)).build();
            }
            r.setIsRideConfirmed(jsonObject.get("ride_confirmed").getAsBoolean());
        }
        if(jsonObject.has("pickup_confirmed")) {
            if(jsonObject.get("pickup_confirmed").isJsonNull() ||
                    !jsonObject.get("pickup_confirmed").getAsBoolean()) {
                JsonObject err = error.createError("Invalid pickup_confirmed", 400, "/rides/"+rid+"/join_requests/"+jid);
                return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(err)).build();
            }
            r.setIsPickUpConfirmed(jsonObject.get("pickup_confirmed").getAsBoolean());
        }
        return Response.status(Response.Status.OK).build();
    }
}
