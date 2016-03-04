'use strict';

angular.module('taamineApp')
    .controller('TypeDevisController', function ($scope, $state, TypeDevis) {

        $scope.typeDeviss = [];
        $scope.loadAll = function() {
            TypeDevis.query(function(result) {
               $scope.typeDeviss = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.typeDevis = {
                libelle: null,
                id: null
            };
        };
    });
