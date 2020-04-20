package InteractorTest;

import Boundary.Account.AccountInteractorCommandBoundary;
import Boundary.Account.AccountInteractorQueryBoundary;
import Boundary.Report.ReportInteractorBoundary;
import Boundary.RideRequest.RideRequestInteractorCommandBoundary;
import Boundary.RideRequest.RideRequestInteractorQueryBoundary;
import Boundary.Trip.TripInteractorCommandBoundary;
import Boundary.Trip.TripInteractorQueryBoundary;
import Entity.Boundary.Account.User.User;
import Entity.Boundary.RideRequest.RideRequest;
import Entity.Boundary.Trip.Car.Car;
import Entity.Boundary.Trip.DateTimeFormat.DateTimeFormat;
import Entity.Boundary.Trip.LocationInformation.LocationInformation;
import Entity.Boundary.Trip.Rules.Rules;
import Entity.Boundary.Trip.Trip;
import Entity.Bounded.Account.CellPhoneFormat.BoundedCellPhoneFormat;
import Entity.Bounded.RideRequest.BoundedRideRequest;
import Entity.Bounded.Trip.Car.BoundedCar;
import Entity.Bounded.Trip.Car.Vehicle.BoundedVehicle;
import Entity.Bounded.Trip.DateTimeFormat.BoundedDateTimeFormat;
import Entity.Bounded.Trip.LocationInformation.BoundedLocationInformation;
import Entity.Bounded.Trip.LocationInformation.Location.BoundedLocation;
import Entity.Bounded.Trip.Rules.BoundedRules;
import Interactor.Account.AccountInteractorCommand;
import Interactor.Account.AccountInteractorQuery;
import Interactor.Report.ReportInteractor;
import Interactor.RideRequest.RideRequestInteractorCommand;
import Interactor.RideRequest.RideRequestInteractorQuery;
import Interactor.Trip.TripInteractor;
import Interactor.Trip.TripInteractorCommand;
import Interactor.Trip.TripInteractorQuery;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Test;

import java.text.ParseException;

public class ReportInteractorTest {
    private ReportInteractorBoundary report_i = ReportInteractor.INSTANCE;
    @After
    public void tearUp(){
        report_i.cleanUpReport();
        TripInteractor t = TripInteractor.INSTANCE;
        t.getTrips().clear();
    }
    @Test
    public void getReports_should_have_two_reports(){
        JsonArray reports = report_i.getReports();
        Assert.assertEquals(2, reports.size());
    }
    @Test
    public void calling_createPostingRideReport_will_assign_fresh_report_template_to_postingRideReportMember(){
        report_i.createPostingRideReport();
        Assert.assertEquals("Rides posted between two dates", report_i.getReports().get(0).getAsJsonObject().get("name").getAsString());
    }

    @Test
    public void calling_createTakingRideReport_will_assign_fresh_report_template_to_takingRideReportMember(){
        report_i.createTakingRideReport();
        Assert.assertEquals("Rides taken between two dates", report_i.getReports().get(1).getAsJsonObject().get("name").getAsString());
    }

    @Test
    public void isPidValid_should_return_false_with_not_existing_pid(){
        report_i.createPostingRideReport();
        report_i.createTakingRideReport();
        Assert.assertFalse(report_i.isPidValid("asdf"));
    }
    @Test
    public void isPidValid_should_return_true_when_two_pids_exist_respectively(){
        report_i.createPostingRideReport();
        report_i.createTakingRideReport();
        JsonObject p = report_i.getPostingRideReport();
        JsonObject t = report_i.getTakingRideReport();
        Assert.assertTrue(report_i.isPidValid(p.get("pid").getAsString()));
        Assert.assertTrue(report_i.isPidValid(t.get("pid").getAsString()));
    }
    @Test
    public void isPidRelatedToPostingRideReport_should_return_true_when_pid_is_linked_to_posting_report(){
        report_i.createPostingRideReport();
        report_i.createTakingRideReport();
        JsonObject p = report_i.getPostingRideReport();
        Assert.assertTrue(report_i.isPidRelatedToPostingRideReport(p.get("pid").getAsString()));
    }

