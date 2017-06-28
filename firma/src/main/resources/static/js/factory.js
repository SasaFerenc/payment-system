angular.module('FirmApplication.factory', []).factory('factory', function ($http) {

    var factory = {};

    // FAKTURE

    factory.sendInvoice = function (invoice) {

        var data = angular.toJson(invoice);

        return $http({
            method: 'POST',
            data: data,
            headers: {'Content-Type': 'application/json'},
            url: "/invoices/send"
        });
    }

    factory.createInvoice = function (invoice) {

        var data = angular.toJson(invoice);

        return $http({
            method: 'POST',
            data: data,
            headers: {'Content-Type': 'application/json'},
            url: "/invoices"
        });
    }

    factory.getSentInvoice = function(){
        return $http({
            method: 'GET',
            url: "/invoices/show/sent"
        });
    }

    factory.getReceivedInvoice = function(){
        return $http({
            method: 'GET',
            url: "/invoices/show/received"
        });
    }

    factory.getCreatedInvoice = function(){
        return $http({
            method: 'GET',
            url: "/invoices/all"
        });
    }

    // NALOZI

    factory.createWarrant = function (warrant) {
        var data = angular.toJson(warrant);

        return $http({
            method: 'POST',
            data: data,
            headers: {'Content-Type': 'application/json'},
            url: "/payments"
        });
    }

    factory.sendWarrant = function (warrant) {
        var data = angular.toJson(warrant);

        return $http({
            method: 'POST',
            data: data,
            headers: {'Content-Type': 'application/json'},
            url: "/payments/send"
        });
    }

    factory.getCreatedWarrant = function () {
        return $http({
            method: 'GET',
            url: "/payments"
        });
    }

 // IZVODI

    factory.sendRequest = function (excerptRequest){
        var data = angular.toJson(excerptRequest);

        return $http({
            method: 'POST',
            data: data,
            headers: {'Content-Type': 'application/json'},
            url: "/statement"
        });
    }


    return factory;
});
