function openAdModal(id) {
    $.get("/ad/ " + id, function(data) {
        $("#adModalContainer").html(data);
        $("#adModal").modal("show");
    });
}

function openConfirmationDialog(id) {
    $.get("/my-ads/confirm-delete?id=" + id, function(data) {
        $("#confirmDeleteContainer").html(data);
        $("#confirmDeleteModal").modal("show");
    });
}

function deleteAd(id) {
    $.get("/my-ads/delete?id=" + id, function(data) {
        $("#confirmDeleteModal").modal("hide");
        $("#boardWithMyAds").replaceWith(data);
    });
}

function editAd(id) {
    $.get("/edit-ad?id= " + id, function(data) {
          $("#editAdForm").replaceWith(data);
    });
}

function openLocationModal() {
    $.get("/new-location-form", function(data) {
        $("#locationModalContainer").html(data);
        $("#locationModal").modal("show");
    });
}

function sortAds(sortBy) {
    $.get("/sort?sortBy= " + sortBy, function(data) {
            $("#boardWithAds").replaceWith(data);
        });
}

function filterAds(filterBy) {
    toggled = true;
    $.get("/filter?filterBy= " + filterBy, function(data) {
            $("#boardWithAds").replaceWith(data).done(toggleClearFilterButton());
        });
}

function clearFilter() {
    toggled = false;
    $.get("/clear" , function(data) {
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
}

function refreshBoard(page, size) {
    $.get("/board?size=" + size + "&page=" + page, function(data) {
            $("#boardWithAds").replaceWith(data);
        });
}