    @Test
    public void isPidRelatedToPostingRideReport_should_return_false_when_pid_is_NOT_linked_to_posting_report(){
        report_i.createPostingRideReport();
        report_i.createTakingRideReport();
        JsonObject t = report_i.getTakingRideReport();
        Assert.assertFalse(report_i.isPidRelatedToPostingRideReport(t.get("pid").getAsString()));
    }
    @Test(expected=java.lang.NullPointerException.class)
    public void accessing_to_extra_info_of_posting_report_should_throw_NullPointer_without_calling_addDetail(){
        report_i.createPostingRideReport();
        report_i.createTakingRideReport();
        JsonObject p = report_i.getPostingRideReport();
        p.get("start_date").getAsString();
    }

    @Test
    public void addDetailToPositingRideReport_should_have_empty_and_zero_result() throws ParseException {
        report_i.createPostingRideReport();
        report_i.createTakingRideReport();
        JsonObject p = report_i.getPostingRideReport();
        report_i.addDetailToPostingRideReport("","");
        Assert.assertEquals("",p.get("start_date").getAsString());
        Assert.assertEquals("", p.get("end_date").getAsString());
        Assert.assertEquals(0, p.get("detail").getAsJsonArray().size());
        Assert.assertEquals(0, p.get("rides").getAsInt());
    }
    @Test
    public void addDetailToPositingRideReport_should_have_empty_and_zero_result_when_dates_are_null() throws ParseException {
        report_i.createPostingRideReport();
        report_i.createTakingRideReport();
        JsonObject p = report_i.getPostingRideReport();
        report_i.addDetailToPostingRideReport(null,null);
        Assert.assertEquals("",p.get("start_date").getAsString());
        Assert.assertEquals("", p.get("end_date").getAsString());
        Assert.assertEquals(0, p.get("detail").getAsJsonArray().size());
        Assert.assertEquals(0, p.get("rides").getAsInt());
    }
    @Test
    public void addDetailToPositingRideReport_should_have_one_detail() throws ParseException {
        TripInteractorQueryBoundary trip_query_i;
        TripInteractorCommandBoundary trip_command_i;
        AccountInteractorQueryBoundary acc_query_i;
        AccountInteractorCommandBoundary acc_command_i;

        trip_query_i = TripInteractorQuery.INSTANCE;
        trip_command_i = TripInteractorCommand.INSTANCE;
        acc_query_i = AccountInteractorQuery.INSTANCE;
        acc_command_i = AccountInteractorCommand.INSTANCE;

        User driver1 = acc_query_i.createUser("driver1", "lastName", BoundedCellPhoneFormat.Make("111","222","3333"), "http://example.com/test.png");
        acc_command_i.registerUser(driver1);
        acc_command_i.activateUser(driver1.getAid());

        Trip t1;
        String conditions = "";
        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo = BoundedRules.Make(2, 5, conditions);
        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime("15-May-2020, 09:00");
        t1 = trip_query_i.createTrip(driver1.getAid(), locationInfo, carInfo, dt, passengerInfo);
        trip_command_i.registerTrip(t1);

        report_i.createPostingRideReport();
        report_i.createTakingRideReport();
        JsonObject p = report_i.getPostingRideReport();
        report_i.addDetailToPostingRideReport(null,null);
        Assert.assertEquals("",p.get("start_date").getAsString());
        Assert.assertEquals("", p.get("end_date").getAsString());
        Assert.assertEquals(1, p.get("detail").getAsJsonArray().size());
        Assert.assertEquals("60616", p.get("detail").getAsJsonArray().get(0).getAsJsonObject().get("from_zip").getAsString());
        Assert.assertEquals("",p.get("detail").getAsJsonArray().get(0).getAsJsonObject().get("to_zip").getAsString());
        Assert.assertEquals(1, p.get("detail").getAsJsonArray().get(0).getAsJsonObject().get("count").getAsInt());
        Assert.assertEquals(1, p.get("rides").getAsInt());
    }
    @Test
    public void addDetailToPositingRideReport_should_have_one_detail_and_increase_count_when_same_zip_pattern_are_found() throws ParseException {
        TripInteractorQueryBoundary trip_query_i;
        TripInteractorCommandBoundary trip_command_i;
        AccountInteractorQueryBoundary acc_query_i;
        AccountInteractorCommandBoundary acc_command_i;

        trip_query_i = TripInteractorQuery.INSTANCE;
        trip_command_i = TripInteractorCommand.INSTANCE;
        acc_query_i = AccountInteractorQuery.INSTANCE;
        acc_command_i = AccountInteractorCommand.INSTANCE;

        User driver1 = acc_query_i.createUser("driver1", "lastName", BoundedCellPhoneFormat.Make("111","222","3333"), "http://example.com/test.png");
        acc_command_i.registerUser(driver1);
        acc_command_i.activateUser(driver1.getAid());

        Trip t1;
        String conditions = "";
        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo = BoundedRules.Make(2, 5, conditions);
        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime("15-May-2020, 09:00");
        t1 = trip_query_i.createTrip(driver1.getAid(), locationInfo, carInfo, dt, passengerInfo);
        trip_command_i.registerTrip(t1);

        Trip t2;
        LocationInformation locationInfo1 = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo1 =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo1 = BoundedRules.Make(2, 5, conditions);
        DateTimeFormat dt1 = BoundedDateTimeFormat.MakeDateTime("15-May-2020, 09:00");
        t2 = trip_query_i.createTrip(driver1.getAid(), locationInfo1, carInfo1, dt1, passengerInfo1);
        trip_command_i.registerTrip(t2);

        report_i.createPostingRideReport();
        report_i.createTakingRideReport();
        JsonObject p = report_i.getPostingRideReport();
        report_i.addDetailToPostingRideReport(null,null);
        Assert.assertEquals("",p.get("start_date").getAsString());
        Assert.assertEquals("", p.get("end_date").getAsString());
        Assert.assertEquals(1, p.get("detail").getAsJsonArray().size());
        Assert.assertEquals("60616", p.get("detail").getAsJsonArray().get(0).getAsJsonObject().get("from_zip").getAsString());
        Assert.assertEquals("",p.get("detail").getAsJsonArray().get(0).getAsJsonObject().get("to_zip").getAsString());
        Assert.assertEquals(2, p.get("detail").getAsJsonArray().get(0).getAsJsonObject().get("count").getAsInt());
        Assert.assertEquals(2, p.get("rides").getAsInt());
    }
    @Test(expected=java.lang.NullPointerException.class)
    public void accessing_to_extra_info_of_taking_report_should_throw_NullPointer_without_calling_addDetail(){
        report_i.createPostingRideReport();
        report_i.createTakingRideReport();
        JsonObject t = report_i.getTakingRideReport();
        t.get("start_date").getAsString();
    }
    @Test
    public void addDetailToTakingRideReport_should_have_empty_and_zero_result() throws ParseException {
        report_i.createPostingRideReport();
        report_i.createTakingRideReport();
        JsonObject t = report_i.getTakingRideReport();
        report_i.addDetailToTakingRideReport("","");
        Assert.assertEquals("",t.get("start_date").getAsString());
        Assert.assertEquals("", t.get("end_date").getAsString());
        Assert.assertEquals(0, t.get("detail").getAsJsonArray().size());
        Assert.assertEquals(0, t.get("rides").getAsInt());
    }
    @Test
    public void addDetailToTakingRideReport_should_have_empty_and_zero_result_when_dates_are_null() throws ParseException {
        report_i.createPostingRideReport();
        report_i.createTakingRideReport();
        JsonObject t = report_i.getTakingRideReport();
        report_i.addDetailToTakingRideReport(null,null);
        Assert.assertEquals("",t.get("start_date").getAsString());
        Assert.assertEquals("", t.get("end_date").getAsString());
        Assert.assertEquals(0, t.get("detail").getAsJsonArray().size());
        Assert.assertEquals(0, t.get("rides").getAsInt());
    }
    @Test
    public void addDetailToTakingRideReport_should_have_one_detail_when_same_zip_pattern_are_found() throws ParseException {
        TripInteractorQueryBoundary trip_query_i;
        TripInteractorCommandBoundary trip_command_i;
        AccountInteractorQueryBoundary acc_query_i;
        AccountInteractorCommandBoundary acc_command_i;

        trip_query_i = TripInteractorQuery.INSTANCE;
        trip_command_i = TripInteractorCommand.INSTANCE;
        acc_query_i = AccountInteractorQuery.INSTANCE;
        acc_command_i = AccountInteractorCommand.INSTANCE;

        User driver1 = acc_query_i.createUser("driver1", "lastName", BoundedCellPhoneFormat.Make("111","222","3333"), "http://example.com/test.png");
        acc_command_i.registerUser(driver1);
        acc_command_i.activateUser(driver1.getAid());

        User rider = acc_query_i.createUser("driver1", "lastName", BoundedCellPhoneFormat.Make("111","222","3333"), "http://example.com/test.png");
        acc_command_i.registerUser(rider);
        acc_command_i.activateUser(rider.getAid());

        Trip t1;
        String conditions = "";
        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo = BoundedRules.Make(2, 5, conditions);
        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime("15-May-2020, 09:00");
        t1 = trip_query_i.createTrip(driver1.getAid(), locationInfo, carInfo, dt, passengerInfo);
        trip_command_i.registerTrip(t1);

        Trip t2;
        LocationInformation locationInfo1 = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo1 =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo1 = BoundedRules.Make(2, 5, conditions);
        DateTimeFormat dt1 = BoundedDateTimeFormat.MakeDateTime("15-May-2020, 09:00");
        t2 = trip_query_i.createTrip(driver1.getAid(), locationInfo1, carInfo1, dt1, passengerInfo1);
        trip_command_i.registerTrip(t2);

        RideRequestInteractorQueryBoundary request_query_i = RideRequestInteractorQuery.INSTANCE;
        RideRequestInteractorCommandBoundary request_command_i = RideRequestInteractorCommand.INSTANCE;

        RideRequest rr = BoundedRideRequest.Make(rider.getAid(), t1.getTid(),2);
        request_query_i.requestRide(rr);

        request_command_i.confirmRide(driver1.getAid(), t1.getTid(), rr.getJid());
        rr.setIsPickUpConfirmed(true);

        report_i.createPostingRideReport();
        report_i.createTakingRideReport();
        JsonObject t = report_i.getTakingRideReport();
        report_i.addDetailToTakingRideReport(null,null);
        Assert.assertEquals("",t.get("start_date").getAsString());
        Assert.assertEquals("", t.get("end_date").getAsString());
        Assert.assertEquals(1, t.get("detail").getAsJsonArray().size());
        Assert.assertEquals("60616", t.get("detail").getAsJsonArray().get(0).getAsJsonObject().get("from_zip").getAsString());
        Assert.assertEquals("",t.get("detail").getAsJsonArray().get(0).getAsJsonObject().get("to_zip").getAsString());
        Assert.assertEquals(1, t.get("detail").getAsJsonArray().get(0).getAsJsonObject().get("count").getAsInt());
        Assert.assertEquals(1, t.get("rides").getAsInt());
    }

