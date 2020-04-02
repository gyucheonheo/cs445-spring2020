package Entity.Bounded.Account.User.RideInformation.Rate;

import Entity.Boundary.Account.User.RideInformation.Rate.Rate;
import Entity.Bounded.Trip.DateTimeFormat.BoundedDateTimeFormat;
import Lib.UniqueId;

public class BoundedRate implements Rate {
    private String rid;
    private String sentBy;
    private String firstName;
    private String date;
    private int rating;
    private String comment;

    public static Rate Make(){
        return new NullBoundedRate();
    }
        protected BoundedRate(){ }
    public static Rate Make(String sentBy, String firstName, int rating, String comment){
        return new BoundedRate(sentBy, firstName, rating, comment);
    }
        private BoundedRate(String sentBy, String firstName, int rating, String comment){
            rid = UniqueId.getUniqueID();
            date = BoundedDateTimeFormat.MakeNow().getDate();
            setSentBy(sentBy);
            setFirstName(firstName);
            setRating(rating);
            setComment(comment);
        }

            private void setSentBy(String sentBy) {
                this.sentBy = sentBy;
            }

            private void setFirstName(String firstName){
                this.firstName = firstName;
            }
            private void setRating(int rating){
                this.rating = rating;
            }
            private void setComment(String comment){
                this.comment = comment;
            }

    public String getRid(){
        return this.rid;
    }
    public String getSentBy(){
        return this.sentBy;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public String getDate(){
        return this.date;
    }
    public int getRating(){
        return this.rating;
    }
    public String getComment(){
        return this.comment;
    }
}


