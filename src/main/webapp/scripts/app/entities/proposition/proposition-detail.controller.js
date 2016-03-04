'use strict';

angular.module('taamineApp')
    .controller('PropositionDetailController', function ($scope, $rootScope, $stateParams, entity, Proposition, Devis, User) {
        $scope.proposition = entity;
        $scope.load = function (id) {
            Proposition.get({id: id}, function(result) {
                $scope.proposition = result;
            });
        };
        var unsubscribe = $rootScope.$on('taamineApp:propositionUpdate', function(event, result) {
            $scope.proposition = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
