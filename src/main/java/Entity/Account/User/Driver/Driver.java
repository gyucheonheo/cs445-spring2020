package Entity.Account.User.Driver;

import Lib.UniqueId;

public class Driver {
    private String did;
    private String first;
    private String last;
    private Rate rates;
    private List<Comment> comments;
    public String picture;
    public Driver(){
        did = UniqueId.getUniqueID();
    }

    public boolean isNil(){
        return false;
    }
}
