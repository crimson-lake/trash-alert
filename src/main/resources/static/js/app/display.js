function display(id) {
    $.get("/trash-resque/api/ads/" + id, function(data){
        document.getElementById("board").innerHTML = data.title;
    });
}