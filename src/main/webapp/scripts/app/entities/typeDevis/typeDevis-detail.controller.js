'use strict';

angular.module('taamineApp')
    .controller('TypeDevisDetailController', function ($scope, $rootScope, $stateParams, entity, TypeDevis) {
        $scope.typeDevis = entity;
        $scope.load = function (id) {
            TypeDevis.get({id: id}, function(result) {
                $scope.typeDevis = result;
            });
        };
        var unsubscribe = $rootScope.$on('taamineApp:typeDevisUpdate', function(event, result) {
            $scope.typeDevis = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