    @Test
    public void addDetailToTakingRideReport_should_have_one_increment_detail_when_same_zip_pattern_are_found() throws ParseException {
        TripInteractorQueryBoundary trip_query_i;
        TripInteractorCommandBoundary trip_command_i;
        AccountInteractorQueryBoundary acc_query_i;
        AccountInteractorCommandBoundary acc_command_i;

        trip_query_i = TripInteractorQuery.INSTANCE;
        trip_command_i = TripInteractorCommand.INSTANCE;
        acc_query_i = AccountInteractorQuery.INSTANCE;
        acc_command_i = AccountInteractorCommand.INSTANCE;

        User driver1 = acc_query_i.createUser("driver1", "lastName", BoundedCellPhoneFormat.Make("111","222","3333"), "http://example.com/test.png");
        acc_command_i.registerUser(driver1);
        acc_command_i.activateUser(driver1.getAid());

        User rider = acc_query_i.createUser("driver1", "lastName", BoundedCellPhoneFormat.Make("111","222","3333"), "http://example.com/test.png");
        acc_command_i.registerUser(rider);
        acc_command_i.activateUser(rider.getAid());

        Trip t1;
        String conditions = "";
        LocationInformation locationInfo = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo = BoundedRules.Make(2, 5, conditions);
        DateTimeFormat dt = BoundedDateTimeFormat.MakeDateTime("15-May-2020, 09:00");
        t1 = trip_query_i.createTrip(driver1.getAid(), locationInfo, carInfo, dt, passengerInfo);
        trip_command_i.registerTrip(t1);

        Trip t2;
        LocationInformation locationInfo1 = BoundedLocationInformation.Make(BoundedLocation.Make("Chicago", "60616"), BoundedLocation.Make("Los Angeles",""));
        Car carInfo1 =  BoundedCar.Make(BoundedVehicle.Make("Chevy", "Cruze","White"), "IL", "COVID19");
        Rules passengerInfo1 = BoundedRules.Make(2, 5, conditions);
        DateTimeFormat dt1 = BoundedDateTimeFormat.MakeDateTime("15-May-2020, 09:00");
        t2 = trip_query_i.createTrip(driver1.getAid(), locationInfo1, carInfo1, dt1, passengerInfo1);
        trip_command_i.registerTrip(t2);

        RideRequestInteractorQueryBoundary request_query_i = RideRequestInteractorQuery.INSTANCE;
        RideRequestInteractorCommandBoundary request_command_i = RideRequestInteractorCommand.INSTANCE;

        RideRequest rr = BoundedRideRequest.Make(rider.getAid(), t1.getTid(),2);
        request_query_i.requestRide(rr);

        request_command_i.confirmRide(driver1.getAid(), t1.getTid(), rr.getJid());
        rr.setIsPickUpConfirmed(true);

        RideRequest rr1 = BoundedRideRequest.Make(rider.getAid(), t2.getTid(),2);
        request_query_i.requestRide(rr1);

        request_command_i.confirmRide(driver1.getAid(), t2.getTid(), rr1.getJid());
        rr1.setIsPickUpConfirmed(true);

        report_i.createPostingRideReport();
        report_i.createTakingRideReport();
        JsonObject t = report_i.getTakingRideReport();
        report_i.addDetailToTakingRideReport(null,null);
        Assert.assertEquals("",t.get("start_date").getAsString());
        Assert.assertEquals("", t.get("end_date").getAsString());
        Assert.assertEquals(1, t.get("detail").getAsJsonArray().size());
        Assert.assertEquals("60616", t.get("detail").getAsJsonArray().get(0).getAsJsonObject().get("from_zip").getAsString());
        Assert.assertEquals("",t.get("detail").getAsJsonArray().get(0).getAsJsonObject().get("to_zip").getAsString());
        Assert.assertEquals(2, t.get("detail").getAsJsonArray().get(0).getAsJsonObject().get("count").getAsInt());
        Assert.assertEquals(2, t.get("rides").getAsInt());
    }
}
