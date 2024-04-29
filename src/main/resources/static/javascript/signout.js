function signOut()  {

    document.cookie = "username=" + username + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/favorites.js";
    document.cookie = "password=" + password + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/favorites.js";
    document.cookie = "username=" + username + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/movie.js";
    document.cookie = "password=" + password + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/movie.js";
    document.cookie = "username=" + username + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/index.js";
    document.cookie = "password=" + password + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/index.js";
    document.cookie = "username=" + username + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/favorites.js";
    document.cookie = "password=" + password + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/favorites.js";
    document.cookie = "username=" + username + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/sign_up.js";
    document.cookie = "password=" + password + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/sign_up.js";
    document.cookie = "username=" + username + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/profile.js";
    document.cookie = "password=" + password + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/profile.js";
    document.cookie = "username=" + username + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/login.js";
    document.cookie = "password=" + password + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/login.js";
    document.cookie = "username=" + username + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/favorites.html";
    document.cookie = "password=" + password + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/favorites.html";
    document.cookie = "username=" + username + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/movie.html";
    document.cookie = "password=" + password + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/movie.html";
    document.cookie = "username=" + username + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/index.html";
    document.cookie = "password=" + password + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/index.html";
    document.cookie = "username=" + username + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/favorites.html";
    document.cookie = "password=" + password + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/favorites.html";
    document.cookie = "username=" + username + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/sign_up.html";
    document.cookie = "password=" + password + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/sign_up.html";
    document.cookie = "username=" + username + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/profile.html";
    document.cookie = "password=" + password + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/profile.html";
    document.cookie = "username=" + username + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/login.html";
    document.cookie = "password=" + password + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/login.html";


    sessionStorage.removeItem('searchTerm')
    sessionStorage.removeItem('user');
    sessionStorage.removeItem("pageNumber")

    window.location.href = 'login.html';
}