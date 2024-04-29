package connor.josh.moviedatabaseui;

import java.util.ArrayList;

public class Movie {

    private int recommends;
    private String imdbID;

    public Movie(String imdbID, int recommends) {
        this.imdbID = imdbID;
        this.recommends = recommends;
    }

    public Movie() {
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public int getRecommends() {
        return recommends;
    }

    public void setRecommends(int recommends) {
        this.recommends = recommends;
    }
}
