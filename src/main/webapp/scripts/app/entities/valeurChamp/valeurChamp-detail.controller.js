'use strict';

angular.module('taamineApp')
    .controller('ValeurChampDetailController', function ($scope, $rootScope, $stateParams, entity, ValeurChamp, Devis, Champ) {
        $scope.valeurChamp = entity;
        $scope.load = function (id) {
            ValeurChamp.get({id: id}, function(result) {
                $scope.valeurChamp = result;
            });
        };
        var unsubscribe = $rootScope.$on('taamineApp:valeurChampUpdate', function(event, result) {
            $scope.valeurChamp = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
