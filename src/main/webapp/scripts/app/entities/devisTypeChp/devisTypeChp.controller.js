'use strict';

angular.module('taamineApp')
    .controller('DevisTypeChpController', function ($scope, $state, DevisTypeChp) {

        $scope.devisTypeChps = [];
        $scope.loadAll = function() {
            DevisTypeChp.query(function(result) {
               $scope.devisTypeChps = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.devisTypeChp = {
                actif: null,
                obligatoire: null,
                id: null
            };
        };
    });
