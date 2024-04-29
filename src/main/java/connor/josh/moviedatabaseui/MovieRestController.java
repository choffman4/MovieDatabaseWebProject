package connor.josh.moviedatabaseui;

import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/movies")
public class MovieRestController {

    MovieBLL mb = new MovieBLL();

    //REVIEWS
    ///////////////////////////////////////////////////////////////////////////////q
    @RequestMapping(path = "/createReview", method = RequestMethod.POST)
    @ResponseBody
    public void addReview(@RequestBody Review review) throws IOException {
        mb.addMovieReview(review);
    }

    @RequestMapping(path = "/updateReview", method = RequestMethod.PUT)
    @ResponseBody
    public void updateReview(@RequestBody Review review) throws IOException {
        mb.updateMovieReview(review);
    }

    @RequestMapping(path = "/deleteReview/{username}/{imdbid}", method = RequestMethod.DELETE)
    public void deleteReview(@PathVariable String username, @PathVariable String imdbid) throws IOException {
        mb.deleteMovieReview(username, imdbid);
    }

    @RequestMapping(path="/reviews/{imdbid}", method = RequestMethod.GET)
    public ArrayList<Review> findReviews(@PathVariable String imdbid) throws IOException {
        return mb.findMovieReviewByID(imdbid);
    }

    @RequestMapping(path="/reviews/{username}", method = RequestMethod.GET)
    public ArrayList<Review> findReviewsbyUser(@PathVariable String username) throws IOException {
        return mb.findMovieReviewByUser(username);
    }

    //RECOMMENDS
    ///////////////////////////////////////////////////////////////////////////////
    @RequestMapping(path = "/recommends/add/{username}/{imdbid}", method = RequestMethod.POST)
    public void addRecommendation(@PathVariable String username, @PathVariable String imdbid) throws IOException {
        mb.addMovieRecommendation(username, imdbid);
    }

    @RequestMapping(path = "/recommends/remove/{username}/{imdbid}", method = RequestMethod.DELETE)
    public void removeRecommendation(@PathVariable String username, @PathVariable String imdbid) throws IOException {
        mb.removeMovieRecommendation(username, imdbid);
    }

    @RequestMapping(path = "/recommends/{imdbid}", method = RequestMethod.GET)
    public int getAllMovieRecommendations(@PathVariable String imdbid) throws IOException {
        return mb.getAllMovieRecommends(imdbid);
    }

    //FAVORITES
    ////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(path = "/favorites/add/{username}/{imdbid}", method = RequestMethod.POST)
    public void addMovieToFavorites(@PathVariable String imdbid, @PathVariable String username) throws IOException {
        mb.addMovieToFavorites(username, imdbid);
    }

    @RequestMapping(path = "/favorites/delete/{username}/{imdbid}", method = RequestMethod.DELETE)
    public void deleteMovieFromFavorites(@PathVariable String imdbid, @PathVariable String username) throws IOException {
        mb.deleteMovieFromFavorites(username, imdbid);
    }

    @RequestMapping(path = "/favorites/{username}", method = RequestMethod.GET)
    public ArrayList<String> getAllFavorites(@PathVariable String username) throws IOException {
        return mb.getAllFavorites(username);
    }
}