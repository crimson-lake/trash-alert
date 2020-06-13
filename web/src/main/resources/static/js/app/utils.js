function openAdModal(id) {
    $.get("/type190-200/outdoor-search/ad/ " + id, function(data) {
        $("#adModalContainer").html(data);
        $("#adModal").modal("show");
    });
};

function openConfirmationDialog(id) {
    $.get("/type190-200/outdoor-search/my-ads/confirm-delete?id=" + id, function(data) {
        $("#confirmDeleteContainer").html(data);
        $("#confirmDeleteModal").modal("show");
    });
};

function deleteAd(id) {
    $.get("/type190-200/outdoor-search/my-ads/delete?id=" + id, function(data) {
        $("#confirmDeleteModal").modal("hide");
        $("#boardWithMyAds").replaceWith(data);
    });
};

function editAd(id) {
    $.get("/type190-200/outdoor-search/edit-ad?id= " + id, function(data) {
          $("#editAdForm").replaceWith(data);
    });
}

function openLocationModal() {
    $.get("/type190-200/outdoor-search/new-location-form", function(data) {
        $("#locationModalContainer").html(data);
        $("#locationModal").modal("show");
    });
};

function sortAds(sortBy) {
    $.get("/type190-200/outdoor-search/sort?sortBy= " + sortBy, function(data) {
            $("#boardWithAds").replaceWith(data);
        });
}

function filterAds(filterBy) {
    toggled = true;
    $.get("/type190-200/outdoor-search/filter?filterBy= " + filterBy, function(data) {
            $("#boardWithAds").replaceWith(data).done(toggleClearFilterButton());
        });
}

function clearFilter() {
    toggled = false;
    $.get("/type190-200/outdoor-search/clear" , function(data) {
            $("#boardWithAds").replaceWith(data).done(toggleClearFilterButton());
        });
}

function toggleClearFilterButton() {
  var x = document.getElementById("clearFilter");
  if (toggled) {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
};