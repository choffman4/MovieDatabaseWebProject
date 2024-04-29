var authHeaderValue = null;
var username = null;
var password = null;
var foundUser = null;

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

window.onload = function() {
    username = getCookie("username");
    password = getCookie("password");
    showProfileTags();
}

function displayProfile() {
    var profileInfo = document.getElementById("profileInfo");
    profileInfo.innerHTML = foundUser.username;

    var profileHTML = "";
    profileInfo.innerHTML += profileHTML;
}

//show signed in/signed out tags
function showProfileTags() {
    if (sessionStorage.getItem("user") === null) {
        document.getElementById('accountLinks').style.display = "none";
        document.getElementById('userLinks').style.display = "block";
    } else {
        document.getElementById('accountLinks').style.display = "block";
        document.getElementById('userLinks').style.display = "none";
    }
}
