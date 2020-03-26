package Interactor;

import Boundary.MessageInteractorBoundary;
import Boundary.Trip.TripInteractorBoundary;
import Entity.Boundary.Message.Message;

import java.util.ArrayList;
import java.util.List;

public enum MessageInteractor implements MessageInteractorBoundary {
    INSTANCE;
    private static List<Message> msgs = new ArrayList<>();
    private TripInteractorBoundary tb = TripInteractor.INSTANCE;
    public void sendMsgToRide(String rid, Message msg) {
        try {
            tb.getTripById(rid);
        }
        catch(TripInteractorBoundary.NotFoundByTripIdException e){
            throw new RideMatchedByRidNotFoundException();
        }
    }

    public List<Message> getAllMessagesByRid(String rid) {
        return null;
    }
}
