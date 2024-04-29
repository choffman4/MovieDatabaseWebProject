var authHeaderValue = null;
var username = null;
var password = null;


//retrieve cookies
function getCookie(cname) {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for(let i = 0; i <ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

//onload, grab cookies, get favorites for signed-in user
window.onload = function() {
    username = getCookie("username");
    password = getCookie("password");
    showProfileTags();
    var movieList = document.getElementById("user_favorites");
    movieList.innerHTML = "";
    getFavorites()
}

//grabs favorites for signed in user,
function getFavorites() {
    authHeaderValue = "Basic " + btoa(username + ":" + password); //btoa base 64 encoding
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", "http://Moviedbjava-env.eba-pppkpw72.us-west-2.elasticbeanstalk.com:5000/movies/favorites/" + username);
    xmlHttp.setRequestHeader("Authorization", authHeaderValue);
    xmlHttp.onreadystatechange = function () {
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            var favorites = JSON.parse(this.responseText);
            for (let i = 0; i < favorites.length; i++) {
                const favorite = favorites[i];
                fetchMovies(favorite)
            }
        }
    }
    xmlHttp.send();
}

//fetch movies from by movieid
function fetchMovies(imdbID) {

    fetch("http://www.omdbapi.com/?i=" + imdbID + "&plot=full&apikey=7c9d77e9", {
        method: 'GET',
    })
        .then((res) => res.json())
        .then((res) => {
            var movieList = document.getElementById("user_favorites");
            var movieHTML = `</br><button type=\"button\" value="` + res.imdbID + `" onclick="fetchMovie(this.value)">`
                + res.Title + "</button><p>"
                + res.Year + "</p>" + "<img src=" + res.Poster
                + `" width=\"185\" height=\"273.79\"></br>`
                + `<div><button type=/"button/" value="` + res.imdbID + `" onclick="deleteFavorite(this.value)">` + `DELETE</button></div><hr>`

            ;
            movieList.innerHTML += movieHTML;
        })
}

//delete movie from userfavorite by user and movie id
function deleteFavorite(imdbid) {
    authHeaderValue = "Basic " + btoa(username + ":" + password); //btoa base 64 encoding
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("DELETE", "http://Moviedbjava-env.eba-pppkpw72.us-west-2.elasticbeanstalk.com:5000/movies/favorites/delete/" + username + "/" + imdbid);
    xmlHttp.setRequestHeader("Authorization", authHeaderValue);
    xmlHttp.onreadystatechange = function () {
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            console.log("SUCCESSFULLY DELETED MOVIE FROM FAVORITES")
            location.reload(true);
        }
    }
    xmlHttp.send();
}

//from favorites page user can select movie and be brought into movie page to see more details about the movie.
function fetchMovie(imdbID) {
    sessionStorage.setItem("imdbID", imdbID)
    window.location.href = 'movie.html';
}

//show signed in/signed out tags
function showProfileTags() {
    if (sessionStorage.getItem("user") === null) {
        document.getElementById('accountLinks').style.display = "none";
        document.getElementById('userLinks').style.display = "block";
        document.getElementById("profileName").style.display = "none";
    } else {
        document.getElementById('accountLinks').style.display = "block";
        document.getElementById("profileName").style.display = "block";
        document.getElementById('userLinks').style.display = "none";
        document.getElementById("profileName").innerHTML = username;
    }
}