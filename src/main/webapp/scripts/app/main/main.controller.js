'use strict';

angular.module('taamineApp')
    .controller('MainController', function ($scope, Principal) {
    	console.log("main controlleru");
        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
            $scope.isCompany = Principal.hasAuthority('ROLE_COMPANY');
            console.log("company");
            console.log($scope.isCompany);
            //$scope.isProfessional = Principa
            console.log("account ");
            console.log($scope.account);
        });
    });
