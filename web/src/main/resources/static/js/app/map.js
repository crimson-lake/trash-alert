var mymap;
var defaultView;
var defaultZoom;
var layers = new Map();
var markers = new Map();
function showMap(y, x, zoom) {
        defaultView = [y, x];
        defaultZoom = zoom;
		mymap = L.map('mapid').setView([y, x], zoom);

		L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}', {
	    	attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
	    	maxZoom: 18,
	    	id: 'crimson-lake/ck8vwz7ur2qyj1jsy5g1w0bpj',
	    	accessToken: 'pk.eyJ1IjoiY3JpbXNvbi1sYWtlIiwiYSI6ImNrNXB4MHl0eTBzMjUzbW5ydmkxOW9jNjMifQ.bytLrO4_qkCOQqMSSKEn_A'
		}).addTo(mymap);

		var chair = L.icon({
			iconUrl: "https://img.icons8.com/doodle/50/000000/chair.png",
			iconSize: [32, 37],
			iconAnchor: [16, 37],
			popupAnchor: [0, -28]
		});

		function onEachFeature(feature, layer) {
			var popupContext = "<div>" + feature.properties.address + "</div>";
			layers.set(feature.properties.id, layer);
			layer.bindPopup(popupContext, {closeButton: false, className: "popup"})
			     .on('click', function(e){
			        mymap.flyTo(e.latlng, 15);
			        displayAd(feature.properties.id);
			     });
		}

		$.get("/api/ads/geoinfo", function(data) {
			L.geoJSON(data, {
				pointToLayer: function (feature, latlng) {
                    markers.set(feature.properties.id, latlng);
					return L.marker(latlng, {icon: chair, riseOnHover: true});
				},
				onEachFeature: onEachFeature
			}).addTo(mymap);
		});
}

function changeView(y, x, zoom) {
    mymap.flyTo([y, x], zoom);
    console.log("change view")
}

function firePopup(id) {
    mymap.flyTo(markers.get(id), 15);
    layers.get(id).openPopup();
}

function displayAd(id) {
    $.get("/ad/" + id, function(data) {
           $("#boardWithAds").replaceWith(data);
    });
}

function toggle(elementId) {
  var x = document.getElementById(elementId);
  if (x.style.display === "none") {
    toggleAll();
    x.style.display = "block";
    x.parentElement.classList.add("active-ad");
    x.parentElement.scrollIntoViewIfNeeded();
  } else {
    x.style.display = "none";
    x.parentElement.classList.remove("active-ad");
    mymap.closePopup();
    mymap.flyTo(defaultView, defaultZoom);
  }
}

function toggleAll() {
    var divs = document.getElementsByClassName("toggleable");
    for (i = 0; i < divs.length; i++) {
      divs[i].style.display = "none";
      divs[i].parentElement.classList.remove("active-ad");
    }
}

function truncate(text) {
    var maxLength = 100;
    if (text.length > maxLength) {
        text = text.substr(0,maxLength) + '...';
    }
    return text;
}

function flyTo(x, y) {
    mymap.flyTo([y, x], 17);
}
