var app = angular.module('FirmApplication.excerptController', []);

app.controller('excerpt', ['$scope', function ($scope){

    $scope.excerptRequest = {
        brojRacuna: "",
        datum: "",
        redniBrojPreseka: ""
    }



    $scope.sendRequest = function () {
        alert("sljaka");
    }


}]);