'use strict';

angular.module('taamineApp')
	.controller('DevisTypeChpDeleteController', function($scope, $uibModalInstance, entity, DevisTypeChp) {

        $scope.devisTypeChp = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            DevisTypeChp.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
