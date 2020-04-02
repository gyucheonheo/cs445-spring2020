package Controller;

import Boundary.AccountInteractorBoundary;
import Interactor.AccountInteractor;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/accounts")
public class AccountController {
    private AccountInteractorBoundary aib = AccountInteractor.INSTANCE;
    private Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccounts(@Context UriInfo uriInfo){
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
