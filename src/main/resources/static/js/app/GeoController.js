'use strict'
var module = angular.module('geo.controllers', []);
module.controller("GeoController", ["$scope", "GService",
    function($scope, GService) {
        GService.getGeoJsonForAllAdds().then(function(value) {
                    $scope.allGeoData = value.data;
                }, function(reason) {
                    console.log("error occured");
                }, function(value) {
                    console.log("no callback");
                });
    }
]);