'use strict';

angular.module('taamineApp')
	.controller('TypeDevisDeleteController', function($scope, $uibModalInstance, entity, TypeDevis) {

        $scope.typeDevis = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            TypeDevis.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
