'use strict';

angular.module('taamineApp')
    .controller('ChpListeDeroulanteController', function ($scope, $state, ChpListeDeroulante) {

        $scope.chpListeDeroulantes = [];
        $scope.loadAll = function() {
            ChpListeDeroulante.query(function(result) {
               $scope.chpListeDeroulantes = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.chpListeDeroulante = {
                valeur: null,
                id: null
            };
        };
    });
