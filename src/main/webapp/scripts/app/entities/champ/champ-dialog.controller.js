'use strict';

angular.module('taamineApp').controller('ChampDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Champ',
        function($scope, $stateParams, $uibModalInstance, entity, Champ) {

        $scope.champ = entity;
        $scope.load = function(id) {
            Champ.get({id : id}, function(result) {
                $scope.champ = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('taamineApp:champUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.champ.id != null) {
                Champ.update($scope.champ, onSaveSuccess, onSaveError);
            } else {
                Champ.save($scope.champ, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
