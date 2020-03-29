function display(id, elementId) {
    $.get("/trash-resque/api/ads/" + id, function(data){
        var adHTML = "<h3 class=\"hover\">" + data.title + "</h3>";
        adHTML += "<hr>"
        adHTML += "<p class=\"px-2 d-flex justify-content-end\">created: " + data.created + " </p>";
        adHTML += "<p>" + data.details + "</p>";
        document.getElementById("board" + elementId).innerHTML = adHTML;
    });
};

function displayUsersAds() {
    $.get("/trash-resque/info/username", function(username) {
        $.get("/trash-resque/api/users/" + username + "/ads", function(data) {
            var adsHTML = "";
            for (i=0; i<data.length; i++) {
                adsHTML += "<div id=\"board" + i + "\">"
                adsHTML += "<h3 class=\"hover\" onclick=\"display(" + data[i].id + ", " + i + ")\">"
                adsHTML += data[i].title + "</h3></div><hr>";
            }
            document.getElementById("board").innerHTML = adsHTML;
        });
     });
};