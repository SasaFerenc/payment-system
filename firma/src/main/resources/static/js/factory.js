angular.module('FirmApplication.factory', []).factory('factory', function ($http) {

    var factory = {};

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

    factory.getReceivedInvoice = function(){
        return $http({
            method: 'GET',
            url: "/invoices/show/createds"
        });
    }

    return factory;
});
