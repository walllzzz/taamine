'use strict';

angular.module('taamineApp')
	.controller('PropositionDeleteController', function($scope, $uibModalInstance, entity, Proposition) {

        $scope.proposition = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Proposition.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
