'use strict';

angular.module('taamineApp')
    .controller('DevisTypeChpDetailController', function ($scope, $rootScope, $stateParams, entity, DevisTypeChp, TypeDevis, Champ) {
        $scope.devisTypeChp = entity;
        $scope.load = function (id) {
            DevisTypeChp.get({id: id}, function(result) {
                $scope.devisTypeChp = result;
            });
        };
        var unsubscribe = $rootScope.$on('taamineApp:devisTypeChpUpdate', function(event, result) {
            $scope.devisTypeChp = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
