function openAdModal(id) {
    $.get("/type190-200/outdoor-search/ad/ " + id, function(data) {
        $("#adModalContainer").html(data);
        $("#adModal").modal("show");
    });
};

function sortAds(sortBy) {
    $.get("/type190-200/outdoor-search/sort?sortBy= " + sortBy, function(data) {
            $("#boardWithAds").replaceWith(data);
        });
}

function filterAds(filterBy) {
    $.get("/type190-200/outdoor-search/filter?filterBy= " + filterBy, function(data) {
            $("#boardWithAds").replaceWith(data).done(toggleClearFilterButton());
        });
}

function clearFilter() {
    $.get("/type190-200/outdoor-search/clear" , function(data) {
            $("#boardWithAds").replaceWith(data).done(toggleClearFilterButton());
        });
}

function toggleClearFilterButton() {
  var x = document.getElementById("clearFilter");
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
};