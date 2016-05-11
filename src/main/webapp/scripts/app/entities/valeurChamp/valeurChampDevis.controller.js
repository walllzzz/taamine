'use strict';

angular.module('taamineApp')
    .controller('ValeurChampDevisController', function ($scope, $state, $stateParams, ValeurChampDevis) {

        $scope.valeurChamps = [];
        $scope.loadAll = function(idDevis) {
            ValeurChampDevis.query({id:idDevis},function(result) {
               $scope.valeurChamps = result;
            });
        };
        console.log("get val champsDevis with id "+$stateParams.id);
        $scope.loadAll($stateParams.id);
        

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
