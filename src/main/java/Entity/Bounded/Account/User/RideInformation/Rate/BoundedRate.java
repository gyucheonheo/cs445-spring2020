package Entity.Bounded.Account.User.RideInformation.Rate;

import Entity.Boundary.Account.User.RideInformation.Rate.Rate;
import Entity.Bounded.Trip.DateTimeFormat.BoundedDateTimeFormat;
import Lib.UniqueId;

public class BoundedRate implements Rate {
    private String rid;
    private String sentBy;
    private String date;
    private int rating;
    private String comment;

    public static Rate Make(){
        return new NullBoundedRate();
    }
        protected BoundedRate(){ }
    public static Rate Make(String rid, String sentBy, int rating, String comment){
        return new BoundedRate(rid, sentBy, rating, comment);
    }
        private BoundedRate(String rid, String sentBy, int rating, String comment){
            this.rid = rid;
            date = BoundedDateTimeFormat.MakeNow().getDate();
            setSentBy(sentBy);
            setRating(rating);
            setComment(comment);
        }

            private void setSentBy(String sentBy) {
                this.sentBy = sentBy;
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


