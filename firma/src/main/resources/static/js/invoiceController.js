var app = angular.module('FirmApplication.invoiceController', []);

app.controller("invoice",['$scope' ,'factory', function ($scope, factory){

    $scope.invoiceFormShow = false;

    $scope.invoices = [];

    $scope.showDetails = false;
    $scope.showItemDetails = false;

    var initialiseInvoice = function () {

        $scope.item = {
            redniBroj: "",
            nazivRobeUsluge: "",
            kolicina: "",
            jedinicaMere: "",
            jedinicnaCena: "",
            vrednost: "",
            procenatRabata: "",
            iznosRabata: "",
            umanjenoZaRabat: "",
            ukupanPorez: ""
        }


        $scope.invoice ={
            idPoruke: "",
            nazivDobavljaca: "",
            adresaDobavljaca: "",
            pibDobavljaca: "",
            nazivKupca: "",
            adresaKupca: "",
            pibKupca: "",
            brojRacuna: "",
            datumRacuna: "",
            vrednostRobe: "",
            vrednostUsluga: "",
            vrednostRobeUsluga: "",
            ukupanRabat: "",
            ukupanPorez: "",
            oznakaValute: "",
            iznosZaUplatu: "",
            uplataNaRacun: "",
            datumValute: "",
            stavkeFakture: []
        }

    }


    initialiseInvoice();

    $scope.addItem = function () {

        if($scope.item.redniBroj === undefined || $scope.item.redniBroj === ""){
            return;
        }

        for(var i in $scope.invoice.stavkeFakture){
            if($scope.invoice.stavkeFakture[i].redniBroj === $scope.item.redniBroj){
                return;
            }
        }

        $scope.invoice.stavkeFakture.push(angular.copy($scope.item));

    }

    $scope.saveInvoice = function () {

        if($scope.invoice.idPoruke === undefined || $scope.invoice.idPoruke === ""){
            return;
        }

        for(var i in $scope.invoices){
            if($scope.invoices[i].idPoruke === $scope.invoice.idPoruke){
                return;
            }
        }

        $scope.getSentInvoice();
        for(var i in $scope.sentInvoices){
            if($scope.sentInvoices[i].idPoruke === $scope.invoice.idPoruke){
                return;
            }
        }


        factory.createInvoice($scope.invoice).then(function success(response) {
            response.data.datumRacuna = new Date(response.data.datumRacuna);
            response.data.datumValute = new Date(response.data.datumValute);

            $scope.invoices.push(response.data);
            $scope.showInvoiceForm(!$scope.invoiceFormShow);
        });

    }

    $scope.sendInvoice = function (invoice) {
        factory.sendInvoice(invoice).then(function success(response) {

        });
    }

    $scope.details = function (invoice) {
        $scope.showInvoiceForm(!$scope.invoiceFormShow);
        $scope.showDetails = true;

        $scope.invoice = invoice;

    }

    $scope.itemDetails = function(item){
        $scope.showItemDetails = true;
        $scope.item = item;
    }

    $scope.deleteItem = function (item) {
        $scope.invoice.items.splice($scope.invoice.items.indexOf(item), 1);
    }


    $scope.getCreatedInvoice = function () {
        factory.getCreatedInvoice().then(function success(response) {

            for(var i in response.data){
                response.data[i].datumRacuna = new Date(response.data[i].datumRacuna);
                response.data[i].datumValute = new Date(response.data[i].datumValute);
            }

            $scope.invoices = response.data;
        })
    }
    
    $scope.addItemButton = function(){
        $scope.showItemDetails = false;
    }

    $scope.showInvoiceForm = function (flag) {
        $scope.invoiceFormShow = flag;
        $scope.showDetails = false;

        initialiseInvoice();


    }

    
    // POSlATI
    $scope.sentInvoices = [];

    $scope.getSentInvoice = function () {
        factory.getSentInvoice().then(function success(response) {
            for(var i in response.data){
                response.data[i].datumRacuna = new Date(response.data[i].datumRacuna);
                response.data[i].datumValute = new Date(response.data[i].datumValute);
            }
            $scope.sentInvoices = response.data;
        })
    }

    // PRIMLJENI
    $scope.receivedInvoices = [];

    $scope.getReceived = function () {
        factory.getReceivedInvoice().then(function success(response) {
            for(var i in response.data){
                response.data[i].datumRacuna = new Date(response.data[i].datumRacuna);
                response.data[i].datumValute = new Date(response.data[i].datumValute);
            }
            $scope.receivedInvoices = response.data;
        })
    }



}]);
