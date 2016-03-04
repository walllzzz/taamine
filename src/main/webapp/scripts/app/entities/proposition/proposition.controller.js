'use strict';

angular.module('taamineApp')
    .controller('PropositionController', function ($scope, $state, Proposition) {

        $scope.propositions = [];
        $scope.loadAll = function() {
            Proposition.query(function(result) {
               $scope.propositions = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.proposition = {
                prix: null,
                dateProposition: null,
                id: null
            };
        };
    });
