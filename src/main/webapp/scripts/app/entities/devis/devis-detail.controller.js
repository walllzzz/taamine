'use strict';

angular.module('taamineApp')
    .controller('DevisDetailController', function ($scope, $rootScope, $stateParams, entity, Devis, TypeDevis, ValeurChamp, User) {
        $scope.devis = entity;
        $scope.load = function (id) {
            Devis.get({id: id}, function(result) {
                $scope.devis = result;
            });
        };
        var unsubscribe = $rootScope.$on('taamineApp:devisUpdate', function(event, result) {
            $scope.devis = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
