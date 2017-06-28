var app = angular.module('FirmApplication.routes',['ngRoute']);

app.config(['$routeProvider', function ($routeProvider) {

    $routeProvider
        .when('/invoice',{
            templateUrl: 'invoice.html',
            controller: 'invoice'
        })
        .when('/warrant', {
            templateUrl: 'warrant.html',
            controller: 'warrant'
        })
        .when('/excerpt', {
            templateUrl: 'excerpt.html',
            controller: 'excerpt'
        });
}]);
