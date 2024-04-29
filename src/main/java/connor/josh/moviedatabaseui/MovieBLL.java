package connor.josh.moviedatabaseui;

import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MovieBLL {

    static String url = "jdbc:mysql://awseb-e-ctpmnqybmw-stack-awsebrdsdatabase-gmem0u0zwemc.ciabqvuwkkzn.us-west-2.rds.amazonaws.com:3306/" +
            "moviedb";
    static String user = "admin";
    static String password = "password";
    public static ArrayList<Review> allReviews = new ArrayList<>();

    //MOVIE RECOMMENDS
    //////////////////////////////////////////////////////////////////////////////
    public void addMovieRecommendation(String username, String imdbID) throws IOException {
        String sql = "INSERT INTO moviedb.userrecommends (id ,username, imdbid) Values(?, ?, ?)";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, (username + ":" + imdbID));
            pst.setString(2, username);
            pst.setString(3, imdbID);
            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeMovieRecommendation(String username, String imdbID) throws IOException {
        String sql = "DELETE FROM moviedb.userrecommends WHERE id=(?)";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, (username + ":" + imdbID));
            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getAllMovieRecommends(String imdbid) throws IOException {
        int recommends = 0;
        String sql = "SELECT * from moviedb.userrecommends where imdbid=(?);";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, imdbid);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                recommends++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return recommends;
    }

    //MOVIE REVIEW CRUD
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void addMovieReview(Review review) {
        String sql = "INSERT INTO moviedb.moviereview (username, imdbid, review) Values(?, ?, ?)";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, review.getUsername());
            pst.setString(2, review.getImdbid());
            pst.setString(3, review.getReview());
            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateMovieReview(Review review) {
        String sql = "UPDATE moviedb.moviereview where (imdbid=(?) and username=(?)) set review=(?);";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, review.getImdbid());
            pst.setString(2, review.getUsername());
            pst.setString(3, review.getReview());
            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMovieReview(String username, String imdbid) {
        String sql = "DELETE FROM moviedb.moviereview WHERE username=(?) and imdbid=(?)";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, imdbid);
            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Review> findAllMovieReviews() throws IOException {
        String sql = "Select * from moviedb.movie";
        allReviews.clear();

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                String imdbID = rs.getString("imdbid");
                String username = rs.getString("username");
                String review = rs.getString("review");
                Review movieReview = new Review(imdbID, username, review);
                allReviews.add(movieReview);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return allReviews;
    }

    public ArrayList<Review> findMovieReviewByID(String imdbID) throws IOException {
        ArrayList<Review> movieReviews = new ArrayList<>();
        for (Review review: findAllMovieReviews()) {
            if(review.getImdbid() == imdbID) {
                movieReviews.add(review);
            }

        }
        return movieReviews;
    }

    public ArrayList<Review> findMovieReviewByUser(String username) throws IOException {
        ArrayList<Review> movieReviews = new ArrayList<>();
        for (Review review: findAllMovieReviews()) {
            if(review.getUsername() == username) {
                movieReviews.add(review);
            }

        }
        return movieReviews;
    }

    //MOVIE REVIEW CRUD
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void addMovieToFavorites(String username, String imdbid) {
        String sql = "INSERT INTO moviedb.userfavorite (id ,username, imdbid) Values(?, ?, ?)";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, (username + ":" + imdbid));
            pst.setString(2, username);
            pst.setString(3, imdbid);
            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMovieFromFavorites(String username, String imdbid) {
        String sql = "DELETE FROM moviedb.userfavorite WHERE id=(?)";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, (username + ":" + imdbid));
            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getAllFavorites(String username) throws IOException {
        String sql = "Select * from moviedb.userfavorite where username=(?)";
        ArrayList<String> favorites = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                String imdbID = rs.getString("imdbid");
                favorites.add(imdbID);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return favorites;
    }

}
