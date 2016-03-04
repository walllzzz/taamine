'use strict';

angular.module('taamineApp')
	.controller('DevisDeleteController', function($scope, $uibModalInstance, entity, Devis) {

        $scope.devis = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Devis.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
