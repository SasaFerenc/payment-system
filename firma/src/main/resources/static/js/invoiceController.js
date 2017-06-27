var app = angular.module('FirmApplication.invoiceController', []);

app.controller("invoice",['$scope', function ($scope){

    $scope.invoiceFormShow = false;
    $scope.invoiceCreateButton = "Kreiraj Fakturu";

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
        ukupnoRobaUsluga: "",
        ukupanRabat: "",
        ukupanPorez: "",
        oznakaValute: "",
        iznosZaUplatu: "",
        uplataNaRacuna: "",
        datumValute: "",
        items: []
    }


    $scope.addItem = function () {

        if($scope.item.redniBroj === undefined){
            return;
        }

        for(var i in $scope.invoice.items){
            if($scope.invoice.items[i].redniBroj === $scope.item.redniBroj){
                return;
            }
        }

        $scope.invoice.items.push(angular.copy($scope.item));

    }

    $scope.saveInvoice = function () {

    }

    $scope.deleteItem = function (item) {
        $scope.invoice.items.splice($scope.invoice.items.indexOf(item), 1);
    }
    
    $scope.showInvoiceForm = function (flag) {
        $scope.invoiceFormShow = flag;
        if(flag){
            $scope.invoiceCreateButton = "Odustani";
        }else{
            $scope.invoiceCreateButton = "Kreiraj Fakturu";
        }
    }


}]);
