var app = angular.module('FirmApplication.warrantController', []);

app.controller('warrant', ['$scope', function ($scope){

    $scope.warrantFormShow = false;
    $scope.warrantCreateButton = "Kreiraj Nalog";

    var podaciOPlacanju = {
        duznikNalogodavac: "",
        svrhaPlacanja: "",
        primalacPoverilac: "",
        datumNaloga: "",
        datumValute: "",
        racunDuznika: "",
        modelZaduzenja: "",
        pozivNaBrojZaduzenja: "",
        racunPoverioca: "",
        modelOdobrenja: "",
        pozivNaBrojOdobrenja: "",
        iznos: ""
    }

    $scope.warrant = {
        idPoruke: "",
        oznakaValute: "",
        hitno: false,
        podaciOPlacanju: podaciOPlacanju
    }


    $scope.saveWarrant = function () {

    }



    $scope.showWarrantForm = function (flag) {
        $scope.warrantFormShow = flag;
        if(flag){
            $scope.warrantCreateButton = "Odustani";
        }else{
            $scope.warrantCreateButton = "Kreiraj Fakturu";
        }
    }

}]);
