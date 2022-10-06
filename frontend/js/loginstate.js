const logsuc = () => {
    document.getElementById("space").style.display = "block";
    document.getElementById("signout").style.display = "block";
    document.getElementById("login").style.display = "none";
}

const rest = () => {
    document.getElementById("space").style.display = "none";
    document.getElementById("signout").style.display = "none";
    document.getElementById("login").style.display = "block";
}

function setCookie(cname, cvalue, exdays) {

    var d = new Date();

    d.setTime(d.getTime() + (exdays*24*60*60*1000));

    var expires = "expires="+d.toUTCString();

    document.cookie = cname + "=" + cvalue + "; " + expires;

}

function signout(){
    rest();
    setCookie("Auth", "", -1);
}

function getCookie() {
    
    var cookie_name = "Auth";
    var allcookies = document.cookie;
    var cookie_pos = allcookies.indexOf(cookie_name);
    if (cookie_pos != -1) {

        cookie_pos = cookie_pos + cookie_name.length + 1;
        var cookie_end = allcookies.indexOf(";", cookie_pos);


        if (cookie_end == -1) {
            cookie_end = allcookies.length;


        }
        var value = unescape(allcookies.substring(cookie_pos, cookie_end));
    }
    return value;
}


window.onload = function(){
    var cookie = getCookie();
    if(cookie != null && cookie!=""){
        logsuc();
    }
};