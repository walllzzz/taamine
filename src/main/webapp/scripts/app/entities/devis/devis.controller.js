'use strict';

angular.module('taamineApp')
    .controller('DevisController', function ($scope, $state, Devis) {

        $scope.deviss = [];
        $scope.loadAll = function() {
            Devis.query(function(result) {
               $scope.deviss = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.devis = {
                dateDevis: null,
                id: null
            };
        };
    });
