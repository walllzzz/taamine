'use strict';

angular.module('taamineApp')
    .controller('ChampDetailController', function ($scope, $rootScope, $stateParams, entity, Champ) {
        $scope.champ = entity;
        $scope.load = function (id) {
            Champ.get({id: id}, function(result) {
                $scope.champ = result;
            });
        };
        var unsubscribe = $rootScope.$on('taamineApp:champUpdate', function(event, result) {
            $scope.champ = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
