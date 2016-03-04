'use strict';

angular.module('taamineApp')
	.controller('ValeurChampDeleteController', function($scope, $uibModalInstance, entity, ValeurChamp) {

        $scope.valeurChamp = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            ValeurChamp.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
