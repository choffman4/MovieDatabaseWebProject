package connor.josh.moviedatabaseui;

public class Review {

    private String imdbid;
    private String username;
    private String review;

    public Review(String imdbid, String username, String review) {
        this.imdbid = imdbid;
        this.username = username;
        this.review = review;
    }

    public String getImdbid() {
        return imdbid;
    }

    public void setImdbid(String imdbid) {
        this.imdbid = imdbid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
