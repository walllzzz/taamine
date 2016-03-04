'use strict';

angular.module('taamineApp')
    .controller('ChampController', function ($scope, $state, Champ) {

        $scope.champs = [];
        $scope.loadAll = function() {
            Champ.query(function(result) {
               $scope.champs = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.champ = {
                chpLibelle: null,
                chpDansListe: null,
                chpType: null,
                id: null
            };
        };
    });
