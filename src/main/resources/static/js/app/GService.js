'use strict'
angular.module('geo.services', []).factory('GService', ["$http", "CONSTANTS", function($http, CONSTANTS) {
    var service = {};
    service.getGeoJsonForAllAdds = function() {
        var url = CONSTANTS.getGeoJsonForAllAdds;
        return $http.get(url);
    }
    return service;
}]);