'use strict';

angular.module('taamineApp')
    .controller('ChpListeDeroulanteDetailController', function ($scope, $rootScope, $stateParams, entity, ChpListeDeroulante, Champ) {
        $scope.chpListeDeroulante = entity;
        $scope.load = function (id) {
            ChpListeDeroulante.get({id: id}, function(result) {
                $scope.chpListeDeroulante = result;
            });
        };
        var unsubscribe = $rootScope.$on('taamineApp:chpListeDeroulanteUpdate', function(event, result) {
            $scope.chpListeDeroulante = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
