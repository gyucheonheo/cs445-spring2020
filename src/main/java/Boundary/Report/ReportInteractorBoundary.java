package Boundary.Report;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.text.ParseException;

public interface ReportInteractorBoundary {
    void createPostingRideReport();

    void createTakingRideReport();

    JsonArray getReports();

    boolean isPidValid(String pid);

    boolean isPidRelatedToPostingRideReport(String pid);

    JsonObject addDetailToPostingRideReport(String start_date, String end_date) throws ParseException;

    JsonObject addDetailToTakingRideReport(String start_date, String end_date) throws ParseException;
}
