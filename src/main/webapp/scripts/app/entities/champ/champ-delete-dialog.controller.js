'use strict';

angular.module('taamineApp')
	.controller('ChampDeleteController', function($scope, $uibModalInstance, entity, Champ) {

        $scope.champ = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Champ.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
