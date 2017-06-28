var app = angular.module('FirmApplication.excerptController', []);

app.controller('excerpt', ['$scope', 'factory', function ($scope, factory){

    $scope.showSection = false;

    $scope.itemToShow = "";

    $scope.excerptRequest = {
        brojRacuna: "",
        datum: "",
        redniBrojPreseka: ""
    }

    $scope.section = {
        stavkePreseka: [],
        brojRacuna: "",
        datumNaloga: "",
        brojPreseka: "",
        prethodnoStanje: "",
        brojPromenaUKorist: "",
        ukupnoUKorist: "",
        brojPromenaNaTeret: "",
        ukupnoNaTeret: "",
        ukupnoStanje: ""
    }


    $scope.sendRequest = function () {

        if($scope.excerptRequest.brojRacuna === undefined || $scope.excerptRequest.brojRacuna === ""){
            return;
        }

        factory.sendRequest($scope.excerptRequest).then(function success(response) {
            response.data.datumNaloga = new Date(response.data.datumNaloga);

            $scope.section = response.data;
            $scope.changeDivShow(true);

        })
    }


    $scope.changeDivShow = function(flag){
        $scope.showSection = flag;
        $scope.itemToShow = "";
    }
    
    $scope.sectionItemDetails = function (item) {
        $scope.itemToShow = item;
    }

}]);