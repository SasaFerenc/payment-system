var app = angular.module('FirmApplication.warrantController', []);

app.controller('warrant', ['$scope', 'factory' , function ($scope, factory){

    $scope.warrantFormShow = false;
    $scope.warrantCreateButton = "Kreiraj Nalog";
    $scope.showWarrantDetails = false;

    $scope.warrants = [];

    $scope.Combo = {
        poFakturi : null
    }

    var initialiseWarrant = function () {

        $scope.warrant = {
            idPoruke: "",
            oznakaValute: "",
            hitno: false,
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
    }

    initialiseWarrant();


    $scope.saveWarrant = function () {

        if($scope.warrant.idPoruke === undefined || $scope.warrant.idPoruke === ""){
            return;
        }

        for(var i in $scope.warrants){
            if($scope.warrants[i].idPoruke === $scope.warrant.idPoruke){
                return;
            }
        }

        factory.createWarrant($scope.warrant).then(function success(response) {
            response.data.datumNaloga = new Date(response.data.datumNaloga);
            response.data.datumValute = new Date(response.data.datumValute);

            $scope.warrants.push(response.data);
            $scope.showWarrantForm(!$scope.warrantFormShow);
        })


    }

    $scope.warrantDetails = function (warrant) {
        $scope.showWarrantForm(!$scope.warrantFormShow);
        $scope.showWarrantDetails = true;
        $scope.warrant = warrant;
    }

    $scope.sendWarrant = function (warrant) {
        factory.sendWarrant(warrant).then(function success(response) {

        });
    }

    $scope.getCreated = function () {
        factory.getCreatedWarrant().then(function success(response) {

            for(var i in response.data){
                response.data[i].datumNaloga = new Date(response.data[i].datumNaloga);
                response.data[i].datumValute = new Date(response.data[i].datumValute);
            }

            $scope.warrants = response.data;
        })
    }

    $scope.showWarrantForm = function (flag) {
        $scope.warrantFormShow = flag;
        $scope.showWarrantDetails = false;

        initialiseWarrant();

        if(flag){
            $scope.warrantCreateButton = "Odustani";
            getReceived();
        }else{
            $scope.warrantCreateButton = "Kreiraj Fakturu";
        }
    }


    $scope.receivedInvoices = [];

    var getReceived = function () {
        factory.getReceivedInvoice().then(function success(response) {
            for(var i in response.data){
                response.data[i].datumRacuna = new Date(response.data[i].datumRacuna);
                response.data[i].datumValute = new Date(response.data[i].datumValute);
            }
            $scope.receivedInvoices = response.data;
        })
    }

    $scope.selectedWarrant = function () {
        $scope.warrant.primalacPoverilac = $scope.Combo.poFakturi.nazivDobavljaca;
        $scope.warrant.svrhaPlacanja = $scope.Combo.poFakturi.idPoruke;
        $scope.warrant.racunPoverioca = $scope.Combo.poFakturi.uplataNaRacun;
        $scope.warrant.oznakaValute = $scope.Combo.poFakturi.oznakaValute;
        $scope.warrant.datumValute = $scope.Combo.poFakturi.datumValute;
        $scope.warrant.iznos = $scope.Combo.poFakturi.iznosZaUplatu;
        $scope.warrant.racunDuznika = $scope.Combo.poFakturi.brojRacuna;
    }

}]);
