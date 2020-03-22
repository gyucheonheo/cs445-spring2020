package Entity.Note;

import Lib.UniqueId;

public class Note {
    private String nid;
    private int numberOfPassengers;
    private Location pickUpPlace;
    private Location dropOffPlace;

    public Note(){
        this.nid = UniqueId.getUniqueID();
    }

    public boolean isNil(){
        return false;
    }
}
