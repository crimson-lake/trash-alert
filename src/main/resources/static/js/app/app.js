'use strict'
var geoApp = angular.module('geo', ['geo.controllers', 'geo.services']);
geoApp.constant("CONSTANTS", {
    getGeoJsonForAllAdds: "/trash-resque/api/ads/geoinfo",
});