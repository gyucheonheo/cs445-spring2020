package Interactor.Report;

import Boundary.Report.ReportInteractorBoundary;
import Boundary.Trip.TripInteractorQueryBoundary;
import Entity.Boundary.Trip.Trip;
import Interactor.Trip.TripInteractorQuery;
import Lib.UniqueId;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.util.List;


public enum ReportInteractor implements ReportInteractorBoundary {
    INSTANCE;
    private JsonObject postingRideReport;
    private JsonObject takingRideReport;

    public void createPostingRideReport() {
        JsonObject report = new JsonObject();

        report.addProperty("pid", UniqueId.getUniqueID());
        report.addProperty("name", "Rides posted between two dates");

        this.postingRideReport = report;
    }

    public void createTakingRideReport() {
        JsonObject report = new JsonObject();
        report.addProperty("pid", UniqueId.getUniqueID());
        report.addProperty("name", "Rides taken between two dates");

        this.takingRideReport = report;
    }

    public JsonArray getReports() {
        JsonArray reports = new JsonArray();
        reports.add(postingRideReport);
        reports.add(takingRideReport);
        return reports;
    }

    public boolean isPidValid(String pid) {
        if(postingRideReport.get("pid").getAsString().equals(pid)){
            return true;
        }

        if(takingRideReport.get("pid").getAsString().equals(pid)){
            return true;
        }
        return false;
    }

    public boolean isPidRelatedToPostingRideReport(String pid) {
        return postingRideReport.get("pid").getAsString().equals(pid);
    }

    public JsonObject addDetailToPostingRideReport(String start_date, String end_date) throws ParseException {
        TripInteractorQueryBoundary ti = TripInteractorQuery.INSTANCE;
        if(start_date == null){
            start_date = "";
        }
        if(end_date == null){
            end_date = "";
        }
        postingRideReport.addProperty("start_date", start_date);
        postingRideReport.addProperty("end_date", end_date);
        List<Trip> result = ti.getPostingTripsBetweenDates(start_date, end_date);
        JsonArray details = new JsonArray();
        for(Trip t : result){
            JsonObject singleTrip = new JsonObject();
            singleTrip.addProperty("from_zip", t.getLocationInformation().getStartingPoint().getZip());
            singleTrip.addProperty("to_zip", t.getLocationInformation().getEndingPoint().getZip());
            singleTrip.addProperty("count", 1);
            details.add(singleTrip);
        }
        postingRideReport.addProperty("rides", result.size());
        postingRideReport.add("detail", details);

        return this.postingRideReport;
    }

    public JsonObject addDetailToTakingRideReport(String start_date, String end_date) throws ParseException {
        TripInteractorQueryBoundary ti = TripInteractorQuery.INSTANCE;
        if(start_date == null){
            start_date = "";
        }
        if(end_date == null){
            end_date = "";
        }
        takingRideReport.addProperty("start_date", start_date);
        takingRideReport.addProperty("end_date", end_date);
        List<Trip> result = ti.getTakingTripsBetweenDates(start_date, end_date);
        JsonArray details = new JsonArray();
        for(Trip t : result){
            JsonObject singleTrip = new JsonObject();
            singleTrip.addProperty("from_zip", t.getLocationInformation().getStartingPoint().getZip());
            singleTrip.addProperty("to_zip", t.getLocationInformation().getEndingPoint().getZip());
            singleTrip.addProperty("count", 1);
            details.add(singleTrip);
        }
        takingRideReport.addProperty("rides", result.size());
        takingRideReport.add("detail", details);

        return this.takingRideReport;
    }

    public JsonObject getPostingRideReport() {
        return this.postingRideReport;
    }

    public JsonObject getTakingRideReport() {
        return this.takingRideReport;
    }
}
