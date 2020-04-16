package Controller.Report;

import Boundary.Report.ReportInteractorBoundary;
import Interactor.Report.ReportInteractor;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.text.ParseException;

@Path("reports")
public class ReportQueryController {
    private ReportInteractorBoundary report_i = ReportInteractor.INSTANCE;
    private Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewAvailableReport(){
        report_i.createPostingRideReport();
        report_i.createTakingRideReport();
        JsonArray reports = report_i.getReports();
        return Response.status(Response.Status.OK).entity(gson.toJson(reports)).build();
    }

    @Path("{pid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReport(@PathParam("pid") String pid, @QueryParam("start_date") String start_date, @QueryParam("end_date") String end_date) throws ParseException {
        if(!report_i.isPidValid(pid)){
            return Response.status(Response.Status.NOT_FOUND).entity("Not found " + pid).build();
        }
        String s;
        if(report_i.isPidRelatedToPostingRideReport(pid)){
           s = gson.toJson(report_i.addDetailToPostingRideReport(start_date, end_date));
        } else {
           s = gson.toJson(report_i.addDetailToTakingRideReport(start_date, end_date));
        }
        return Response.status(Response.Status.OK).entity(s).build();
    }
}
