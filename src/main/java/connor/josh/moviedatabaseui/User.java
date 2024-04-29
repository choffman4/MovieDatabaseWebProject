package connor.josh.moviedatabaseui;

import java.util.ArrayList;

public class User {

    private String username;
    private String password;

    public static ArrayList<Movie> allMovies = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    private ArrayList<User> favorites = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
