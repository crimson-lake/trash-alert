var mymap;
function showMap(y, x, zoom) {
		mymap = L.map('mapid').setView([y, x], zoom);

		L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}', {
	    	attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
	    	maxZoom: 18,
	    	id: 'mapbox/streets-v11',
	    	accessToken: 'pk.eyJ1IjoiY3JpbXNvbi1sYWtlIiwiYSI6ImNrNXB4NG9naDAxYWgza3Awc3U4dGRhYWoifQ.aFqMq_YpgGn7KZC8KlLnew'
		}).addTo(mymap);

		var chair = L.icon({
			iconUrl: "https://img.icons8.com/doodle/50/000000/chair.png",
			iconSize: [32, 37],
			iconAnchor: [16, 37],
			popupAnchor: [0, -28]
		});

		function onEachFeature(feature, layer) {
			var popupContext = "<div class=\"hover h6\" onclick=\"toggle(ad" + feature.properties.id + ")\">";
			popupContext += feature.properties.title + "<br>" + feature.properties.address + "</div>";
			layer.bindPopup(popupContext);
		}

		$.get("/trash-resque/api/ads/geoinfo", function(data) {
			L.geoJSON(data, {
				pointToLayer: function (feature, latlng) {
					return L.marker(latlng, {icon: chair});
				},
				onEachFeature: onEachFeature
			}).addTo(mymap);
		});
};

function changeView(y, x, zoom) {
    mymap.flyTo([y, x], zoom);
    console.log("change view")
};