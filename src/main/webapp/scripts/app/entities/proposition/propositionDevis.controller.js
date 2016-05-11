'use strict';

angular.module('taamineApp')
    .controller('PropositionDevisController', function ($scope, $state, $stateParams, PropositionDevis) {

        $scope.propositions = [];
        $scope.loadAll = function(id) {
            PropositionDevis.query({id:id},function(result) {
               $scope.propositions = result;
            });
        };
        $scope.loadAll($stateParams.id);


        $scope.refresh = function () {
            $scope.loadAll($stateParams.id);
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
