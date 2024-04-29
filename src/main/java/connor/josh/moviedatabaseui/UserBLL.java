package connor.josh.moviedatabaseui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserBLL {

    static String url = "jdbc:mysql://awseb-e-ctpmnqybmw-stack-awsebrdsdatabase-gmem0u0zwemc.ciabqvuwkkzn.us-west-2.rds.amazonaws.com:3306/" +
            "moviedb";
    static String user = "admin";
    static String pass = "password";

    public void newUser(User newUser) {
        String sql = "INSERT INTO moviedb.useraccount (username, password) Values(?, ?)";

        try {
            Connection con = DriverManager.getConnection(url, user, pass);
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, newUser.getUsername());
            pst.setString(2, newUser.getPassword());
            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User accountLogin(String username, String password) {
        String sql = "Select * from moviedb.useraccount where username=(?) and password=(?)";
        User foundUser = new User();

        try {
            Connection con = DriverManager.getConnection(url, user, pass);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,username);
            pst.setString(2,password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                foundUser.setUsername(rs.getString("username"));
                foundUser.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
        }
        return foundUser;
    }

    public void deleteUser(String username, String pass) {

        String sql = "DELETE FROM moviedb.useraccount WHERE username=(?) and password=(?);";

        try {
            Connection con = DriverManager.getConnection(url, user, pass);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,username);
            pst.setString(2,pass);
            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUserPassword(String username, String pass, String newPass) {
        String sql = "UPDATE moviedb.useraccount SET password=(?) WHERE username=(?) and password=(?);";

        try {
            Connection con = DriverManager.getConnection(url, user, pass);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,newPass);
            pst.setString(2,username);
            pst.setString(3,pass);
            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUsername(String currentUsername, String password, String newUsername) {
        String sql = "UPDATE moviedb.useraccount SET username=(?) WHERE (username=(?) and password=(?));";

        try {
            Connection con = DriverManager.getConnection(url, user, pass);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,newUsername);
            pst.setString(2,currentUsername);
            pst.setString(3,password);
            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /////////////////////////////////////////////////////////////////////////////////

    public void addFavoriteMovie(String username, String imdbID) {
        String sql = "INSERT INTO moviedb.userfavorite (username, imdbid) Values(?, ?)";

        try {
            Connection con = DriverManager.getConnection(url, user, pass);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, imdbID);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFavoriteMovie(String username, String imdbID) {
        String sql = "DELETE FROM moviedb.userfavorite WHERE username=(?) and imdbid=(?);";

        try {
            Connection con = DriverManager.getConnection(url, user, pass);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, imdbID);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
