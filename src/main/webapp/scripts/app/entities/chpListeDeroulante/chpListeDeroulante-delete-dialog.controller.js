'use strict';

angular.module('taamineApp')
	.controller('ChpListeDeroulanteDeleteController', function($scope, $uibModalInstance, entity, ChpListeDeroulante) {

        $scope.chpListeDeroulante = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            ChpListeDeroulante.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
