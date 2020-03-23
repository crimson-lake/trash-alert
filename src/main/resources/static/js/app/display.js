function display(id) {
    $.get("/trash-resque/api/ads/" + id, function(data){
        var adHTML = "<h3>" + data.title + "</h3>";
        adHTML += "<hr>"
        adHTML += "<p class=\"px-2 d-flex justify-content-end\">created: " + data.created + " </p>";
        adHTML += "<p>" + data.details + "</p>";
        document.getElementById("board").innerHTML = adHTML;
    });
};

function displayUsersAds() {
    $.get("/trash-resque/info/username", function(username) {
        $.get("/trash-resque/api/users/" + username + "/ads", function(data) {
            var adsHTML = "";
            for (i=0; i<data.length; i++) {
                adsHTML += "<h4>" + data[i].title + "</h4><hr>";
            }
            document.getElementById("board").innerHTML = adsHTML;
        });
     });
};