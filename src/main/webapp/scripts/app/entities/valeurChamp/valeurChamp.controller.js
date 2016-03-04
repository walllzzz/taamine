'use strict';

angular.module('taamineApp')
    .controller('ValeurChampController', function ($scope, $state, ValeurChamp) {

        $scope.valeurChamps = [];
        $scope.loadAll = function() {
            ValeurChamp.query(function(result) {
               $scope.valeurChamps = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.valeurChamp = {
                valValeur: null,
                id: null
            };
        };
    });
