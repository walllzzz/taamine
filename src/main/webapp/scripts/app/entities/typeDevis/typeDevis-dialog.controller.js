'use strict';

angular.module('taamineApp').controller('TypeDevisDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'TypeDevis',
        function($scope, $stateParams, $uibModalInstance, entity, TypeDevis) {

        $scope.typeDevis = entity;
        $scope.load = function(id) {
            TypeDevis.get({id : id}, function(result) {
                $scope.typeDevis = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('taamineApp:typeDevisUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.typeDevis.id != null) {
                TypeDevis.update($scope.typeDevis, onSaveSuccess, onSaveError);
            } else {
                TypeDevis.save($scope.typeDevis, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
